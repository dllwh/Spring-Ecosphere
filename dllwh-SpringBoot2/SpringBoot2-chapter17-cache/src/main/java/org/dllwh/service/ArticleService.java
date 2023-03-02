package org.dllwh.service;

import org.dllwh.module.Article;
import org.springframework.stereotype.Service;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 业务层接口
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-02-27 22:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
public interface ArticleService {
    /**
     * 增加一篇文章
     * @param article
     * @return
     */
    public Integer addArticle(Article article);

    /**
     * 获取文章
     * @param id
     * @return
     */
    public Article getArticle(Integer id);

    /**
     * 通过id更新内容
     * @param title
     * @param id
     * @return
     */
    public Integer updateContentById(String title, Integer id);

    /**
     * 通过id移除文章
     * @param id
     * @return
     */
    public Integer removeArticleById(Integer id);
}
