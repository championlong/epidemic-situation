package com.shimh.common.api;

import com.shimh.common.annotation.LogAnnotation;
import com.shimh.common.constant.ResultCode;
import com.shimh.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 1159588554@qq.com
 * @date 2020/9/22 19:23
 */
@Api(tags = {"上传"})
public interface UploadControllerApi {

    @ApiOperation("文件上传")
    Result upload(HttpServletRequest request, MultipartFile image);
}
