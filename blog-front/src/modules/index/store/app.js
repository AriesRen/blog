const app = {
    state: {
        toobarTitle: '',
        toobarShow: false
    },
    mutations: {
        SET_TOOBARTITLE: (state, toobarTitle) => {
            state.toobarTitle = toobarTitle;
        },
        SET_TOOBARSHOW: (state, show) => {
            state.toobarShow = show
        }
    },
    actions: {
        ShowToobarTitle({commit, state}, title) {
            commit('SET_TOOBARSHOW', true)
            commit('SET_TOOBARTITLE', title)
        },
        HiddenToobarTitle({commit, state}) {
            commit('SET_TOOBARSHOW', false)
        }
    }
}

export default app