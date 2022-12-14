import request from '@/utils/request'

// 上传文件
export function uploadFile(id) {
  return request({
    url: '/infra/file/upload',
    method: 'post'
  })
}

// 删除文件
export function deleteFile(id) {
  return request({
    url: '/infra/file/delete?id=' + id,
    method: 'delete'
  })
}

// 获得文件分页
export function getFilePage(query) {
  return request({
    url: '/infra/file/page',
    method: 'get',
    params: query
  })
}
