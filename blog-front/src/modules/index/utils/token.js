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
