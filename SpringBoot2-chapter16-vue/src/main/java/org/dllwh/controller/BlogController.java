package org.dllwh.controller;

import java.util.List;
import java.util.Map;

import org.dllwh.entity.Blog;
import org.dllwh.service.BlogService;
import org.dllwh.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class BlogController {
	@Autowired
	private BlogService blogService;

	@GetMapping("getBlogById/{id}")
	public Map<String, Object> getBlogById(@PathVariable Integer id) {
		Blog blogById = blogService.getBlogById(id);
		if (blogById == null) {
			return Json.fail();
		} else {
			return Json.success(blogById);
		}

	}

	@DeleteMapping("deleteBlogById/{id}")
	public Map<String, Object> deleteBlogById(@PathVariable Integer id) {
		Blog blogById = blogService.getBlogById(id);
		if (blogById == null) {
			return Json.fail();
		} else {
			blogService.deleteBlogById(id);
			return Json.success(blogById);
		}
	}

	@GetMapping("getAllBlogs")
	public Map<String, Object> getAllBlogs() {
		List<Blog> allBlogs = blogService.getAllBlogs();
		if (allBlogs.size() == 0) {
			return Json.fail();
		} else {
			return Json.success(allBlogs);
		}
	}

	@PostMapping("insertBlog")
	public Map<String, Object> insertBlog(Blog blog) {
		blogService.insertBlog(blog);
		return Json.success(blog);
	}

	@PutMapping("updateBlog")
	public Map<String, Object> updateBlog(Blog blog) {
		blogService.updateBlog(blog);
		return Json.success(blog);
	}
}