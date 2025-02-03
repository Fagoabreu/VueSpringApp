import { createApp } from 'vue'
import { createRouter, createWebHistory } from 'vue-router';
import { vMaska } from "maska/vue"
import { Money3Directive } from "v-money3";


import App from './App.vue'
import axios from 'axios'
import VueDatePicker from '@vuepic/vue-datepicker';

import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import '@vuepic/vue-datepicker/dist/main.css'

import CreateTransfer from './components/pages/transfer/CreateTransfer.vue';
import UpdateTransfer from './components/pages/transfer/UpdateTransfer.vue';
import TransfersOverview from './components/pages/transfer/TransfersOverview.vue';
import TransferDetail from './components/pages/transfer/TransferDetail.vue';

axios.defaults.baseURL = process.env.VUE_APP_API_BASE_URL;

const router = createRouter({
    history: createWebHistory(),
    routes: [
        { path: '/', component: TransfersOverview },
        { path: '/transfer', component: TransfersOverview },
        { path: '/transfer/detail/:id', component: TransferDetail },
        { path: '/transfer/create', component: CreateTransfer },
        { path: '/transfer/edit/:id', component: UpdateTransfer }
    ]
});

const app = createApp(App);
app
    .use(router)
    .directive("money3", Money3Directive)
    .directive("maska", vMaska)
    .component('VueDatePicker', VueDatePicker)
    .mount('#app');


