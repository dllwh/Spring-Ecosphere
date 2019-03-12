package org.dllwh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.dllwh.entity.Blog;

@Mapper
public interface BlogDao {
	// 查
	@Select("SELECT * FROM t_blog WHERE id = #{id}")
	Blog getBlogById(@Param("id") Integer id);

	// 删
	@Delete("DELETE t_blog WHERE id = = #{id}")
	void deleteBlogById(@Param("id") Integer id);

	// 全
	@Select("SELECT * FROM t_blog")
	List<Blog> getAllBlogs();

	// 增
	void insertBlog(Blog blog);

	// 改
	void updateBlog(Blog blog);
}