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
     * @param id
     * @return
     */
    List<Blog> getAllBlogs();

    /**
     * 增
     *
     * @param id
     * @return
     */
    void insertBlog(Blog blog);

    /**
     * 改
     *
     * @param id
     * @return
     */
    void updateBlog(Blog blog);
}