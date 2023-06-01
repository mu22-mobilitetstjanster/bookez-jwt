import BookView from '@/views/BookView.vue';
import LoginView from '@/views/LoginView.vue';
import { createRouter, createWebHistory } from 'vue-router'

import SigninView from '@/views/login/SigninView.vue';
import SignupView from '@/views/login/SignupView.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/login',
      name: 'login',
      component: LoginView,
      children: [
        {
          path: '',
          name: 'signin',
          component: SigninView
        },
        {
          path: 'signup',
          name: 'signup',
          component: SignupView
        }
      ]
    },
    {
      path: '/library',
      name: 'library',
      component: BookView
    },
  ]
})

export default router
