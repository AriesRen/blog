import Vue from 'vue'
import Router from 'vue-router'
import Header from '../components/header'
import SideNav from '../components/sidenav'
import Dashbord from '../views/dashbord'

Vue.use(Router)


// export default new Router({
//   mode: 'hash',
//   base: process.env.BASE_URL,
//   routes: [
//     {
//       path: '/',
//       name: 'index',
//       components: {
//         sidenav: SideNav,
//         header: Header,
//         default: Dashbord
//       },
//       meta: {
//         title: 'Dashbord'
//       }
//     },
//     {
//       path: '/blogs',
//       name: 'blogs',
//       components: {
//         sidenav: SideNav,
//         header: Header,
//         default: () => import('../views/blogs')
//       },
//       meta: {
//         title: '所有文章'
//       }
//     },
//     {
//       path: '/about',
//       name: 'about',
//       // route level code-splitting
//       // this generates a separate chunk (about.[hash].js) for this route
//       // which is lazy-loaded when the route is visited.
//       component: () => import(/* webpackChunkName: "about" */ '../views/about.vue'),
//       meta: {
//         title: "关于"
//       }
//     }
//   ]
// })



export const constantRoutes = [
  {
    path: '/',
    name: 'index',
    components: {
      sidenav: SideNav,
      header: Header,
      default: Dashbord
    },
    meta: {
      title: 'Dashbord'
    }
  },
  {
    path: '/login',
    name: 'login',
    hiddren: true,
    component: () => import('../views/login')
  },
  {
    path: '/logout',
    name: 'logout',
    hiddren: true,
    component: () => import('../views/logout')
  },
  {
    path: '/404',
    name: '404',
    hiddren: true,
    component: () => import('@/components/404')
  },
  {
    path: '/blogs',
    name: 'blogs',
    components: {
      sidenav: SideNav,
      header: Header,
      default: () => import('../views/blogs')
    },
    meta: {
      title: '所有文章'
    }
  }
]

export const asyncRoutes = [
  {
    path: '/blogs',
    name: 'blogs',
    components: {
      sidenav: SideNav,
      header: Header,
      default: () => import('../views/blogs')
    },
    meta: {
      title: '所有文章'
    }
  }
]

const createRouter = () => new Router({
  mode: 'hash',
  scrollBehavior: () => ({ y: 0 }),
  base: process.env.BASE_URL,
  routes: constantRoutes
})

const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  Router.matcher = newRouter.matcher
}

export default router