import Vue from 'vue'
import Router from 'vue-router';
import Login  from  '../components/Login';
import Registered from '../components/Registered'
import retrievepassword from '../components/RetrievePassword'
 
import index from '../components/index'
import adminindex from '../components/EvaluationBrowse'
import uevluationlist from '../components/EvaluationList'

Vue.use(Router);

export default new Router({
    mode: 'history',
    routes:[
        {
            path: '/pu/user/EvaluationList',
            component: uevluationlist,   
        },
        {
            path: '/pu/user/retrievepassword',
            component: retrievepassword,
        },
        {
            path: '/',
            component: index,
        },
        {
            path: '/admin',
            component: adminindex,
        },
        //login
        {
            path: '/pu/user/login',
            component: Login,
        },
        //register
        {
            path: '/pu/user/registered',
            component: Registered,
        }
    ]
})