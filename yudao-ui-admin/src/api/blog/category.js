import request from '@/utils/request'

// 创建分类
export function createCategory(data) {
  return request({
    url: '/blog/category/create',
    method: 'post',
    data: data
  })
}

// 更新分类
export function updateCategory(data) {
  return request({
    url: '/blog/category/update',
    method: 'put',
    data: data
  })
}

// 角色状态修改
export function changeCategoryStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/blog/category/update-status',
    method: 'put',
    data: data
  })
}

// 删除分类
export function deleteCategory(id) {
  return request({
    url: '/blog/category/delete?id=' + id,
    method: 'delete'
  })
}

// 获得分类
export function getCategory(id) {
  return request({
    url: '/blog/category/get?id=' + id,
    method: 'get'
  })
}

// 获得分类分页
export function getCategoryPage(query) {
  return request({
    url: '/blog/category/page',
    method: 'get',
    params: query
  })
}

// 查询分类（精简)列表
export function listSimpleCategorys() {
  return request({
    url: '/blog/category/list-all-simple',
    method: 'get'
  })
}

// 导出分类 Excel
export function exportCategoryExcel(query) {
  return request({
    url: '/blog/category/export-excel',
    method: 'get',
    params: query,
    responseType: 'blob'
  })
}

