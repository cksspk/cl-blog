import request from '@/axios'



//获取所有分类
export function listCategories() {
    return request({
        url: '/category/list',
        method: 'get',
    })
}
