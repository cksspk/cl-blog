package cn.iocoder.yudao.module.blog.controller.admin.tag.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 博客标签创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TagCreateReqVO extends TagBaseVO {

}
