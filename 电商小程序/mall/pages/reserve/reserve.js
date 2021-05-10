// pages/reserve/reserve.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  todetails:function(e){
    var id = e.target.dataset.id;
    console.log(id);
    wx.request({
      url: 'https://www.8935758.xyz/get/goods/detail.json?id='+id,
      header:{
        "content-Type":'appliaction/json'
      },
      method:'GET',
      data:{

      },
      success:function(res){
        console.log(res.data)
        if(res.statusCode != 200 || res.data.length == 0){
          wx.showToast({
            title: '没有数据!',
            icon:'error',
            duration:1500
          })
        }else{
          wx.setStorageSync('key',res.data);
          wx.navigateTo({
            url: '../goods-detail/goods-detail',
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that  = this;
    var goodsVO = wx.getStorageSync('key');
    var userVO = wx.getStorageSync('userkey');
    var nickName = userVO.nickName;
    console.log(nickName);
    wx.request({
      url: 'https://www.8935758.xyz/get/user/reserve/info.json',
      header:{
        'content-Type':'application/json'
      },
      method:'GET',
      data:{
        nickName:userVO.nickName
      },
      success:function(res){
        console.log(res.data);
        if(res.statusCode != 200||res.data.length == 0){
          wx.showToast({
            title: '请先登录',
            icon:'error',
            duration:1500
          })
        }else{
          wx.showToast({
            title: '加载中..',
            icon:'loading',
            duration:1500
          })
          that.setData({
            list:res.data
          })
        }
      }
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})