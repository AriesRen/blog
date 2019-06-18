import request from '@/utils/request'

export function getTags(current, size) {
    return request({
        url: '/tag',
        method: 'get',
        params: {
            current,
            size
        }
    })
}

export function getTagById(id) {
    return request({
        url: `/tag/${id}`,
        method: 'get'
    })
}