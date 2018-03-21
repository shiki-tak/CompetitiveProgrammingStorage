import * as types from '../mutation-types'
import axios from 'axios'

const BASE_URL = 'http://localhost:8080/api/v1/todos/'

const state = () => ({
  todos: []
})

const getters = {
  todos: (state) => state.todos
}

const actions = {

  async getTodos({commit}) {
    const todos = await axios.get(BASE_URL)
    commit(types.TODOS, { todos })
  }

}

const mutations = {
  [types.TODOS](state, { todos }) {
    state.todos = todos
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
