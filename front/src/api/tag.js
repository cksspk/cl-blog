import request from '@/axios'



//获取标签
export function listTag() {
    return request({
        url: '/tag/list',
        method: 'get',
    })
}

