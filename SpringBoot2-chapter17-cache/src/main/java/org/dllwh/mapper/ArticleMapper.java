package org.dllwh.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.dllwh.module.Article;

@Mapper
public interface ArticleMapper {
	/**
	 * 插入一篇文章
	 * 
	 * @param title
	 * @param author
	 * @param content
	 * @param fileName
	 * @return
	 */
	@Insert("INSERT ecms_article (title,author) VALUE(#{title},#{author})")
	@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
	public Integer addArticle(@Param("title") String title, @Param("author") String author);

	/**
	 * 根据id获取文章
	 * 
	 * @param id
	 * @return
	 */
	@Select("SELECT * FROM ecms_article WHERE id = #{id}")
	public Article getArticleById(@Param("id") Integer id);

	/**
	 * 更新content
	 * 
	 * @param content
	 */
	@Update("UPDATE ecms_article SET title = #{title} WHERE id = #{id}")
	public Integer updateContentById(@Param("title") String title, @Param("id") Integer id);

	/**
	 * 根据id删除文章
	 * 
	 * @param id
	 * @return
	 */
	@Delete("DELETE ecms_article WHERE id = #{id}")
	public Integer removeArticleById(@Param("id") Integer id);
}