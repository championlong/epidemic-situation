import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/Home'
import Index from '@/views/Index'
import Login from '@/views/Login'
import Register from '@/views/Register'
import Log from '@/views/Map'
import MessageBoard from '@/views/Dynamic'


import {Message} from 'element-ui';


import store from '@/store'

import {getToken} from '@/request/token'

Vue.use(Router)

const router = new Router({
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '',
      name: 'Home',
      component: Home,
      children: [
        {
          path: '/index',
          component: r => require.ensure([], () => r(require('@/views/Index')), 'index')
        },
        {
          path: '/map',
          component: r => require.ensure([], () => r(require('@/views/Map')), 'map')
        },
        {
          path: '/dynamic',
          component: r => require.ensure([], () => r(require('@/views/Dynamic')), 'dynamic')
        }
      ]
    },
    {
      path: '/login',
      component: r => require.ensure([], () => r(require('@/views/Login')), 'login')
    },
    {
      path: '/register',
      component: r => require.ensure([], () => r(require('@/views/Register')), 'register')
    }

  ],
  scrollBehavior(to, from, savedPosition) {
    return {x: 0, y: 0}
  }
})

router.beforeEach((to, from, next) => {
  if (getToken()) {
    if (to.path === '/login') {
      next({path: '/'})
    } else {
      if (store.state.username.length === 0) {
        store.dispatch('getUserInfo').then(data => {//获取用户信息
          next()
        }).catch(() => {
          next({path: '/'})
        })
      } else {
        next()
      }
    }
  } else {
    if (to.matched.some(r => r.meta.requireLogin)) {
      Message({
        type: 'warning',
        showClose: true,
        message: '请先登录哦'
      })

    }
    else {
      next();
    }
  }
})


export default router
