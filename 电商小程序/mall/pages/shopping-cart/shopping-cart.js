Page({

  /**
   * 页面的初始数据
   */
  data: {
    selectilall: false,
    totalPrice:0
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
    var that = this;
    var userVO = wx.getStorageSync('userkey')
    wx.request({
      url: 'https://www.8935758.xyz/get/user/shoppingcart/info.json',
      header:{
        'content-Type':'application/json'
      },
      method:'GET',
      data:{
        nickName:userVO.nickName
      },
      success:function(res){
        console.log(res)
        if(res.statusCode!=200 || res.data==null){
          wx.showToast({
            title: '请先登录',
            icon:'error',
            duration:1500
          })
        }else{
          console.log(res.data)
          that.setData({
            list:res.data,
          })
        }
      }
    })
  },
  //选择事件
  selectList:function(e) {
    var index = e.currentTarget.dataset.index;
    var list = this.data.list;
    var sumprice = e.currentTarget.dataset.sumprice
    console.log(sumprice);
    let newli = 'list[' + index + '].checked';
  console.log(list[index].checked)
  this.setData({
    [newli]: !list[index].checked,
   })
    var num = 0;
    var totalPrice = 0;
    for(var i = 0;i<list.length;i++){
      if(list[i].checked){
        num++;
        totalPrice+=list[i].sumprice;
      }
    }
    console.log(totalPrice)
    this.setData({
      totalPrice:totalPrice
    })
    if(num == list.length){
      this.setData({
        selectilall:true
      })
    }else{
      this.setData({
        selectilall:false
      })
    }
  },
  //全选事件
  selectAll:function(e) {
  var totalPrice = 0;
  var list = this.data.list;
  let selectilall = this.data.selectilall;
  if (selectilall == false) {
   for (let i = 0; i < list.length; i++) {
    let newli = 'list[' + i + '].checked';
    totalPrice+=list[i].sumprice
    this.setData({
     [newli]: true,
     selectilall: true,
     totalPrice:totalPrice
    })
   }
  } else {
   for (let i = 0; i < list.length; i++) {
    let newli = 'list[' + i + '].checked';
    this.setData({
     [newli]: false,
     selectilall: false,
     totalPrice:0
    })
   }
  }
  },
  //删除商品
  deleteList(e) {
    var that = this;
    wx.showModal({
      title: '提示',
      content: '确定要删除该商品？',
      success (res) {
      if (res.confirm) {
      var cartid = e.target.dataset.id;
      wx.request({
        url: 'https://www.8935758.xyz/remove/user/shoppingcart/info.json',
        header:{
          'content-Type':'application/json'
        },
        method:'GET',
        data:{
          id:cartid
        },
        success:function(res){
          console.log(res.data);
          if(res.statusCode!=200||res.data.length == 'fail'){
            wx.showToast({
              title: '删除失败',
              icon:'error',
              duration:1500
            })
          }else{
            wx.showToast({
              title: '删除成功',
              icon:'success',
              duration:1500
            })
            that.onShow();
          }
        }
      })
      } else if (res.cancel) {
      
      }
      }
      })
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
  },
  //付款
  toBuy(){
    if(this.data.totalPrice == 0){
      wx.showToast({
        title: '请选择商品',
        icon:'error',
        duration:1500
      })
    }else{
     wx.showToast({
       title: '暂未开通',
       icon:'error',
       duration:1500
     })
    }
  }
})