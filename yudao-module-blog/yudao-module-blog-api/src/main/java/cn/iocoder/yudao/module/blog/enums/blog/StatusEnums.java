package cn.iocoder.yudao.module.blog.enums.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatusEnums {

    PUBLISH(1), // 发布状态

    DRAFT(2), // 草稿状态
    ;

    private final Integer status;
}
