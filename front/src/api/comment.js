import request from '@/axios'


// 查询评论列表
export function commentPage(params) {
    return request({
        url: '/comment/page',
        method: 'get',
        params: params
    })
}

//新增评论
export function addComment(comment) {
    return request({
        url: '/comment/create',
        method: 'post',
        data: comment
    })
}

