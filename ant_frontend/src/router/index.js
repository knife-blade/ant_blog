import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import BlogDetail from '../views/BlogDetail.vue'
import BlogEdit from '../views/BlogEdit.vue'
import Blogs from "@/views/Blogs";

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: { name: 'Blogs' }
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: {
      notRequireAuth: true
    }
  },
  {
    path: '/blogs',
    name: 'Blogs',
    // 懒加载
    component: () => import('../views/Blogs.vue'),
    meta: {
      notRequireAuth: true
    }
  },
  {
    path: '/blog/add', // 注意放在 path: '/blog/:blogId'之前
    name: 'BlogAdd',
    component: BlogEdit,
  },
  {
    path: '/blog/:blogId',
    name: 'BlogDetail',
    component: BlogDetail,
    meta: {
      notRequireAuth: true
    }
  },
  {
    path: '/blog/:blogId/edit',
    name: 'BlogEdit',
    component: BlogEdit
  }
];

const router = new VueRouter({
  // mode: 'history',
  base: process.env.BASE_URL,
  routes
})
export default router