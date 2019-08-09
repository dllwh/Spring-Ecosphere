package org.dllwh;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 在Spring Boot中使用JavaMailSender发送邮件。
 *       <p>
 *       由于Spring Boot 的 starter 模块提供了自动化配置，所以在引入了 spring-boot-starter-mail
 *       依赖后，会根据配置文件中的内容去创建 JavaMailSender 实例，因此我们可以在需要使用的地方直接 @Autowired
 *       来引入邮件发送对象。
 *       </p>
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2018年10月14日 下午6:27:27
 * @版本: V1.0
 * @since: JDK 1.8
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = JavaMailSenderApplictionTest.class)
@EnableAutoConfiguration
public class JavaMailSenderApplictionTest {
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Autowired
	private Environment env;

	/**
	 * @方法描述: 发送简单文本文件
	 * @throws Exception
	 */
	@Test
	public void sendSimpleMail() {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			String from = env.getProperty("spring.mail.username");
			log.info(from);
			String to = env.getProperty("spring.mail.properties.mail.recipient");
			log.info(to);
			message.setFrom(from);
			message.setTo(to);
			message.setSubject("主题：简单邮件");
			message.setText("测试邮件内容");
			mailSender.send(message);
			log.info("发送简单文本文件-发送成功!");
		} catch (Exception e) {
			log.error("发送简单文本文件-发生异常： ", e.fillInStackTrace());
		}
	}
}