// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canIUseGetUserProfile: false,
    canIUseOpenData: false // 如需尝试获取用户信息可改为false
  },
  // 事件处理函数
  onLoad() {
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
  },
  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        console.log(res.userInfo)
        //向数据库中添加用户数据
        var that = this;
        wx.request({
          url: 'https://www.8935758.xyz/add/user/info.json',
          header:{
            'content-type':'application/json'
          },
          method:'POST',
          data:{
            nickName:res.userInfo.nickName,
            gender:res.userInfo.gender,
            language:res.userInfo.language,
            city:res.userInfo.city,
            province:res.userInfo.province,
            avatarUrl:res.userInfo.avatarUrl,
            country:res.userInfo.country
          },
          success:function(res1){
            console.log(res1.data);
            if(res1.statusCode != 200 || res1.data == null){
              wx.showToast({
                title: '用户信息添加失败',
                icon:'error',
                duration:1500
              })
            }else{
              console.log(res1.statusCode);
              wx.setStorageSync('userkey',res.userInfo);
            }
          }
        })
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    })
  },
  //跳转购物车
  toCart(){
    wx.switchTab({
      url: '../shopping-cart/shopping-cart',
    })
  },
  toAddress(){
    wx.navigateTo({
      url: '../address/address',
    })
  },
  toReserve(){
    wx.navigateTo({
      url: '../reserve/reserve',
    })
  },
  toComments(){
    wx.navigateTo({
      url: '../comments/comments',
    })
  },
  toPropaganda(){
    wx.navigateTo({
      url: '../propaganda/propaganda',
    })
  },
  toOrder(){
    wx.navigateTo({
      url: '../order/order',
    })
  },
  getUserInfo(e) {
    // 不推荐使用getUserInfo获取用户信息，预计自2021年4月13日起，getUserInfo将不再弹出弹窗，并直接返回匿名的用户个人信息
    console.log(e)
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})