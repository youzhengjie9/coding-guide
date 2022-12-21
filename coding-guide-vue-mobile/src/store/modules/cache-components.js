const state = {

    //缓存组件名列表
    cacheComponentNameList: ['Layout']

}

const mutations = {

    ADD_CACHE_COMPONENT_NAME(state,componentName) {
        state.cacheComponentNameList.push(componentName)
    },
    REMOVE_CACHE_COMPONENT_NAME(state, componentName) {
        const index = state.cacheComponentNameList.indexOf(componentName)
        if (index !== -1) {
          state.cacheComponentNameList.splice(index, 1)
        }
    },

}

const actions = {
    addCacheComponentName(context,componentName){
        context.commit('ADD_CACHE_COMPONENT_NAME',componentName)
    },
    removeCacheComponentName(context,componentName){
        context.commit('REMOVE_CACHE_COMPONENT_NAME',componentName)
    },
}

export default {
    state,
    mutations,
    actions
};