const state = {
    // SERVER: "https://192.168.31.142/v1",
    SERVER: "https://i02b106.p.ssafy.io/v1",
    // SERVER: "https://192.168.31.111:443/v1",
    // SERVER: "http://13.125.168.55:3000/v1",
}
const mutations = {
}

const actions = {
}

const getters = {
    colors() {
        return {    
            black: "#555B6E",
            mdark: "#89B0AE",
            mlight: "#BEE3DB",
            beige: "#FAF9F9",
            orange: "#FFD6BA"
        }
    },
}

export default {
    state,
    mutations,
    actions,
    getters
}