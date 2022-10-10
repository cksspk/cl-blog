package cn.iocoder.yudao.module.blog.controller.portal.comment.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 评论创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PortalCommentCreateReqVO extends PortalCommentBaseVO {

}
