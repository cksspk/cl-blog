package cn.iocoder.yudao.module.blog.dal.dataobject.blog;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 博客 DO
 *
 * @author 芋道源码
 */
@TableName("blog_blog")
@KeySequence("blog_blog_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BlogDO extends BaseDO {

    /**
     * 博客编号
     */
    @TableId
    private Long id;
    /**
     * 分类id 
     */
    private Long categoryId;
    /**
     *  文章标题 
     */
    private String title;
    /**
     *  摘要 
     */
    private String summary;
    /**
     * 封面类型(1表示普通,0表示没有,2表示大图)
     */
    private Integer headerImgType;
    /**
     *  封面图片地址 
     */
    private String headerImg;
    /**
     * HTML 格式化后的内容
     */
    private String htmlContent;
    /**
     *  正文内容 
     */
    private String content;
    /**
     *  文章状态,	    1表示已经发表,	    2表示草稿箱 
     */
    private Integer status;
    /**
     *  是否允许评论
     */
    private Integer comment;
    /**
     *  推荐 
     */
    private Integer support;
    /**
     *  权重 
     */
    private Long weight;
    /**
     *  点赞数 
     */
    private Long likes;
    /**
     *  点击数 
     */
    private Long click;

}
