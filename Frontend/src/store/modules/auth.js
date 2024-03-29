// state : data와 유사 
const state = {
    token: null
}
// mutations : state를 변화시키기 위한 메서드(함수)
const mutations = {
    // 첫번째 인자는 state
    // 이후 인자는 payload(즉, 그냥 매개변수)
    setToken(state, token) {
        state.token = token
    }
}

const actions = {
    // 첫번째 인자는 context(다양한)
    // 이후 인자는 payload(임의의 매개 변수)
    login(context, token) {
        // mutation 호출 -> commit
        context.commit('setToken', token)
    },
    logout(context) {
        context.commit('setToken', null)
    }
}
import jwtDecode from 'jwt-decode'
const getters = {
    options(state) {
        return {
            headers: {
                Authorization: `JWT ${state.token}`
            }
        }
    },
    user(state) {
        return jwtDecode(state.token).sub
    },
    isLoggedIn: state => !!state.token,
}
export default {
    state,
    mutations,
    actions,
    getters
}