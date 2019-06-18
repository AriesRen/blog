import store from './'
import router, { resetRouter } from '../router'
import {logout, getInfo} from '../api/auth'
import {getToken, removeToken} from '@/utils/cookie'

const user =  {
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
        GetInfo({ commit, state }) {
            return new Promise((resolve, reject) => {
                let token = getToken()

                if (token !== undefined | token !== '') {
                    getInfo(token).then(res => {
                        commit('SET_USERNAME', res.data.username)
                        commit('SET_NICKNAME', res.data.nickname)
                        commit('SET_AVATAR', res.data.avatar)
                        commit('SET_USERID', res.data.userId)
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