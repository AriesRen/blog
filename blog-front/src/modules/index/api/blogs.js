import request from '@/utils/request'

export function getPosts(current, size) {
    return request({
        url: '/post',
        method: 'get',
        params: {
            current,
            size
        }
    })
}

export function getPostById(id) {
    return request({
        url:  '/post/' + id,
        method: 'get'
    })
}

export function getCategoryByPostId(id) {
    return request({
        url: `/post/${id}/category`,
        method: 'get'
    })
}

export function getTagsByPostId(id) {
    return request({
        url: `/post/${id}/tags`,
        method: 'get'
    })
}