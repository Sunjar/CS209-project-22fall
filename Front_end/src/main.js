import { createApp } from 'vue'

import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus';
import * as echarts from 'echarts'
import 'element-plus/dist/index.css';
import './style/headtap.css'

createApp(App).use(store).use(router).use(ElementPlus).use(echarts).mount('#app')
// createApp(App).use(store).use(router).use(ElementPlus).mount('#app')