import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: localStorage.getItem("token"),
        userInfo: JSON.parse(localStorage.getItem("userInfo")),
        // userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            localStorage.setItem("userInfo", JSON.stringify(userInfo))
            // sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem("token", '')
            localStorage.setItem("userInfo", JSON.stringify(''))
            // sessionStorage.setItem("userInfo", JSON.stringify(''))
        }
    },
    getters: {
        // get
        getUser: state => {
            console.log("getUser:" + JSON.stringify(state));
            return state.userInfo
        },
        getHasLogin: state => {
            return state.token !== ''
        }
    },
    actions: {},
    modules: {}
})