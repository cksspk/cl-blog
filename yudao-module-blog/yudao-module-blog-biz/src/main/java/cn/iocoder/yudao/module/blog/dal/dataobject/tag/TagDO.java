package cn.iocoder.yudao.module.blog.dal.dataobject.tag;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 博客标签 DO
 *
 * @author 芋道源码
 */
@TableName("blog_tag")
@KeySequence("blog_tag_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagDO extends BaseDO {

    /**
     * 标签编号
     */
    @TableId
    private Long id;
    /**
     * 标签轮廓颜色
     */
    private String color;
    /**
     * 标签名
     */
    private String title;

}
