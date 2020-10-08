package com.shimh.dao;

import com.shimh.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/26 14:43
 */
@Mapper
public interface ArticleMapper {
    List<Article> findAll();
}
