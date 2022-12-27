import Vue from 'vue'
import { getCurrentUserInfo } from '../../api/user'
import {
    refreshToken
} from '../../api/refresh-token'
import {
    selectCurUserFollowUserIdList
} from '@/api/user-follow'

const state = {
    userName: '', //用户帐号
    nickName: '', //用户昵称
    avatar: '',//当前用户头像地址
    accessToken: localStorage.getItem('accessToken'),
    refreshToken: localStorage.getItem('refreshToken'),
    userFollowIdList: [] //当前用户关注的所有用户的id集合
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
    },
    SET_USER_FOLLOW_ID_LIST: (state, userFollowIdList) => {
        state.userFollowIdList = userFollowIdList
    },
    //关注用户（将publisherId添加到数组中）
    FOLLOW_USER(state,publisherId) {
        state.userFollowIdList.push(publisherId)
    },
    //取消关注用户（从数组中移除指定publisherId元素）
    CANCEL_FOLLOW_USER(state,publisherId) {
        for (var i in state.userFollowIdList) {
            if (state.userFollowIdList[i] == publisherId) {
                state.userFollowIdList.splice(i, 1);
            }
        }
    },
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

        //加载当前用户关注的所有用户的id集合
        actions.loadCurrentUserFollowIdList(context);
    },
    //退出成功
    logoutSuccess(context) {
        //此时，后端已经成功将用户退出，前端只需要把token和用户信息清空即可（localstorage内容也要清空）
        //清空VUEX内容
        context.commit('SET_USERNAME', '')
        context.commit('SET_NICKNAME', '');
        context.commit('SET_AVATAR', '');
        context.commit('SET_ACCESSTOKEN', '');
        context.commit('SET_REFRESHTOKEN', '');
        context.commit('SET_USER_FOLLOW_ID_LIST', []);
        //清空localstorage的accessToken和refreshToken
        localStorage.removeItem('accessToken')
        localStorage.removeItem('refreshToken')
    },
    //加载当前用户关注的所有用户的id集合
    loadCurrentUserFollowIdList(context) {

        //如果userFollowIdList为空才去后端查询关注列表,防止一直请求后端
        if (state.userFollowIdList.length == 0) {
            selectCurUserFollowUserIdList().then(res => {
                context.commit('SET_USER_FOLLOW_ID_LIST', res.data.data);
            })
        }

    },
    //关注用户
    followUser(context, publisherId) {
        context.commit('FOLLOW_USER', publisherId)
    },
    //取消关注用户
    cancelFollowUser(context, publisherId) {
        context.commit('CANCEL_FOLLOW_USER', publisherId)
    },
    getCurrentUserInfo(context) {
        return new Promise((resolve, reject) => {
            getCurrentUserInfo().then((res) => {
                if (res.data.code === 200) {
                    context.commit('SET_USERNAME', res.data.data.userName);
                    context.commit('SET_NICKNAME', res.data.data.nickName);
                    context.commit('SET_AVATAR', res.data.data.avatar);
                    actions.loadCurrentUserFollowIdList(context);
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
                    context.commit('SET_USER_FOLLOW_ID_LIST', []);
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
                context.commit('SET_USER_FOLLOW_ID_LIST', []);
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
            context.commit('SET_USER_FOLLOW_ID_LIST', []);
            //清空localstorage的accessToken和refreshToken
            localStorage.removeItem('accessToken')
            localStorage.removeItem('refreshToken')
        })
    },
    //修改用户头像
    setAvatar(context,newAvatar){
        context.commit('SET_AVATAR',newAvatar)
    }

}


export default {
    state,
    mutations,
    actions
}