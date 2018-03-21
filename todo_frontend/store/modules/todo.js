import * as types from '../mutation-types'
import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/v1/todos/'

const state = () => ({

})

const getters = {

}

const actions = {

  async getTodos({commit}) {
    const todos = await axios.get(BASE_URL)
    console.log(todos)
  }

}

const mutations = {

}

export default {
  state,
  getters,
  actions,
  mutations
}
