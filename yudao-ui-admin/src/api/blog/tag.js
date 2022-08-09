import request from '@/utils/request'

// 创建博客标签
export function createTag(data) {
  return request({
    url: '/blog/tag/create',
    method: 'post',
    data: data
  })
}

// 更新博客标签
export function updateTag(data) {
  return request({
    url: '/blog/tag/update',
    method: 'put',
    data: data
  })
}

// 删除博客标签
export function deleteTag(id) {
  return request({
    url: '/blog/tag/delete?id=' + id,
    method: 'delete'
  })
}

// 获得博客标签
export function getTag(id) {
  return request({
    url: '/blog/tag/get?id=' + id,
    method: 'get'
  })
}

// 获得博客标签分页
export function getTagPage(query) {
  return request({
    url: '/blog/tag/page',
    method: 'get',
    params: query
  })
}

// 查询博客标签（精简)列表
export function listSimpleTags(title) {
  return request({
    url: '/blog/tag/list-all-simple?title=' + title,
    method: 'get'
  })
}


// 导出博客标签 Excel
export function exportTagExcel(query) {
  return request({
    url: '/blog/tag/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
