package org.dllwh.controller;

import org.dllwh.module.Article;
import org.dllwh.module.ResultVo;
import org.dllwh.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CacheController {
	@Autowired
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
}