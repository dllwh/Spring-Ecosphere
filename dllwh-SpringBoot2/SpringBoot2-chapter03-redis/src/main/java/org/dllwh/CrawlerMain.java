package org.dllwh;

import org.dllwh.entity.Pictures;
import org.dllwh.service.PicturesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 图片爬虫（使用Junit方式启动此爬虫）
 * @创建者: 皇族灬战狼
 * @联系方式: duleilewuhen@sina.com
 * @创建时间: 2019年1月24日 下午8:18:16
 * @版本: V 1.0.1
 * @since: JDK 1.7
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisSpringBootApplication.class)
public class CrawlerMain {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private PicturesService picturesService;
	@Test
	public void runCrawler() {
		// 返回值
		int result = 1;
		// 访问页码
		Integer page = 1;
		log.info("爬虫开始工作！");
		while (result ==1 ) {
			result = crawler(page);
			page++;
			if (result == 0) {
				log.info("爬虫运行结束！！");
			}
		}
	}

	public int crawler(int page) {
		// 初始化返回值
		int result = 1;
		String url = "";
		url = "https://gank.io/api/data/福利/100/" + page;
		// url = "https://story.hhui.top/detail?id=666106231640";
		// log.info("当前爬取第" + page + "页数据");
		log.info(url);
		JSONObject resultjson = getReturnJson(url);
		if (resultjson != null) {
			if (resultjson.containsKey("results")) {
				JSONArray jArray = resultjson.getJSONArray("results");
				if (jArray != null && jArray.size() > 0) {
					for (Object object : jArray) {
						Pictures gank = JSON.parseObject(JSON.toJSONString(object), Pictures.class);
						gank.setSource("gank");
						picturesService.insertOrUpdate(gank);
					}
				} else {
					result = 0;
				}
			}
		} else {
			result = 0;
		}
		return result;
	}

	public JSONObject getReturnJson(String url) {
		String result = restTemplate.getForObject(url, String.class);
		log.info(result);
		return JSONObject.parseObject(result);
	}
}