package com.shimh.repository;

import com.shimh.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/10/4 20:21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestArticleRepository {
    @Autowired
    ArticleRepository articleRepository;


    @Test
    public void testFindAll(){
        List<Article> articles = articleRepository.findAll();
        System.out.println(articles);
    }


}
