import router, { resetRouter } from '../router'
import { login, logout, getInfo } from '../api/auth'
import { setToken, getToken, removeToken } from '@/utils/cookie'

const user = {
    state: {
        username: '',
        nickname: '',
        userId: '',
        avatar: '',
        roles: [],
        permissions: [],
        token: ''
    },
    mutations: {
        SET_USERID: (state, userid) => {
            state.userId = userid;
        },
        SET_AVATAR: (state, avatar) => {
            state.avatar = avatar
        },
        SET_USERNAME: (state, username) => {
            state.username = username;
        },
        SET_NICKNAME: (state, nickname) => {
            state.nickname = nickname
        },
        SET_ROLES: (state, roles) => {
            state.roles = roles
        },
        SET_PERMISSIONS: (state, permissions) => {
            state.permissions = permissions
        },
        SET_TOKEN: (state, token) => {
            state.token = token
        },
        RESET_USER: (state) => {
            state.userId = ''
            state.token = ''
            state.username = ''
            state.nickname = ''
            state.roles = []
            state.permissions = []
            state.avatar = ''
        }
    },
    actions: {
        Login({ commit, state }, loginForm) {
            return new Promise((resolve, reject) => {
                const { username, password } = loginForm
                login(username, password).then(res => {
                    if (res.code === 200) {
                        commit('SET_TOKEN', res.data.token)
                        setToken(res.data.token)
                        resolve(res)
                    } else {
                        resolve(res)
                    }
                }).catch(error => {
                    reject(error)
                })
            })
        },
        GetInfo({ commit, state }) {
            return new Promise((resolve, reject) => {
                let token = getToken()

                if (token !== undefined | token !== '') {
                    getInfo(token).then(res => {
                        if (res.code === 200) {
                            commit('SET_USERNAME', res.data.username)
                            commit('SET_NICKNAME', res.data.nickname)
                            commit('SET_AVATAR', res.data.avatar)
                            commit('SET_USERID', res.data.userId)
                        } else {
                            console.log("token已过期")
                            commit('RESET_USER')
                            removeToken()
                        }
                        resolve(res)
                    }).catch(error => {
                        reject(error)
                    })
                }
            })
        },
        FedLogout({ commit, state }) {
            return new Promise(resolve => {
                commit('RESET_USER')
                removeToken()
                resolve()
            })
        },
        Logout({ commit, state }) {
            return new Promise(async (resolve) => {
                logout().then(res => {
                    console.log(res)
                    commit('RESET_USER')
                    removeToken()
                    resetRouter()
                    resolve()
                }).catch(error => {
                    console.log(error)
                    commit('RESET_USER')
                    removeToken()
                })
            })
        }
    }
}

export default user