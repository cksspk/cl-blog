package cn.iocoder.yudao.module.blog.service.tag;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagCreateReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagExportReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagPageReqVO;
import cn.iocoder.yudao.module.blog.controller.admin.tag.vo.TagUpdateReqVO;
import cn.iocoder.yudao.module.blog.convert.tag.TagConvert;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagDO;
import cn.iocoder.yudao.module.blog.dal.dataobject.tag.TagMappingDO;
import cn.iocoder.yudao.module.blog.dal.mysql.tag.TagMapper;
import cn.iocoder.yudao.module.blog.dal.mysql.tag.TagMappingBatchInsetMapper;
import cn.iocoder.yudao.module.blog.dal.mysql.tag.TagMappingMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.TAG_DUPLICATE;
import static cn.iocoder.yudao.module.blog.enums.ErrorCodeConstants.TAG_NOT_EXISTS;

/**
 * 博客标签 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TagServiceImpl implements TagService {

    @Resource
    private TagMapper tagMapper;
    @Resource
    private TagMappingMapper tagMappingMapper;

    @Resource
    private TagMappingBatchInsetMapper tagMappingBatchInsetMapper;

    @Override
    public Long createTag(TagCreateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(null, reqVO.getTitle());
        // 插入
        TagDO tag = TagConvert.INSTANCE.convert(reqVO);
        tagMapper.insert(tag);
        // 返回
        return tag.getId();
    }



    @Override
    public void updateTag(TagUpdateReqVO reqVO) {
        // 校验正确性
        checkCreateOrUpdate(reqVO.getId(), reqVO.getTitle());
        // 更新
        TagDO updateObj = TagConvert.INSTANCE.convert(reqVO);
        tagMapper.updateById(updateObj);
    }

    @Override
    public void deleteTag(Long id) {
        // 校验存在
        this.validateTagExists(id);
        // 删除
        tagMapper.deleteById(id);
    }

    private void validateTagExists(Long id) {
        if (tagMapper.selectById(id) == null) {
            throw exception(TAG_NOT_EXISTS);
        }
    }

    @Override
    public TagDO getTag(Long id) {
        return tagMapper.selectById(id);
    }

    @Override
    public List<TagDO> getTagList(Collection<Long> ids) {
        return tagMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<TagDO> getTagPage(TagPageReqVO pageReqVO) {
        return tagMapper.selectPage(pageReqVO);
    }

    @Override
    public List<TagDO> getTagList(TagExportReqVO exportReqVO) {
        return tagMapper.selectList(exportReqVO);
    }

    @Override
    public List<TagDO> getTags(String title) {
        return tagMapper.selectList(title);
    }

    private void checkCreateOrUpdate(Long id, String title) {
        // 校验标签存在
        checkTagExists(id);
        // 校验标签 title 唯一性
        if (StrUtil.isNotEmpty(title)) {
            checkTagUnique(id, title);
        }
    }

    public TagDO checkTagExists(Long id) {
        if (id == null) {
            return null;
        }
        TagDO config = tagMapper.selectById(id);
        if (config == null) {
            throw ServiceExceptionUtil.exception(TAG_NOT_EXISTS);
        }
        return config;
    }

    public void checkTagUnique(Long id, String title) {
        TagDO config = tagMapper.selectByTitle(title);
        if (config == null) {
            return;
        }
        // 如果 id 为空，说明不用比较是否为相同 id 的参数配置
        if (id == null) {
            throw ServiceExceptionUtil.exception(TAG_DUPLICATE);
        }
        if (!config.getId().equals(id)) {
            throw ServiceExceptionUtil.exception(TAG_DUPLICATE);
        }
    }


    @Override
    public void updateTagMapping(Long blogId, List<String> tagTitleList) {
        //删除该Id下的所有关联
        tagMappingMapper.deleteTagMappingByBlogId(blogId);

        if (CollUtil.isNotEmpty(tagTitleList)) {
            // 批量插入
            List<TagMappingDO> list = new ArrayList<>();
            for (String title : tagTitleList) {
                //搜索所有的tag
                TagDO tag = tagMapper.selectByTitle(title.trim());
                if (tag != null) {
                    list.add(new TagMappingDO().setBlogId(blogId).setTagId(tag.getId()));
                } else {
                    TagDO temp = new TagDO().setTitle(title.trim()).setColor(
                            StrUtil.format("rgba({}, {}, {}, {})",
                            RandomUtil.randomInt(255),
                                    RandomUtil.randomInt(255),
                                    RandomUtil.randomInt(255), 1));
                    tagMapper.insert(temp);
                    list.add(new TagMappingDO().setBlogId(blogId).setTagId(temp.getId()));
                }
            }
            tagMappingBatchInsetMapper.saveBatch(list);
        }
    }


    @Override
    public List<TagDO> getTagListByBlogId(Long blogId) {
        List<TagMappingDO> tagMappingDOS = tagMappingMapper.selectTagMappingByBlogId(blogId);
        if (CollUtil.isEmpty(tagMappingDOS)) {
            return Collections.emptyList();
        }
        Set<Long> tagIds = CollectionUtils.convertSet(tagMappingDOS, TagMappingDO::getTagId);
        return tagMapper.selectBatchIds(tagIds);
    }




    // ====== Portal 部分

    /**
     * TODO 需要添加到缓存
     * @return
     */
    @Override
    public List<TagDO> getPortalTagList() {
        List<TagDO> tagDOList = tagMapper.selectList();

        return tagDOList;
    }
}
