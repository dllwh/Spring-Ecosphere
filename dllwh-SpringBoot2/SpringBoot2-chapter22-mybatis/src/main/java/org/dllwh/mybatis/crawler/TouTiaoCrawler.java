package org.dllwh.mybatis.crawler;

import java.io.IOException;
import java.util.Date;

import org.dllwh.mybatis.MybatisApplication;
import org.dllwh.mybatis.entity.FunnyEntity;
import org.dllwh.mybatis.service.FunnyService;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 《今日头条》搞笑动态图片爬取
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-10-13 2:42:45 AM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MybatisApplication.class)
public final class TouTiaoCrawler {
	// 搞笑板块的api地址
	public static final String FUNNY = "http://www.toutiao.com/api/pc/feed/?utm_source=toutiao&widen=1";
	// 头条首页地址
	public static final String TOUTIAO = "http://www.toutiao.com";
	@Autowired
	private FunnyService funnyService;
	// 时间戳
	private long nextTime = 0;

	@Test
	public void runCrawler() throws IOException {
		while (true) {
			crawler(nextTime);
		}
	}

	/**
	 * @方法描述: 根据时间戳获取的内容
	 * @param hottime 时间戳
	 * @throws IOException
	 */
	public void crawler(long hotTime) throws IOException {
		System.out.println(hotTime);
		// max_behot_time和max_behot_time_tmp这两个参数值是时间戳，首次请求是0，之后的值是响应数据里面的next中的值。
		String url = FUNNY + "&max_behot_time=" + hotTime + "&max_behot_time_tmp=" + hotTime;

		// 定义接口访问的模块 __all__ : 推荐 news_hot: 热点 funny：搞笑
		// url += "&category=funny";
		Response response = Jsoup.connect(url).header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate")
				.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
				.timeout(10000).ignoreContentType(true).execute();
		// 获取json串
		JSONObject json = JSONObject.parseObject(response.body());

		// 时间戳
		nextTime = json.getJSONObject("next").getLongValue("max_behot_time");
		JSONArray data = json.getJSONArray("data");
		for (int i = 0; i < data.size(); i++) {
			try {
				JSONObject obj = (JSONObject) data.get(i);
				FunnyEntity funnyEntity = JSONObject.toJavaObject(obj, FunnyEntity.class);
				funnyEntity.setBehotTime(new Date(obj.getLong("behot_time") * 1000));
				FunnyEntity entity = funnyService.getByGroupId(funnyEntity.getGroupId());
				if (entity == null) {
					funnyService.insert(funnyEntity);
				} else {
					log.error("----------此文章已经爬过啦！-----------------");
				}
			} catch (Exception e) {
				log.error("", e);
			}
		}
	}
}
