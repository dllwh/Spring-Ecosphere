package org.dllwh.service.impl;

import java.util.List;

import org.dllwh.dao.BlogDao;
import org.dllwh.model.Blog;
import org.dllwh.service.BlogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BlogServiceImpl implements BlogService {

	@Resource
	private BlogDao blogDao;

	@Override
	public Blog getBlogById(Integer id) {
		return blogDao.getBlogById(id);
	}

	@Override
	public void deleteBlogById(Integer id) {
		blogDao.deleteBlogById(id);
	}

	@Override
	public List<Blog> getAllBlogs() {
		return blogDao.getAllBlogs();
	}

	@Override
	public void insertBlog(Blog blog) {
		blogDao.insertBlog(blog);
	}

	@Override
	public void updateBlog(Blog blog) {
		blogDao.updateBlog(blog);
	}
}