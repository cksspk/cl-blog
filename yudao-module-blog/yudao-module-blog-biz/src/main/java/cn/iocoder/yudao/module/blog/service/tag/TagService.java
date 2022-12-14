package cn.iocoder.yudao.module.blog.service.tag;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagExportReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagPageReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagUpdateReqVO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 博客标签 Service 接口
 *
 * @author 芋道源码
 */
public interface TagService {

    /**
     * 创建博客标签
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createTag(@Valid TagCreateReqVO createReqVO);

    /**
     * 更新博客标签
     *
     * @param updateReqVO 更新信息
     */
    void updateTag(@Valid TagUpdateReqVO updateReqVO);

    /**
     * 删除博客标签
     *
     * @param id 编号
     */
    void deleteTag(Long id);

    /**
     * 获得博客标签
     *
     * @param id 编号
     * @return 博客标签
     */
    TagDO getTag(Long id);

    /**
     * 获得博客标签列表
     *
     * @param ids 编号
     * @return 博客标签列表
     */
    List<TagDO> getTagList(Collection<Long> ids);

    /**
     * 获得博客标签分页
     *
     * @param pageReqVO 分页查询
     * @return 博客标签分页
     */
    PageResult<TagDO> getTagPage(TagPageReqVO pageReqVO);

    /**
     * 获得博客标签列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 博客标签列表
     */
    List<TagDO> getTagList(TagExportReqVO exportReqVO);

    /**
     * 获得博客标签列表
     *
     * @param title 标签名称
     * @return 博客标签列表列表
     */
    List<TagDO> getTags(String title);

    /**
     * 更新标签关系
     * @param blogId    博客编号
     * @param tagTitleList 标签列表
     */
    void updateTagMapping(Long blogId, List<String> tagTitleList);

    /**
     * 根据 博客编号获取标签列表
     *
     * @param blogId 博客编号
     * @return 标签列表
     */
    List<TagDO> getTagListByBlogId(Long blogId);


    //====== Portal 部分
    /**
     * @return 标签列表
     */
    List<TagDO> getPortalTagList();
}
