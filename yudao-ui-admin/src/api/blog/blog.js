import request from '@/utils/request'

// 创建博客
export function createBlog(data) {
  return request({
    url: '/blog/blog/create',
    method: 'post',
    data: data
  })
}

// 更新博客
export function updateBlog(data) {
  return request({
    url: '/blog/blog/update',
    method: 'put',
    data: data
  })
}

// 保存草稿
export function addBlogDraft(data) {
  return request({
    url: '/blog/blog/draft/create',
    method: 'post',
    data: data
  })
}
// 修改博客草稿
export function updateBlogDraft(data) {
  return request({
    url: '/blog/blog/draft/update',
    method: 'put',
    data: data
  })
}

// 修改推荐状态 
export function changeBlogSupport(id, support) {
  const data = {
    id,
    support
  }
  return request({
    url: '/blog/blog/support',
    method: 'put',
    data: data
  })
}

// 修改推荐状态 
export function changeBlogComment(id, comment) {
  const data = {
    id,
    comment
  }
  return request({
    url: '/blog/blog/comment',
    method: 'put',
    data: data
  })
}


// 删除博客
export function deleteBlog(id) {
  return request({
    url: '/blog/blog/delete?id=' + id,
    method: 'delete'
  })
}

// 获得博客
export function getBlog(id) {
  return request({
    url: '/blog/blog/get?id=' + id,
    method: 'get'
  })
}

// 获得博客分页
export function getBlogPage(query) {
  return request({
    url: '/blog/blog/page',
    method: 'get',
    params: query
  })
}


