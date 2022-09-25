package cn.iocoder.yudao.module.blog.dal.mysql.comment;

import cn.iocoder.yudao.module.blog.dal.dataobject.comment.CommentDO;
import lombok.Data;

@Data
public class ChildCommentDTO extends CommentDO {

    /**
     * 个数
     */
    private Long countCount;

}
