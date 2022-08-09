package cn.iocoder.yudao.module.blog.controller.admin.category;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagSimpleRespVO;
import cn.iocoder.yudao.module.blog.convert.tag.TagConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.blog.controller.admin.category.vo.*;
import cn.iocoder.yudao.module.blog.dal.dataobject.category.BlogCategoryDO;
import cn.iocoder.yudao.module.blog.convert.category.BlogCategoryConvert;
import cn.iocoder.yudao.module.blog.service.category.BlogCategoryService;

@Api(tags = "管理后台 - 分类")
@RestController
@RequestMapping("/blog/category")
@Validated
public class BlogCategoryController {


    @Resource
    private BlogCategoryService categoryService;

    @PostMapping("/create")
    @ApiOperation("创建分类")
    @PreAuthorize("@ss.hasPermission('blog:category:create')")
    public CommonResult<Long> createCategory(@Valid @RequestBody BlogCategoryCreateReqVO createReqVO) {
        return success(categoryService.createCategory(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新分类")
    @PreAuthorize("@ss.hasPermission('blog:category:update')")
    public CommonResult<Boolean> updateCategory(@Valid @RequestBody BlogCategoryUpdateReqVO updateReqVO) {
        categoryService.updateCategory(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-status")
    @ApiOperation("修改分类推荐")
    @PreAuthorize("@ss.hasPermission('system:role:update')")
    public CommonResult<Boolean> updateRoleStatus(@Valid @RequestBody BlogCategoryUpdateStatusReqVO reqVO) {
        categoryService.updateCategoryStatus(reqVO.getId(), reqVO.getStatus());
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blog:category:delete')")
    public CommonResult<Boolean> deleteCategory(@RequestParam("id") Long id) {
        categoryService.deleteCategory(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得分类")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blog:category:query')")
    public CommonResult<BlogCategoryRespVO> getCategory(@RequestParam("id") Long id) {
        BlogCategoryDO category = categoryService.getCategory(id);
        return success(BlogCategoryConvert.INSTANCE.convert(category));
    }

    @GetMapping("/list")
    @ApiOperation("获得分类列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blog:category:query')")
    public CommonResult<List<BlogCategoryRespVO>> getCategoryList(@RequestParam("ids") Collection<Long> ids) {
        List<BlogCategoryDO> list = categoryService.getCategoryList(ids);
        return success(BlogCategoryConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/list-all-simple")
    @ApiOperation(value = "获取分类精简信息列表", notes = "主要用于前端的下拉选项")
    public CommonResult<List<CategorySimpleRespVO>> getSimpleDepts() {
        // 获得岗位列表，只要开启状态的
        List<BlogCategoryDO> list = categoryService.getCategorys();
        // 排序后，返回给前端
//        list.sort(Comparator.comparing(TagDO::getSort)); //TODO 可增加排序
        return success(BlogCategoryConvert.INSTANCE.convertList03(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得分类分页")
    @PreAuthorize("@ss.hasPermission('blog:category:query')")
    public CommonResult<PageResult<BlogCategoryRespVO>> getCategoryPage(@Valid BlogCategoryPageReqVO pageVO) {
        PageResult<BlogCategoryDO> pageResult = categoryService.getCategoryPage(pageVO);
        return success(BlogCategoryConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出分类 Excel")
    @PreAuthorize("@ss.hasPermission('blog:category:export')")
    @OperateLog(type = EXPORT)
    public void exportCategoryExcel(@Valid BlogCategoryExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BlogCategoryDO> list = categoryService.getCategoryList(exportReqVO);
        // 导出 Excel
        List<BlogCategoryExcelVO> datas = BlogCategoryConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "分类.xls", "数据", BlogCategoryExcelVO.class, datas);
    }

}
