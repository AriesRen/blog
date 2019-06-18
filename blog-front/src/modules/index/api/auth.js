import request from '@/utils/request'

export function login(username, password) {
    return request({
        url: '/auth/login',
        method: 'post',
        data: {
            username,
            password
        }
    })
}

export function getInfo(token) {
    return request({
        url: '/user/current',
        method: 'get',
        headers: {
            token
        }
    })
}

export function logout() {
    return request({
        url: 'auth/logout',
        method: 'get'
    })
}

export function getCaptcha(){
    return request({
        url: '/captcha',
        method: 'get'
    })
}

export function checkCaptcha(params) {
    return request({
        url: '/captcha',
        method: 'post',
        params: {
            ...params
        }
    })
}