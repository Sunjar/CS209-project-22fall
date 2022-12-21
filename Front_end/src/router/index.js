import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Vue from 'vue'

const routes = [
    {
        path: '/home',
        name: 'home',
        component: HomeView
    },
    {
        path: '/about',
        name: 'about',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    },
    {
        path:  '/repos',
        name: 'repos',
        component: () =>import('../views/RepoView.vue')
    },
    {
        path: '/test',
        name: 'test',
        component: () => import('../views/TestView.vue')
    },
    {
        path: '/crawler',
        name: 'crawler',
        component: () => import('../views/CrawlerView.vue')
    },
    {
        path: '/detail',
        name: 'detail',
        component: () => import('../views/DetailView.vue')
    },
    {
        path: '/comments',
        name: 'comments',
        component: () => import('../views/CommentView.vue')
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
