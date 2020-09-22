package com.shimh.common.api;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Category;
import com.shimh.vo.CategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/20 21:57
 */
@Api(tags = {"文章分类api"})
public interface CategoryControllerApi {
    @ApiOperation("获取所有文章分类")
    Result listCategorys();

    @ApiOperation("获取所有文章分类，详细")
    Result listCategorysDetail();

    @ApiOperation("根据id获取文章分类")
    Result getCategoryById(Integer id);

    @ApiOperation("根据id获取详细文章分类，文章数")
    Result getCategoryDetail(Integer id);

    @ApiOperation("添加文章分类")
    Result saveCategory(Category category);

    @ApiOperation("修改文章分类")
    public Result updateCategory(Category category);

    @ApiOperation("删除文章分类")
    Result deleteCategoryById(Integer id);
}
