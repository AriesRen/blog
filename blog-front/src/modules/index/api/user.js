import request from '@/utils/request'

export function getUserById(id) {
    return request({
        url: `/user/${id}`,
        method: 'get'
    })
}