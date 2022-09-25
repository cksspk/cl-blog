import request from '@/axios'


// 查询博客列表
export function blogPage(params) {
    return request({
        url: '/blog/page',
        method: 'get',
        params: params
    })
}

//获取推荐内容
export function listRecommend(query) {
    return request({
        url: '/blog/support',
        method: 'get',
        params: query
    })
}

//获取最热内容
export function listHot(query) {
    return request({
        url: '/blog/hot',
        method: 'get',
        params: query
    })
}

// 查询博客详情
export function BlogDetail(id) {
    return request({
        url: '/blog/get/' + id,
        method: 'get',
    })
}