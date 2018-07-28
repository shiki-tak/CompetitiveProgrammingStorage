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
    const res = await axios.get(BASE_URL)
    const todos = res.data
    commit(types.SET_TODOS, { todos })
    return { 'data': todos }
  },

  async createTodo({ commit }, { todo }) {
    const res = await axios.post(BASE_URL, { todo })
    const res_todo = res.data
    return { 'todo': res_todo }
  }
}

const mutations = {
  [types.SET_TODOS](state, { todos }) {
    state.todos = todos
  }
}

export default {
  state,
  getters,
  actions,
  mutations
}
