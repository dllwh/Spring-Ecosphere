package org.dllwh.config;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class RestTemplateConfig {
	/** 建立连接的超时时间 */
	private static int	connectTimeout		= 20000;
	/** 连接不够用的等待时间 */
	private static int	requestTimeout		= 20000;
	/** 每次请求等待返回的超时时间 */
	private static int	socketTimeout		= 30000;
	/** 每个主机最大连接数 */
	private static int	defaultMaxPerRoute	= 100;
	/** 最大连接数 */
	private static int	maxTotalConnections	= 300;

	@Bean
	public RestTemplate buildRestTemplate(ClientHttpRequestFactory factory) {
		RestTemplate restTemplate = new RestTemplate(factory);
		restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
		return restTemplate;
	}

	/**
	 * @方法描述:创建HTTP客户端工厂
	 * @return
	 */
	@Bean
	public HttpComponentsClientHttpRequestFactory createFactory() {
		// httpClient连接配置
		SSLContextBuilder builder = new SSLContextBuilder();

		try {
			TrustStrategy acceptingTrustStrategy = new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) {
					return true;
				}
			};

			builder.loadTrustMaterial(null, acceptingTrustStrategy);
		} catch (Exception e) {
			log.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
		}

		SSLConnectionSocketFactory socketFactory = null;
		try {
			socketFactory = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			log.error("Pooling Connection Manager Initialisation failure because of " + e.getMessage(), e);
		}

		// 注册http和https请求
		Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
				.register("http", PlainConnectionSocketFactory.getSocketFactory())
				.register("https", socketFactory).build();

		// 开始设置连接池
		PoolingHttpClientConnectionManager phccm = new PoolingHttpClientConnectionManager(registry);
		// 最大连接数
		phccm.setMaxTotal(maxTotalConnections);
		// 同路由并发数
		phccm.setDefaultMaxPerRoute(defaultMaxPerRoute);

		HttpClientBuilder httpClientBuilder = HttpClients.custom();
		httpClientBuilder.setSSLSocketFactory(socketFactory);
		httpClientBuilder.setConnectionManager(phccm);
		httpClientBuilder.setConnectionManagerShared(true);
		// 重试次数，默认是3次，没有开启
		httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(3, true));
		// 保持长连接配置，需要在头添加Keep-Alive
		httpClientBuilder.setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE);

		List<Header> headers = new ArrayList<>();
		headers.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.59 Safari/537.36"));
		headers.add(new BasicHeader("Connection", "keep-alive"));

		httpClientBuilder.setDefaultHeaders(headers);

		CloseableHttpClient httpClient = httpClientBuilder.build();

		// httpClient连接配置，底层是配置RequestConfig
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

		factory.setHttpClient(httpClient);
		// 连接超时
		factory.setConnectTimeout(connectTimeout);
		// 数据读取超时时间，即SocketTimeout
		factory.setReadTimeout(socketTimeout);
		// 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
		factory.setConnectionRequestTimeout(requestTimeout);
		// 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
		factory.setBufferRequestBody(false);
		return factory;
	}
}