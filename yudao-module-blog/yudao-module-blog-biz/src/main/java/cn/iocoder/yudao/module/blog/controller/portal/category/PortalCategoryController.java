package cn.iocoder.yudao.module.blog.controller.portal.category;


import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.blog.controller.admin.category.vo.BlogCategoryRespVO;
import cn.iocoder.yudao.module.blog.convert.category.BlogCategoryConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.module.blog.service.category.BlogCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "门户 - 分类")
@RestController
@RequestMapping("/f/category")
@Validated
@Slf4j
public class PortalCategoryController {

    @Resource
    private BlogCategoryService categoryService;


    @GetMapping("/list")
    @ApiOperation("获得分类列表")
    public CommonResult<List<BlogCategoryRespVO>> getCategoryList() {
        List<BlogCategoryDO> list = categoryService.getAllCategoryList();
        return success(BlogCategoryConvert.INSTANCE.convertList(list));
    }
}
