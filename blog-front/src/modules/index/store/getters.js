const getters = {
    token: state => state.user.token,
    nickname: state => state.user.nickname,
    username: state => state.user.username,
    userId: state => state.user.userId,
    roles: state => state.user.roles,
    permissions: state => state.user.permissions,
    addRouters: state => state.permission.addRouters,
    permission_routers: state => state.permission.routes,
    toobarTitle: state => state.app.toobarTitle,
    toobarShow: state => state.app.toobarShow
}

export default getters