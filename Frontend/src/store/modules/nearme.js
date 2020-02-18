const state = {
    cafes: []
}
const mutations = {
	setCafes(state, [index, val]){
		this.set(state.cafes, index, val)
	}
}

const actions = {
}

const getters = {
	filteredCafes: state => search => state.cafes.filter(cafe => cafe.cafe_name.includes(search))
}

export default {
    state,
    mutations,
    actions,
    getters
}