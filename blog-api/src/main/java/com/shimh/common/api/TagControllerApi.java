package com.shimh.common.api;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.Base;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import com.shimh.entity.Tag;
import com.shimh.vo.TagVO;
import io.swagger.annotations.Api;
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
 * @date 2020/9/22 19:22
 */
@Api(tags = {"标签api"})
public interface TagControllerApi {
    @ApiOperation("获取所有标签")
    Result listTags();

    @ApiOperation("获取所有标签，详细")
    Result listCategorysDetail();

    @ApiOperation("获取最热标签")
    Result listHotTags();

    @ApiOperation("根据id获取标签")
    Result getTagById(Integer id);

    @ApiOperation("根据id获取详细标签，文章数")
    Result getTagDetail(Integer id);

    @ApiOperation("添加标签")
    Result saveTag(Tag tag);

    @ApiOperation("修改标签")
    Result updateTag(Tag tag);

    @ApiOperation("删除标签")
    Result deleteTagById(Integer id);
}
