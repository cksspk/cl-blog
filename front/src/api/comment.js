import request from '@/axios'


// 查询评论列表
export function commentPage(params) {
    return request({
        url: '/comment/page',
        method: 'get',
        params: params
    })
}

