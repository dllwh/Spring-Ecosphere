package org.dllwh.controller;

import lombok.extern.slf4j.Slf4j;
import org.dllwh.module.*;
import org.dllwh.service.ArticleService;
import org.springframework.cache.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * 把今天最好的表现当作明天最新的起点．．～
 * <p>
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 控制层
 * @author: <a href="mailto:duleilewuhen@sina.com">独泪了无痕</a>
 * @创建时间: 2022-02-27 22:55
 * @版本: V 1.0.1
 * @since: JDK 1.8
 */
@RestController
@Slf4j
public class CacheController {
    @Resource(name = "articleService")
    private ArticleService articleService;

    @PostMapping("add")
    public ResultVo addArticle(Article article) {
        log.info(article.toString());
        Integer result = articleService.addArticle(article);

        if (result >= 0) {
            return ResultVo.success();
        }
        return ResultVo.fail();
    }

    @GetMapping("get")
    public ResultVo getArticle(@RequestParam("id") Integer id) {

        Long start = System.currentTimeMillis();
        Article article = articleService.getArticle(id);
        Long end = System.currentTimeMillis();
        log.info("耗时：" + (end - start));

        if (null != article)
            return ResultVo.success(article);
        return ResultVo.fail();
    }

    @GetMapping("update")
    public ResultVo update(@RequestParam("title") String title, @RequestParam("id") Integer id) {
        final Integer result = articleService.updateContentById(title, id);
        if (result > 0) {
            return ResultVo.success();
        } else {
            return ResultVo.fail();
        }
    }

    @GetMapping("rem")
    public ResultVo remove(@RequestParam("id") Integer id) {
        final Integer result = articleService.removeArticleById(id);
        if (result > 0) {
            return ResultVo.success();
        } else {
            return ResultVo.fail();
        }
    }

    @Cacheable(value = "listUsers", key = "#root.methodName+#username", condition = "#username.equals('duleleiwuhen')")
    public List<String> listUsers(String username) {
        System.out.println("执行了listUsers方法");
        return Arrays.asList("独泪了无痕", "Hello-SpringBoot", System.currentTimeMillis() + "");
    }

    @CachePut(value = "listUsers", key = "#username", condition = "#username.equals('duleleiwuhen')")
    public List<String> updateCache(String username) {
        System.out.println("执行了updateCache方法");
        return Arrays.asList("独泪了无痕", "Hello-SpringBoot", System.currentTimeMillis() + "");
    }
}