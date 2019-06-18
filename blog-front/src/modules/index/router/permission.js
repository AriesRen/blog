import NProgress from 'nprogress'
import 'nprogress/nprogress.css'
import router from './index'
import { getToken } from '@/utils/cookie'
import store from '../store'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login', '404', '401', '403', '/', '/index', '/tags']

router.beforeEach(async (to, from, next) => {
    NProgress.start()
    const token = getToken()

    if (token) {
        const { data } = await store.dispatch('GetInfo')
        next()
    }

    if (to.meta.requireAuth === true) {
        if (token) {
            if (to.path === '/login') {
                next('/')
                NProgress.done()
            } else {
                const roles = store.getters.roles && store.getters.roles.length > 0
                if (roles) {
                    next()
                    NProgress.done()
                } else {
                    const { data } = await store.dispatch('GetInfo')
                    console.log(data)
                    console.log(to)
                    next()
                }
            }
        } else {
            next(`/login?redireect=${to.path}`)
            NProgress.done()
        }
    } else {
        next()
        NProgress.done()
    }

    // if (token) {
    //     if (to.path === '/login') {
    //         next('/')
    //         NProgress.done()
    //     } else {
    //         const roles = store.getters.roles && store.getters.roles.length > 0
    //         if (roles) {
    //             next()
    //         } else {
    //             try {
    //                 // const { roles } = await store.dispatch('GetInfo')
    //                 // console.log(roles)
    //                 store.dispatch('GetInfo').then(res => {
    //                     console.log(res)
    //                 })
    //                 next({ ...to, replace: true })
    //             } catch (error) {
    //                 console.log(error)
    //                 await store.dispatch('FedLogout')
    //                 next(`/login?redireect=${to.path}`)
    //                 NProgress.done()
    //             }
    //         }
    //     }
    // } else {
    //     if (whiteList.indexOf(to.path) !== -1) {
    //         next()
    //         NProgress.done()
    //     } else {
    //         next(`/login?redireect=${to.path}`)
    //         NProgress.done()
    //     }
    // }
})

router.afterEach(() => {
    NProgress.done()
})