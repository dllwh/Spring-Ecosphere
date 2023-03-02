package org.dllwh.mapper;

import org.apache.ibatis.annotations.*;
import org.dllwh.module.Article;

@Mapper
public interface ArticleMapper {
    /**
     * 插入一篇文章
     *
     * @param title  文章标题
     * @param author 作者或者发布人
     * @return 结果的数量
     */
    @Insert("INSERT ecms_article (title,author) VALUE(#{title},#{author})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public Integer addArticle(@Param("title") String title, @Param("author") String author);

    /**
     * 根据id获取文章
     *
     * @param id id
     * @return
     */
    @Select("SELECT * FROM ecms_article WHERE id = #{id}")
    public Article getArticleById(@Param("id") Integer id);

    /**
     * 更新content
     *
     * @param title 文章标题
     * @param id    id
     */
    @Update("UPDATE ecms_article SET title = #{title} WHERE id = #{id}")
    public Integer updateContentById(@Param("title") String title, @Param("id") Integer id);

    /**
     * 根据id删除文章
     *
     * @param id id
     * @return
     */
    @Delete("DELETE ecms_article WHERE id = #{id}")
    public Integer removeArticleById(@Param("id") Integer id);
}