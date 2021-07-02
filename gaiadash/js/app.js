import Vue from 'vue';
import dash from './dash';
Vue.config.productionTip = false;

import '../css/app.css';

new Vue({
    el: '#vue-home',
    template: '<staff/>',
    components: { dash }
});
