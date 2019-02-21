package org.dllwh.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.dllwh.mapper.ArticleMapper;
import org.dllwh.module.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@CacheConfig(cacheNames = "articleCache")
@Slf4j
public class ArticleService {
	@Autowired
	private ArticleMapper	articleMapper;
	private AtomicInteger	count	= new AtomicInteger(0);

	/**
	 * @方法描述:增加一篇文章 每次就进行缓存
	 * @param article
	 * @return
	 */
	@CachePut
	public Integer addArticle(Article article) {
		Integer result = articleMapper.addArticle(article.getTitle(), article.getAuthor());
		if (result > 0) {
			log.info("--执行增加操作--id:" + article.getId());
		}
		return result;
	}

	/**
	 * @方法描述: 获取文章 以传入的id为键，当state为0的时候不进行缓存
	 * @param id
	 * @return
	 */
	@Cacheable(key = "#id", unless = "#result.status==0")
	public Article getArticle(Integer id) {
		try {
			// 模拟耗时操作
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Article artcile = articleMapper.getArticleById(id);
		log.info("--执行数据库查询操作" + count.incrementAndGet() + "次" + "id:" + id);
		return artcile;
	}

	/**
	 * @方法描述:通过id更新内容，清除以id作为键的缓存
	 * @param title
	 * @param id
	 * @return
	 */
	@CacheEvict(key = "#id")
	public Integer updateContentById(String title, Integer id) {
		Integer result = articleMapper.updateContentById(title, id);
		log.info("--执行更新操作id:--" + id);
		return result;
	}

	/**
	 * @方法描述:通过id移除文章
	 * @param id
	 * @return
	 */
	@CacheEvict(key = "#id")
	public Integer removeArticleById(Integer id) {
		final Integer result = articleMapper.removeArticleById(id);
		log.info("执行删除操作,id:" + id);
		return result;
	}
}
