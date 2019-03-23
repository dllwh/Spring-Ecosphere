package org.dllwh.controller;

import java.util.List;
import java.util.Map;

import org.dllwh.model.Blog;
import org.dllwh.service.BlogService;
import org.dllwh.utils.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * 把今天最好的表现当作明天最新的起点．．～
 *
 * Today the best performance as tomorrow newest starter!
 *
 * @类描述: 控制层 简单演示增删改查及分页
 * @创建者: 独泪了无痕--duleilewuhen@sina.com
 * @创建时间: 2019年3月23日 下午11:09:17
 * @版本: V1.0.1
 * @since: JDK 1.8
 */
@RestController
@Api(tags = "博客API", value = "博客API")
public class SwaggerController {
	@Autowired
	private BlogService blogService;

	@GetMapping("getBlogById/{id}")
	@ApiOperation(value = "通过ID获取博客信息", notes = "通过ID获取博客信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id", value = "查询ID", required = true,allowEmptyValue=true)
	})
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok", response = Blog.class),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	public Map<String, Object> getBlogById(@PathVariable Integer id) {
		Blog blog = blogService.getBlogById(id);
		if (blog == null) {
			return Json.fail();
		} else {
			return Json.success(blog);
		}
	}

	@DeleteMapping("deleteBlogById/{id}")
	@ApiOperation(value = "通过ID删除博客信息", notes = "通过ID删除博客信息")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	public Map<String, Object> deleteBlogById(@PathVariable Integer id) {
		Blog blogById = blogService.getBlogById(id);
		if (blogById == null) {
			return Json.fail();
		} else {
			blogService.deleteBlogById(id);
			return Json.success();
		}
	}

	@GetMapping("getAllBlogs")
	@ApiOperation(value = "获取全部博客信息", notes = "获取全部博客信息")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	public Map<String, Object> getAllBlogs() {
		List<Blog> allBlogs = blogService.getAllBlogs();
		if (allBlogs.size() == 0) {
			return Json.fail();
		} else {
			return Json.success(allBlogs);
		}
	}

	@PutMapping("insertBlog")
	@ApiOperation(value = "创建博客信息", notes = "创建博客信息")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	public Map<String, Object> insertBlog(Blog blog) {
		blogService.insertBlog(blog);
		return Json.success();
	}

	@PutMapping("updateBlog")
	@ApiOperation(value = "博客信息修改", notes = "博客信息修改")
	@ApiResponses({ 
		@ApiResponse(code = 200, message = "ok"),
		@ApiResponse(code = 400, message = "业务逻辑异常"),
		@ApiResponse(code = 500, message = "服务器内部错误")
	})
	public Map<String, Object> updateBlog(Blog blog) {
		blogService.updateBlog(blog);
		return Json.success();
	}
}