package com.shimh.common.api;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Comment;
import com.shimh.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/21 15:42
 */
@Api(tags = {"评论api"})
public interface CommentControllerApi {
    @ApiOperation("获取所有评论")
    Result listComments();

    @ApiOperation("根据id获取评论")
    Result getCommentById(Integer id);

    @ApiOperation("根据文章获取评论")
    Result listCommentsByArticle(Integer id);


    @ApiOperation("添加评论")
    Result saveComment(Comment comment);


    @ApiOperation("删除评论")
    Result deleteCommentById(Integer id);

    @ApiOperation("添加评论，增加评论数")
    Result saveCommentAndChangeCounts(Comment comment);


    @ApiOperation("删除评论，减少评论数")
    Result deleteCommentByIdAndChangeCounts(Integer id);
}
