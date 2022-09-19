package cn.iocoder.yudao.module.blog.controller.portal.blog.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@ApiModel(value = "管理后台 - 博客分页时的信息 Response VO", description = "相比博客基本信息来说，会多分类信息")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PortalBlogPageRespVO extends PortalBlogRespVO {

    /**
     * 所在分类
     */
    private Category category;

    @ApiModel("分类")
    @Data
    public static class Category {

        @ApiModelProperty(value = "分类编号", required = true, example = "1")
        private Long id;

        @ApiModelProperty(value = "分类名称", required = true, example = "分类A")
        private String title;

    }

}
