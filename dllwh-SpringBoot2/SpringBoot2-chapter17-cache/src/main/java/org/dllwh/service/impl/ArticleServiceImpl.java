package org.dllwh.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dllwh.mapper.ArticleMapper;
import org.dllwh.module.Article;
import org.dllwh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 业务层实现
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-02-27 22:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@Slf4j
@CacheConfig(cacheNames = "articleCache")
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    private ArticleMapper articleMapper;
    private AtomicInteger count = new AtomicInteger(0);

    @Autowired
    public void setArticleMapper(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    @CachePut
    public Integer addArticle(Article article) {
        Integer result = articleMapper.addArticle(article.getTitle(), article.getAuthor());
        if (result > 0) {
            log.info("--执行增加操作--id:" + article.getId());
        }
        return result;
    }

    /**
     * @param id
     * @return
     * @方法描述: 获取文章 以传入的id为键，当state为0的时候不进行缓存
     */
    @Override
    @Cacheable(value = "getArticle", key = "#id", unless = "#result.status==0")
    public Article getArticle(Integer id) {
        try {
            // 模拟耗时操作
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Article article = articleMapper.getArticleById(id);
        log.info("--执行数据库查询操作" + count.incrementAndGet() + "次" + "id:" + id);
        return article;
    }

    @Override
    @CacheEvict(key = "#id")
    public Integer updateContentById(String title, Integer id) {
        Integer result = articleMapper.updateContentById(title, id);
        log.info("--执行更新操作id:--" + id);
        return result;
    }

    @Override
    @CacheEvict(key = "#id")
    public Integer removeArticleById(Integer id) {
        final Integer result = articleMapper.removeArticleById(id);
        log.info("执行删除操作,id:" + id);
        return result;
    }
}
