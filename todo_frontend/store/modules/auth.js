import axios from 'axios';
import * as types from '../mutation-types'

const BASE_URL = 'http://localhost:8080/'
const SIGNIN_URL = BASE_URL + 'api/v1/auth/sign_in/'
const SIGNUP_URL = BASE_URL + 'api/v1/auth/'
const SIGNOUT_URL = BASE_URL + 'api/v1/auth/sign_out/'

const state = () => ({
  accessToken: null,
  client: null,
  uid: null,
  userName: null,
  userEmail: null,
  isLogged: false
})

const getters = {
  accessToken: (state) => state.accessToken,
  userName: (state) => state.userName,
  userEmail: (state) => state.userEmail,
  isLogged: (state) => state.isLogged
}

const mutations = {
  [types.ACCESS_TOKEN](state, { accessToken }) {
    state.accessToken = accessToken
  },

  [types.CLIENT](state, { client }) {
    state.client = client
  },

  [types.UID](state, { uid }) {
    state.uid = uid
  },

  [types.USER_NAME](state, { registeredName }) {
    state.userName = registeredName
  },

  [types.USER_EMAIL](state, { registeredEmail }) {
    state.userEmail = registeredEmail
  },

  [types.SIGNIN_USER](state, { authed }) {
    if (authed) {
      state.isLogged = false
    } else {
      state.isLogged = true
    }
  },
}

const actions = {

  async signup({ commit }, { name, email, password }) {
    const res = await axios.post(SIGNUP_URL, { name, email, password })
    const accessToken = res.headers['access-token']
    const client = res.headers['client']
    const uid = res.headers['uid']
    const registeredName = res.data.data.name
    const registeredEmail = res.data.data.email
    const authed = true

    await commit(types.ACCESS_TOKEN, { accessToken })
    await commit(types.CLIENT, { client })
    await commit(types.UID, { uid })
    await commit(types.USER_NAME, { registeredName })
    await commit(types.USER_EMAIL, { registeredEmail })
    await commit(types.SIGNIN_USER, { authed })
    window.localStorage.setItem('token', accessToken)
    window.localStorage.setItem('isLogged', authed)

  },

  async getToken() {
    return window.localStorage.getItem('token')
  },

  async signout({ commit }) {
    window.localStorage.removeItem('token')
  },

  async getAuth({ commit }) {
    return {'authed': window.localStorage.getItem('isLogged')}
  }


}

export default {
  state,
  getters,
  mutations,
  actions,

}
