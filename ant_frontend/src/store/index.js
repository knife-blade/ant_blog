import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        token: '',
        // userInfo: JSON.parse(sessionStorage.getItem("userInfo")),
        userInfo: JSON.parse(localStorage.getItem("userInfo")),
    },
    mutations: {
        // set
        SET_TOKEN: (state, token) => {
            state.token = token
            localStorage.setItem("token", token)
        },
        SET_USERINFO: (state, userInfo) => {
            state.userInfo = userInfo
            // sessionStorage.setItem("userInfo", JSON.stringify(userInfo))
            localStorage.setItem("userInfo", JSON.stringify(userInfo))
        },
        REMOVE_INFO: (state) => {
            state.token = ''
            state.userInfo = {}
            localStorage.setItem("token", '')
            // sessionStorage.setItem("userInfo", JSON.stringify(''))
            localStorage.setItem("userInfo", JSON.stringify(''))
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