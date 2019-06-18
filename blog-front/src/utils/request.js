import axios from 'axios'
import { stringify } from 'querystring';


const request = axios.create({
    // baseURL: 'http://localhost:8080',
    baseURL: 'http://172.19.0.110:8080',
    withCredentials: true,
    timeout: 15000
})

// request.defaults.headers.common['token'] = getToken()

request.interceptors.request.use(
    config =>{
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