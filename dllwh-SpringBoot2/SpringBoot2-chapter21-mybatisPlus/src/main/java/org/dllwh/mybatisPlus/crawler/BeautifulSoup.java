package org.dllwh.mybatisPlus.crawler;

import java.io.IOException;
import java.util.List;

import org.dllwh.mybatisPlus.model.SinaWbBlogInfo;
import org.dllwh.mybatisPlus.model.SinaWbBlogInfo.Mblog;
import org.dllwh.mybatisPlus.model.SinaWbUserInfo;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 利用微博的搜索功能抓取数据
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019-11-01 8:57:39 AM
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@Slf4j
public final class BeautifulSoup {
	private static Gson gson = new Gson();

	public static void main(String[] args) throws IOException {
		// long uid = getUid("宋祖儿");
		// SinaWbUserInfo sinaWbUserInfo = getUserinfo(uid);
		// 这里只需要获取微博的ID
		// String containerid = sinaWbUserInfo.getTabsInfo().getTabs().stream().filter(tab -> "weibo".equals(tab.getTabKey())).findFirst().get().getContainerid();
		// getBlogByPage(containerid, 6);
		// getBlogInfo("I7MqwDSjq");
	}

	/**
	 * @方法描述: 获取用户的 UID
	 * @备注 这种搜索可能会搜索出很多结果，而我们只关心大V，那就只判断个人认证的用户，这样能在一定程度上避免拿到错误的 UID。
	 * @param self
	 * @throws IOException
	 */
	public static long getUid(String keyWord) throws IOException {
		String url = String.format("https://s.weibo.com/user?q=%s", keyWord);
		log.info("获取用户的 UID: " + url);
		Long uid = null;

		Response response = Jsoup.connect(url).header("Accept", "*/*").header("Accept-Encoding", "gzip, deflate")
				.header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
				.header("Content-Type", "application/json;charset=UTF-8")
				.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
				.timeout(10000).ignoreContentType(true).ignoreHttpErrors(true).execute();
		Document content = response.parse();
		Element userElement = content.getElementsByClass("card card-user-b s-pg16 s-brt1").first();
		Elements userInfoElement = userElement.getElementsByClass("info").first().children().first().select("div");
		Elements hrefList = userInfoElement.select("a");
		if (hrefList.size() == 3) {
			String title = hrefList.get(1).attr("title");
			if ("微博个人认证".equals(title)) {
				uid = Long.parseLong(hrefList.attr("uid"));
			}
		}
		return uid;
	}

	/**
	 * @方法描述: 获取用户信息，包括 containerid
	 * @param self
	 * @param uid
	 * @throws IOException
	 */
	public static SinaWbUserInfo getUserinfo(long uid) throws IOException {
		String url = String.format("https://m.weibo.cn/api/container/getIndex?type=uid&value=%s", uid);
		log.info("【获取用户信息】：" + url);
		SinaWbUserInfo sinaWbUserInfo = null;
		String result = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get().text();
		JSONObject object = JSONObject.parseObject(result);
		if (object.containsKey("ok") && object.getIntValue("ok") == 1) {
			sinaWbUserInfo = gson.fromJson(object.getJSONObject("data").toJSONString(), SinaWbUserInfo.class);
		}
		return sinaWbUserInfo;
	}

	/**
	 * 
	 * @方法描述: 获取 page 页的微博信息
	 * @param self
	 * @param containerid
	 * @param page
	 * @throws IOException
	 */
	public static List<SinaWbBlogInfo> getBlogByPage(String containerid, int page) throws IOException {
		String url = "";
		List<SinaWbBlogInfo> sinaWbBlogInfoList = Lists.newArrayList();
		for (int i = 0; i < page; i++) {
			url = String.format("https://m.weibo.cn/api/container/getIndex?containerid=%s&page=%s", containerid, i);
			log.info(url);
			String result = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get().text();
			JSONObject object = JSONObject.parseObject(result);
			if (object.containsKey("ok") && object.getIntValue("ok") == 1) {
				JSONArray jsonArray = object.getJSONObject("data").getJSONArray("cards");
				jsonArray.forEach(card -> {
					SinaWbBlogInfo sinaWbBlogInfo = new SinaWbBlogInfo();
					JSONObject cardObject = JSONObject.parseObject(card.toString());
					int cardType = cardObject.getInteger("card_type");
					if (9 == cardType) {
						String scheme = cardObject.getString("scheme");
						sinaWbBlogInfo.setScheme(scheme);
						Mblog mblog = gson.fromJson(cardObject.getJSONObject("mblog").toJSONString(), Mblog.class);
						sinaWbBlogInfo.setMblog(mblog);
						sinaWbBlogInfoList.add(sinaWbBlogInfo);
					}
				});
			}
		}
		return sinaWbBlogInfoList;
	}

	/**
	 * 
	 * @方法描述:
	 * @param self
	 * @param containerid
	 * @param blog_text
	 * @param name
	 * @throws IOException 
	 */
	public static void getBlogInfo(String bid) throws IOException {
		String url = String.format("https://m.weibo.cn/statuses/show?id=%s", bid);
		String result = Jsoup.connect(url).ignoreContentType(true).ignoreHttpErrors(true).get().text();
		System.out.println(result);
	}

	/**
	 * @方法描述: 用于获取某个微博的评论
	 * @param self
	 * @param mblog_id
	 * @param page
	 */
	public void get_comment(long mblog_id, int page) {

	}
}
