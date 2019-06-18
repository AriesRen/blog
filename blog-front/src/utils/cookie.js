import Cookie from 'js-cookie'

const tokenName = 'token'

export function getToken() {
    return Cookie.get(tokenName)
}

export function setToken(token){
    return Cookie.set(tokenName, token)
}

export function removeToken() {
    return Cookie.remove(tokenName)
}

export function setCookie(name, value, expire) {
    return Cookie.set(name, value, expire)
}

export function getCookie(name) {
    return Cookie.get(name)
}

export function deleteCookie(name) {
    return Cookie.remove(name)
}