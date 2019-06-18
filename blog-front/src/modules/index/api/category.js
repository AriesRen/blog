import request from '@/utils/request'

export function getCategories(params) {
    return request({
        url: '/category',
        method: 'get',
        params: {
            ...params
        }
    })
}

export function getCategoryById(id){
    return request({
        url: `/category/${id}`,
        method: 'get'
    })
}

export function saveCategory(data) {
    return request({
        url: '/category',
        method: 'post',
        data: {
            ...data
        }
    })
}