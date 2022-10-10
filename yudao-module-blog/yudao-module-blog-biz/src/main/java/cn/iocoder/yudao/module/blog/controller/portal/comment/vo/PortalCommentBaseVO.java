package cn.iocoder.yudao.module.blog.controller.portal.comment.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* 评论 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PortalCommentBaseVO {


    @ApiModelProperty(value = "昵称")
    private String nickName;


    @ApiModelProperty(value = "Email地址")
    private String email;


    @ApiModelProperty(value = "IP地址")
    private String ip;


    @ApiModelProperty(value = "地理位置")
    private String location;


    @ApiModelProperty(value = "系统")
    private String os;


    @ApiModelProperty(value = "浏览器")
    private String browser;


    @ApiModelProperty(value = "父评论的id")
    private Long parentId;


    @ApiModelProperty(value = "QQ号码")
    private String qqNum;


    @ApiModelProperty(value = "头像地址")
    private String avatar;


    @ApiModelProperty(value = "博客编号")
    private Long blogId;


    @ApiModelProperty(value = "页面的URL")
    private String url;


    @ApiModelProperty(value = "1表示显示,0表示不显示")
    private Integer display;


    @ApiModelProperty(value = "点赞")
    private Long good;


    @ApiModelProperty(value = "踩")
    private Long bad;


    @ApiModelProperty(value = "评论内容")
    private String content;


    @ApiModelProperty(value = "html内容")
    private String htmlContent;


    @ApiModelProperty(value = "接收回复邮件")
    private Integer reply;


    @ApiModelProperty(value = "回复类型（1：站长回复 2：不是站长回复）")
    private Integer adminReply;


    @ApiModelProperty(value = "回复的编号")
    private Long replyId;


    @ApiModelProperty(value = "一级评论编号")
    private Long rootId;


}
