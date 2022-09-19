package cn.iocoder.yudao.module.blog.controller.portal.tag;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.blog.controller.portal.tag.vo.*;
import cn.iocoder.yudao.module.blog.convert.tag.TagConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import cn.iocoder.yudao.module.blog.service.tag.TagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "门户 - 博客标签")
@RestController
@RequestMapping("/tag")
@Validated
public class PortalTagController {

    @Resource
    private TagService tagService;


    @GetMapping("/list")
    @ApiOperation("获得博客标签列表")
    public CommonResult<List<PortalTagRespVO>> getTagList() {
        List<TagDO> list = tagService.getPortalTagList();
        return success(TagConvert.INSTANCE.convertPortalList(list));
    }


}
