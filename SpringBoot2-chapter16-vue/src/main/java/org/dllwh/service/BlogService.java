package org.dllwh.service;

import java.util.List;

import org.dllwh.entity.Blog;

public interface BlogService {
	// 查
	Blog getBlogById(Integer id);

	// 删
	void deleteBlogById(Integer id);

	// 全
	List<Blog> getAllBlogs();

	// 增
	void insertBlog(Blog blog);

	// 改
	void updateBlog(Blog blog);
}