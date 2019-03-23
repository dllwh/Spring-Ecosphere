package org.dllwh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;
import org.dllwh.model.Blog;

@Mapper
public interface BlogDao {
	// 查
	@Select("SELECT * FROM t_blog WHERE id = #{id}")
	Blog getBlogById(@Param("id") Integer id);

	// 删
	@Delete("DELETE  FROM t_blog WHERE id = #{id}")
	void deleteBlogById(@Param("id") Integer id);

	// 全
	@Select("SELECT * FROM t_blog")
	List<Blog> getAllBlogs();

	// 增
	@Insert("insert into `t_blog`(`title`,`link`,`author`,`tag`) values(#{title},#{link},#{author},#{tag});")
	@Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
	void insertBlog(Blog blog);

	// 改
	@UpdateProvider(type = BlogDaoProvider.class, method = "updateBlog")
	void updateBlog(Blog blog);

	class BlogDaoProvider {
		public String updateBlog(final Blog blog) {
			return new SQL() {
				{
					UPDATE("t_blog");
					if (blog.getTitle() != null) {
						SET("title=#{title}");
					}
					if (blog.getLink() != null) {
						SET("link=#{link}");
					}
					if (blog.getAuthor() != null) {
						SET("author=#{author}");
					}
					if (blog.getTag() != null) {
						SET("tag=#{tag}");
					}
					WHERE("id = #{id}");
				}
			}.toString();
		}
	}
}