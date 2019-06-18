import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/index'
import Header from '../components/header'
import Footer from '../components/footer'

Vue.use(Router)

export default new Router({
  mode: 'hash',
  base: process.env.BASE_URL,
  scrollBehavior(to, from, savePosition) {
    if (savePosition) {
      return savePosition
    } else {
      return {x:0, y: 0}
    }
  },
  routes: [
    {
      path: '/',
      name: 'index',
      components: {
        header: Header,
        default: Index,
        footer: Footer
      },
      meta: {
        title: '无名万物-首页'
      }
    },
    {
      path: '/about',
      name: 'about',
      components: {
        header: Header,
        default: () => import(/* webpackChunkName: "about" */ '../views/about.vue'),
        footer: Footer
      },
      meta: {
        title: '关于我'
      }
    },
    {
      path: '/login',
      name: 'login',
      components: {
        default: () => import('../views/login'),
      },
      meta: {
        title: '登录'
      }
    },
    {
      path: '/post/:id',
      name: 'post-detail',
      components: {
        header: Header,
        default: () => import('../views/post-detail'),
        footer: Footer
      },
      meta: {
        title: '无名万物-文章详情'
      }
    },
    {
      path: '/category',
      name: 'category',
      components: {
        header: Header,
        default: () => import('../views/category'),
        footer: Footer
      },
      meta: {
        title: '无名万物-分类'
      }
    },
    {
      path: '/tags',
      name: 'tags',
      components: {
        header: Header,
        default: () => import('../views/tags'),
        footer: Footer
      },
      meta: {
        title: '无名万物-标签'
      }
    },
    {
      path: '/archive',
      name: 'archive',
      components: {
        header: Header,
        default: () => import('../views/archive'),
        footer: Footer
      },
      meta: {
        title: '无名万物-归档'
      }
    },
    {
      path: '/tags/:tag',
      name: 'tags-defail',
      components: {
        header: Header,
        default: () => import('../components/tags/tags-detail'),
        footer: Footer
      },
      meta: {
        title: `无名万物-标签`
      }
    },
    {
      path: '/category/:category',
      name: 'category-defail',
      components: {
        header: Header,
        default: () => import('../components/category/category-detail'),
        footer: Footer
      },
      meta: {
        title: `无名万物-分类`
      }
    },
    {
      path: '/forget',
      name: 'forget',
      components: {
        default: () => import('../views/forget'),
      },
      meta: {
        title: `无名万物找回密码`
      }
    },
    {
      path: '/forgetmail',
      name: 'forgetmail',
      components: {
        default: () => import('../components/forget2mail'),
      },
      meta: {
        title: `无名万物找回密码`
      }
    },
    {
      path: '/register',
      name: 'register',
      components: {
        default: () => import('../views/register'),
      },
      meta: {
        title: `无名万物-注册`
      }
    },
    {
      path: '/friends',
      name: 'friends',
      components: {
        header: Header,
        default: () => import('../views/friends'),
        footer: Footer
      },
      meta: {
        title: `无名万物-友链`
      }
    }
  ]
})