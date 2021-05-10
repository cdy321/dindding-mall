// pages/search/search.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  search: function(e){
    if(e.detail.value.content.length == 0){
      wx.showToast({
        title: '输入不能为空!',
        icon:'error',
        duration:1500
      })
    }else{
      console.log(e.detail.value.content)
      wx.request({
        url: 'https://www.8935758.xyz/search/goods/info.json?content='+e.detail.value.content,
        header: {
          "content-Type":"application/json"
        },
        method:"POST",
        data:{
          content:e.detail.value.content
        },
        success:function(res){
          console.log(res.data);
          if(res.statusCode != 200 || res.data.length == 0){
            wx.showToast({
              title: '搜索失败!',
              icon:"error",
              duration:1500
            })
          }else{
            wx.setStorage({
              key:"key",
              data:res.data
            })
            wx.navigateTo({
              url: '../goods-info/goods-info',
            })
            wx.showToast({
              title: '搜索成功!',
              icon:"success",
              duration:1000
            })
          }
        }
      })
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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