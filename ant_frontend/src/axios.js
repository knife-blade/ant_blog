import axios from 'axios'
import Element from 'element-ui'
import router from './router'
import store from './store'
import Auth from "@/common/Auth";

// axios.defaults.baseURL = "http://localhost:8081"

// 请求拦截
axios.interceptors.request.use(config => {
    // 统一设置请求参数
    if (store.state.token) {
        config.headers[Auth.HEADER_NAME] = store.state.token
    }
    return config;
})

// 响应拦截
axios.interceptors.response.use(response => {
        let res = response.data;

        if (res.code === 1000) {
            return response
        } else {
            Element.Message.error('失败：' + response.data.msg);
            return response;
        }
    },
    error => {
        console.log(error)
        Element.Message.error('失败：' + error.response.data.message);
        if (error.response.data) {
            error.message = error.response.data.msg
        }

        if (error.response.status === 401) {
            store.commit("REMOVE_INFO")
            router.push("/login")
        }

        Element.Message.error(error.message)
        return Promise.reject(error)
    }
)