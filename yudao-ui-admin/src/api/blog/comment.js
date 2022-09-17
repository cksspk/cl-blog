import request from '@/utils/request'

// 创建评论
export function createComment(data) {
  return request({
    url: '/blog/comment/create',
    method: 'post',
    data: data
  })
}

// 更新评论
export function updateComment(data) {
  return request({
    url: '/blog/comment/update',
    method: 'put',
    data: data
  })
}

// 删除评论
export function deleteComment(id) {
  return request({
    url: '/blog/comment/delete?id=' + id,
    method: 'delete'
  })
}

// 获得评论
export function getComment(id) {
  return request({
    url: '/blog/comment/get?id=' + id,
    method: 'get'
  })
}

// 获得评论分页
export function getCommentPage(query) {
  return request({
    url: '/blog/comment/page',
    method: 'get',
    params: query
  })
}

// 导出评论 Excel
export function exportCommentExcel(query) {
  return request({
    url: '/blog/comment/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}
