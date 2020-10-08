package com.shimh.common.api;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Article;
import com.shimh.entity.ArticleBody;
import com.shimh.entity.Tag;
import com.shimh.entity.User;
import com.shimh.vo.ArticleVo;
import com.shimh.vo.PageVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/20 20:03
 */
@Api(value = "文章接口",tags = {"文章api"})
public interface ArticleControllerApi {

    @ApiOperation("获取所有文章")
    Result listArticles(ArticleVo article, PageVo page);

    @ApiOperation("获取最热文章")
    Result listHotArticles();

    @ApiOperation("获取最新文章")
    Result listNewArticles();

    @ApiOperation("根据id获取文章")
    Result getArticleById(Integer id);

    @ApiOperation("根据id获取文章，添加阅读数")
    Result getArticleAndAddViews(Integer id);

    @ApiOperation("根据标签获取文章")
    Result listArticlesByTag(Integer id);


    @ApiOperation("根据分类获取文章")
    Result listArticlesByCategory(Integer id);

    @ApiOperation("发布文章")
    Result saveArticle(Article article);

    @ApiOperation("修改文章")
    Result updateArticle(Article article);

    @ApiOperation("删除文章")
    Result deleteArticleById(Integer id);

    @ApiOperation("获取文章归档日期")
    Result listArchives();

}
