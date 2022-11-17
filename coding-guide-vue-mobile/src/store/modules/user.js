import Vue from 'vue'
import { getCurrentUserInfo } from '../../api/user'
import {
    refreshToken
} from '../../api/refreshToken'

const state = {
    userName: '', //用户帐号
    nickName: '', //用户昵称
    avatar: '',//当前用户头像地址
    accessToken: localStorage.getItem('accessToken'),
    refreshToken: localStorage.getItem('refreshToken')
}

const mutations = {
    SET_USERNAME: (state, userName) => {
        state.userName = userName;
    },
    SET_NICKNAME: (state, nickName) => {
        state.nickName = nickName
    },
    SET_AVATAR: (state, avatar) => {
        state.avatar = avatar
    },
    SET_ACCESSTOKEN: (state, accessToken) => {
        state.accessToken = accessToken
    },
    SET_REFRESHTOKEN: (state, refreshToken) => {
        state.refreshToken = refreshToken
    }
}

const actions = {

    //用户登录成功action
    loginSuccess(context, data) {
        //将用户基本信息存储到vuex中，这部分的数据会随着刷新页面而丢失
        context.commit('SET_USERNAME', data.data.userName)
        context.commit('SET_NICKNAME', data.data.nickName);
        context.commit('SET_AVATAR', data.data.avatar);
        //将token到localstorage中进行持久化，因为vuex的数据是没有持久化效果的，刷新页面就会丢失，所以要放到localstorage中
        localStorage.setItem('accessToken', data.data.accessToken)
        localStorage.setItem('refreshToken', data.data.refreshToken)

        //点击登录后还需要执行一次手动把token放到vuex中（这步操作只在login操作执行一次即可）
        context.commit('SET_ACCESSTOKEN', data.data.accessToken);
        context.commit('SET_REFRESHTOKEN', data.data.refreshToken);
    },
    getCurrentUserInfo(context) {
        return new Promise((resolve, reject) => {
            getCurrentUserInfo().then((res) => {
                if (res.data.code === 200) {
                    context.commit('SET_USERNAME', res.data.data.userName);
                    context.commit('SET_NICKNAME', res.data.data.nickName);
                    context.commit('SET_AVATAR', res.data.data.avatar);
                    resolve(res);
                } else if (res.data.code === 301) {
                    //这里不做任何事，为了就是防止下一个else将token全部清掉，因为code=301是刷新token的编码，而不需要被清空数据
                }
                else {
                    //防止accessToken和refreshToken过期了
                    //而这两个token会一直存在localstorage，需要客户手动删除才可以进入/login页面
                    //（因为我们设置了路由守卫，一旦有token在localstorage中就无法访问login页面的配置）
                    //所以当获取不到用户信息则要清空这些数据（特别是两个token数据）
                    //清空VUEX内容
                    context.commit('SET_USERNAME', '')
                    context.commit('SET_NICKNAME', '');
                    context.commit('SET_AVATAR', '');
                    context.commit('SET_ACCESSTOKEN', '');
                    context.commit('SET_REFRESHTOKEN', '');
                    //清空localstorage的accessToken和refreshToken
                    localStorage.removeItem('accessToken')
                    localStorage.removeItem('refreshToken')
                    resolve(res);
                }

            }).catch((err) => {
                //防止accessToken和refreshToken过期了
                //而这两个token会一直存在localstorage，需要客户手动删除才可以进入/login页面
                //（因为我们设置了路由守卫，一旦有token在localstorage中就无法访问login页面的配置）
                //所以当获取不到用户信息则要清空这些数据（特别是两个token数据）
                //清空VUEX内容
                context.commit('SET_USERNAME', '')
                context.commit('SET_NICKNAME', '');
                context.commit('SET_AVATAR', '');
                context.commit('SET_ACCESSTOKEN', '');
                context.commit('SET_REFRESHTOKEN', '');
                //清空localstorage的accessToken和refreshToken
                localStorage.removeItem('accessToken')
                localStorage.removeItem('refreshToken')
                reject(err);
            })
        })
    },
    //刷新token
    toRefreshToken(context) {

        refreshToken().then(res => {
            //将token到localstorage中进行持久化，因为vuex的数据是没有持久化效果的，刷新页面就会丢失，所以要放到localstorage中
            localStorage.setItem('accessToken', res.data.data.accessToken)
            localStorage.setItem('refreshToken', res.data.data.refreshToken)

            //点击登录后还需要执行一次手动把token放到vuex中（这步操作只在login操作执行一次即可）
            context.commit('SET_ACCESSTOKEN', res.data.data.accessToken);
            context.commit('SET_REFRESHTOKEN', res.data.data.refreshToken);
        }).catch(err => { //如果刷新token失败，则清空所有数据
            context.commit('SET_USERNAME', '')
            context.commit('SET_NICKNAME', '');
            context.commit('SET_AVATAR', '');
            context.commit('SET_ACCESSTOKEN', '');
            context.commit('SET_REFRESHTOKEN', '');
            //清空localstorage的accessToken和refreshToken
            localStorage.removeItem('accessToken')
            localStorage.removeItem('refreshToken')
        })


    }
}


export default {
    state,
    mutations,
    actions
}