import Vue from 'vue'
import axios from 'axios'
import store from '../store'
import { stringify } from 'querystring';
import { getToken } from '@/utils/cookie'


const request = axios.create({
    baseURL: 'http://localhost:8080',
    withCredentials: true,
    headers: {"token": getToken()},
    timeout: 15000
})

// request.defaults.headers.common['token'] = getToken()

request.interceptors.request.use(
    config => {
        // const token = store.getters.token
        // if(token) {
        //    config.headers['token'] = token
        // }
        console.log(config.headers)
        return config
    },
    error => {
        console.log(stringify(error))
        return Promise.reject(error)
    }
)

request.interceptors.response.use(
    response => {
        const res = response.data
        return res
    },
    error => {
        console.log(error)
        return Promise.reject(stringify(error))
    }
)

export default request