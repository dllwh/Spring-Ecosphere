package org.dllwh.service;

import java.util.List;

import org.dllwh.model.Blog;

public interface BlogService {
    /**
     * 查
     *
     * @param id
     * @return
     */
    Blog getBlogById(Integer id);

    /**
     * 删
     *
     * @param id
     * @return
     */
    void deleteBlogById(Integer id);

    /**
     * 全
     *
     * @return
     */
    List<Blog> getAllBlogs();

    /**
     * 增
     * @param blog
     */
    void insertBlog(Blog blog);

    /**
     * 改
     * @param blog
     */
    void updateBlog(Blog blog);
}