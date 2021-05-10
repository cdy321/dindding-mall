// pages/mall-main/mall-main.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    //轮播图数据
    swiperCurrent:0,
    indicatorDots: true,
    autoplay: true,
    interval: 3000,
    duration: 800,
    circular:true,
    imgUrls:[
      '../index/img/ad1.jpg',
      '../index/img/ad2.jpg',
      '../index/img/ad3.jpg'
    ],
    links:[
    ],
    //商品展示数据
  },
  //轮播图的切换事件
  swiperChange:function(e){
    this.setData({
      swiperCurrent: e.detail.current
    })
  },
  //点击指示点切换
  chuangEvent: function (e) {
    this.setData({
      swiperCurrent: e.currentTarget.id
    })
  },
  //点击图片触发事件
  swipclick: function (e) {
    var id = this.data.swiperCurrent+1;
    console.log(id);
   wx.request({
     url: 'https://www.8935758.xyz/get/goods/detail.json?id='+id,
     header:{
       "content-Type":'application/json'
     },
     method:'GET',
     data:{

     },
     success:function(res){
       console.log(res.data);
       if(res.statusCode!=200 || res.data.length == 0){
         wx.showToast({
           title: '没有数据',
           icon:'error',
           duration:1500
         })
       }else{
        wx.setStorageSync('key', res.data)
        wx.navigateTo({
          url: '../goods-detail/goods-detail'
        })
       }
     }

   })
  },
  //搜索商品
  searchGoods(){
    wx.navigateTo({
      url: '../search/search',
    })
  },
  //页面跳转
  shoesBtn(){
    wx.navigateTo({
      url: '../shoes/shoes',
    })
  },
  electricBtn(){
    wx.navigateTo({
      url: '../electric/electric',
    })
  },
  fruitBtn(){
    wx.navigateTo({
      url: '../fruit/fruit',
    })
  },
  medicineBtn(){
    wx.navigateTo({
      url: '../medicine/medicine',
    })
  },
  hometoolBtn(){
    wx.navigateTo({
      url: '../hometool/hometool',
    })
  },
  vegitableBtn(){
    wx.navigateTo({
      url: '../vegitable/vegitable',
    })
  },
  animalBtn(){
    wx.navigateTo({
      url: '../animal/animal',
    })
  },
  familyBtn(){
    wx.navigateTo({
      url: '../family/family',
    })
  },
  doTest(){
    wx.navigateTo({
      url: '../test/test',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: 'https://www.8935758.xyz/get/goods/list/bySaleNum.json',
      method:'GET',
      data:{
      },
      header:{
        'contentType':'application/json'
      },
      success(res){
        console.log(res.data);
        if(res.data == null){
          var toastText = '没有数据';
          wx.showToast({
            title: toastText,
            icon:'none',
            duration: 2000
          })
        }else{
          that.setData({
            list:res.data
          })
        }
      }
    })
  },
  //跳转到商品详情页
  redirectGoodsdetail:function(e){
    var id = e.currentTarget.dataset.id;
    console.log(id)
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