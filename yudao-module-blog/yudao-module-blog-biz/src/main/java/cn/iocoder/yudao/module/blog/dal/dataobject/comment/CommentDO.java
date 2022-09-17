package cn.iocoder.yudao.module.blog.dal.dataobject.comment;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 评论 DO
 *
 * @author cksspk
 */
@TableName("blog_comment")
@KeySequence("blog_comment_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDO extends BaseDO {

    /**
     * 评论编号
     */
    @TableId
    private Long id;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * Email地址
     */
    private String email;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 地理位置
     */
    private String location;
    /**
     * 系统
     */
    private String os;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 父评论的id
     */
    private Long parentId;
    /**
     * QQ号码
     */
    private String qqNum;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 博客编号
     */
    private Long blogId;
    /**
     * 页面的URL

     */
    private String url;
    /**
     * 1表示显示,0表示不显示
     */
    private Integer display;
    /**
     * 点赞

     */
    private Long good;
    /**
     * 踩
     */
    private Long bad;
    /**
     * 评论内容
     */
    private String content;
    /**
     * html内容
     */
    private String htmlContent;
    /**
     * 接收回复邮件
     */
    private Integer reply;
    /**
     * 回复类型（1：站长回复 2：不是站长回复）
     */
    private Integer adminReply;
    /**
     * 回复的编号
     */
    private Long replyId;

}
