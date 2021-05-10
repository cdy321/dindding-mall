// pages/findings/findings.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  searchGoods(){
    wx.navigateTo({
      url: '../search/search',
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'https://www.8935758.xyz/get/goods/list.json',
      header:{
        "content-Type":"application/json"
      },
      method:"GET",
      data:{
      },
      success:function(res){
        console.log(res.data);
        if(res.data.length == 0||res.statusCode != 200){
          wx.showToast({
            title: '未加载到数据',
            icon:'error',
            duration:'1500'
          })
        }else{
          that.setData({
            list:res.data
          })
        }
      }
    })
  },
  //跳转到详情页面
  redirectGoodsdetail:function(e){
    var id = e.target.dataset.id;
    console.log(id);
    wx.request({
      url: 'https://www.8935758.xyz/get/goods/detail.json?id='+id,
      header:{
        'content-Type':'application/json'
      },
      method:'GET',
      data:{

      },
      success:function(res){
        console.log(res.data);
        if(res.statusCode != 200 || res.data.length == 0){
          wx.showToast({
            title: '没有数据',
            icon:'error',
            duration:1500
          })
        }else{
          wx.setStorageSync('key', res.data);
          wx.navigateTo({
            url: '../goods-detail/goods-detail'
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