package cn.iocoder.yudao.module.blog.dal.dataobject.tag;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 博客标签 DO
 *
 * @author 芋道源码
 */
@TableName("blog_tag_mapping")
@KeySequence("blog_tag_mapping_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TagMappingDO extends BaseDO {

    /**
     * 标签编号
     */
    @TableId
    private Long id;

    /**
     * 标签编号
     */
    private Long tagId;

    /**
     * 博客编号
     */
    private Long blogId;

}
