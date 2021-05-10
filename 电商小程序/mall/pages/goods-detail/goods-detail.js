// pages/goods-detail/goods-detail.js
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    isLike: false,
    //商品数量
    goodsNumber: 1,
    minusStatus: 'disabled',
    //遮罩层显示状态
    mask: true,
    //购物车弹窗显示隐藏
    cartBox: true,
  },
  //预览图片
  previewImage:function(e){
    var imgUrls = e.target.dataset.path;
    console.log(imgUrls);
    //构造img数组
    var imgArray=[1];
    for(var i =0;i < 1;i++){
      imgArray[i] = imgUrls;
    }
  wx.previewImage({
    urls: imgArray,
    current: imgUrls,
    showmenu: true,
  })
},
// 收藏
addLike() {
  if(this.data.isLike == false){
    var that = this;
  wx.showModal({
    title:'收藏夹',
    content:'确定要加入收藏夹?',
    showCancel:true,
    cancelText:"否",
    cancelColor: 'cancelColor',
    confirmText:"是",//默认是“确定”
         confirmColor: 'skyblue',//确定文字的颜色
         success: function (res) {
            if (res.cancel) {
               //点击取消,默认隐藏弹框
            } else {
               //点击确定
               that.setData({
                isLike: !that.data.isLike
              });
             var userVO = wx.getStorageSync('userkey');
             var goodsVO = wx.getStorageSync('key');
              //加入收藏夹
             wx.request({
               url: 'https://www.8935758.xyz/add/user/reserve/info.json',
               header:{
                         'content-Type':'application/json'
                    },
               method:'POST',
               data:{
                 goodsVO:goodsVO,
                 userVO:userVO
                },
                success:function(res){
                  console.log(res.data);
                  if(res.statusCode!=200||res.data == 'fail'){
                    wx.showToast({
                      title: '您已收藏',
                      icon:'error',
                      duration:1500
                    })
                  }else{
                    wx.showToast({
                      title: '添加成功',
                      icon:'success',
                      duration:1500
                    })
                    console.log(res.data)
                  }
                }
            })
            }
         },
         fail: function (res) { 
         },//接口调用失败的回调函数
         complete: function (res) { 
         },//接口调用结束的回调函数（调用成功、失败都会执行）
  })
}else{
  var that = this;
wx.showModal({
  title:'收藏夹',
  content:'确定要移出收藏夹?',
  showCancel:true,
  cancelText:"否",
  cancelColor: 'cancelColor',
  confirmText:"是",//默认是“确定”
       confirmColor: 'skyblue',//确定文字的颜色
       success: function (res) {
          if (res.cancel) {
             //点击取消,默认隐藏弹框
          } else {
             //点击确定
             that.setData({
              isLike: !that.data.isLike
            });
             var userVO = wx.getStorageSync('userkey');
             var goodsVO = wx.getStorageSync('key');
            //移出收藏夹
           wx.request({
                url: 'https://www.8935758.xyz/remove/user/reserve/info.json',
                header:{
                  'content-Type':'application/json'
                },
                method:'POST',
                data:{
                  goodsVO:goodsVO,
                  userVO:userVO
                },
                success:function(res){
                  if(res.statusCode!= 200 || res.data=='fail'){
                    wx.showToast({
                      title: '您已删除',
                      icon:'error',
                      duration:1500
                    })
                  }else{
                    wx.showToast({
                      title: '删除成功',
                      icon:'success',
                      duration:1500
                    })
                  }
                }
                })
          }
       },
       fail: function (res) { 

       },//接口调用失败的回调函数
       complete: function (res) { 

       },//接口调用结束的回调函数（调用成功、失败都会执行）
})
}
},
//加入购物车
addCart(){
  var goodsNum = wx.getStorageSync('goodsnumberkey');
  var sumPrice = wx.getStorageSync('sumpricekey');
  var goodsDetail = this.data.detail;
  var userVO = wx.getStorageSync('userkey');
  if(goodsNum==""||sumPrice==""){
    wx.showToast({
      title: '选择购买数量',
      icon:'error',
      duration:1500
    })
  }else{
    console.log(goodsNum);
    console.log(sumPrice);
    //添加数据到购物车
    wx.request({
      url: 'https://www.8935758.xyz/add/user/shoppingcart/info.json',
      header:{
        'content-Type':'application/json'
      },
      method:'POST',
      data:{
        goodsVO:goodsDetail,
        goodsnumber:goodsNum,
        sumprice:sumPrice,
        userVO:userVO
      },
      success:function(res){
        console.log(res.data)
        if(res.statusCode!= 200||res.data=='fail'){
          wx.showToast({
            title: '加入失败',
            icon:'error',
            duration:1500
          })
        }
        else{
          wx.setStorageSync('goodsnumbercartkey', goodsNum)
          wx.setStorageSync('sumpricecartkey', sumPrice)
          console.log(res.data)
          wx.switchTab({
            url: '../shopping-cart/shopping-cart',
          }) 
          wx.removeStorageSync('goodsnumberkey')
          wx.removeStorageSync('sumpricekey')
        }
      }
    })
  }
  /*
  wx.switchTab({
    url: '../shopping-cart/shopping-cart',
  })*/
},
//显示购物车弹框
showCart(){
  this.setData({
    cartBox: !this.data.cartBox,
    mask: !this.data.mask
  })
},
//点击遮罩层隐藏弹窗
hideAllBox() {
  this.setData({
    //遮罩层隐藏
    mask: true,
    //产品参数弹窗隐藏
    paramsBox: true,
    //购物车弹窗隐藏
    cartBox: true,
    //选择规格弹窗隐藏
    choice: true,

  })
},
/* 点击减号 */
reduceNumber: function () {
  var num = this.data.goodsNumber;
  // 如果大于1时，才可以减  
  if (num > 1) {
    num--;
  }
  // 只有大于一件的时候，才能normal状态，否则disable状态  
  var minusStatus = num <= 1 ? 'disabled' : 'normal';
  // 将数值与状态写回  
  this.setData({
    goodsNumber: num,
    minusStatus: minusStatus
  });
},
/* 点击加号 */
addNumber: function () {
  var num = this.data.goodsNumber;
  // 不作过多考虑自增1  
  num++;
  // 只有大于一件的时候，才能normal状态，否则disable状态  
  var minusStatus = num < 1 ? 'disabled' : 'normal';
  // 将数值与状态写回  
  this.setData({
    goodsNumber: num,
    minusStatus: minusStatus
  });
},
/* 输入框事件 */
inputValueChange: function (e) {
  var num = e.detail.value;
  // 将数值与状态写回  
  this.setData({
    goodsNumber: num
  });
},
cartBoxClick(){
  console.log(this.data.goodsNumber)
  var sumPrice = (this.data.goodsNumber)*(this.data.detail.price);
  console.log(sumPrice);
  //把商品数量和总价存入缓存
  wx.setStorageSync('goodsnumberkey', this.data.goodsNumber)
  wx.setStorageSync('sumpricekey', sumPrice)
  this.hideAllBox();
},

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    var value = wx.getStorageSync('key');
    console.log(value);
    that.setData({
      detail:value
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