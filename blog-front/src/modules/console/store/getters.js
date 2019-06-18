const getters = {
    nickname: state => state.user.nickname,
    username: state => state.user.username,
    userId: state => state.user.userId,
    roles: state => state.user.roles,
    permissions: state => state.user.permissions,
    addRouters: state => state.permission.addRouters,
    permission_routers: state => state.permission.routes,
}

export default getters