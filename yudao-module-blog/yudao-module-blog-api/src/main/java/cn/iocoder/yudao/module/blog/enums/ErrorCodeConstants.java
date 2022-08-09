package cn.iocoder.yudao.module.blog.enums;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

/**
 * Blog 错误码 Core 枚举类
 *
 * Blog 系统，使用 1-020-000-000 段
 */
public interface ErrorCodeConstants {


    /**
     * ========== 博客标签 1-020-000-000 ==========
     */
    ErrorCode TAG_NOT_EXISTS = new ErrorCode(1020000000, "博客标签不存在");
    ErrorCode TAG_DUPLICATE = new ErrorCode(1020000001, "博客标签名重复");



    // ========== 分类 1-020-001-000 ==========
    ErrorCode CATEGORY_NOT_EXISTS = new ErrorCode(1020001000, "分类不存在");

    // ========== 分类 1-030-001-000 ==========
    ErrorCode BLOG_NOT_EXISTS = new ErrorCode(1030001000, "博客不存在");

}
