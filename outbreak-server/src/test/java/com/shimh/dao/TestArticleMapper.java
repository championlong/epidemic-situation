package com.shimh.dao;

import com.shimh.entity.Article;
import io.swagger.annotations.ApiOperation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/26 14:50
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestArticleMapper {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    public void testFindAll(){
        List<Article> articles = articleMapper.findAll();
        System.out.println(articles);
    }
}
