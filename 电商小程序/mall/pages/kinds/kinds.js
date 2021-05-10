const app = getApp();
//声明全局变量
let proListToTop = [], menuToTop = [], MENU = 0, 
windowHeight, timeoutId;
Page({
  /**
   * 页面的初始数据
   */
  data: {
    staticImg: app.globalData.staticImg,
    currentActiveIndex: 0,
    // 接口返回的商品数组
    
  },
  searchGoods(){
    wx.navigateTo({
      url: '../search/search',
    })
  },
  //跳转到详情
  Todetails:function(e){
    var id = e.currentTarget.dataset.id;
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
        if(res.statusCode!=200||res.data.length==0){
          wx.showToast({
            title: '没有数据',
            icon:'error',
            duration:1500
          })
        }else{
          wx.setStorageSync('key', res.data);
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
    //获取数据
    var that = this;
    wx.request({
      url: 'https://www.8935758.xyz/get/goods/type/list.json',
      header:{
        "content-type":'application'
      },
      method:'GET',
      data:{

      },
      success:function(res){
        console.log(res.data);
        if(res.statusCode!=200||res.data.length==0){
          wx.showToast({
            title: '没有数据',
            icon:'error',
            duration:1500
          })
        }else{
          that.setData({
            list:res.data
          })
        }
      }
    })
    // 确保页面数据已经刷新完毕~
    setTimeout(() => {
      that.getAllRects()
    }, 2000)
  },

  changeMenu(e) {
    
    console.log(proListToTop);
    console.log(e)
    console.log(this.data.currentActiveIndex);
    // 改变左侧tab栏操作
    if (Number(e.target.id) === this.data.currentActiveIndex) return
    MENU = 1
    this.setData({
      currentActiveIndex: Number(e.target.id),
      rightProTop: proListToTop[Number(e.target.id)]
    })
    this.setMenuAnimation(Number(e.target.id))
  },
  scroll(e) {
    for (let i = 0; i < proListToTop.length; i++) {
      if (e.detail.scrollTop < proListToTop[i] && i !== 0 && e.detail.scrollTop > proListToTop[i - 1]) {
        return this.setDis(i)
      }
    }
    // 找不到匹配项，默认显示第一个数据
    if (!MENU) {
      this.setData({
        currentActiveIndex: 0
      })
    }
    MENU = 0
  },
  setDis(i) {
    
    // 设置左侧menu栏的选中状态
    if (i !== this.data.currentActiveIndex + 1 && !MENU) {
      this.setData({
        currentActiveIndex: i - 1
      })
    }
    MENU = 0
    this.setMenuAnimation(i)
  },
  setMenuAnimation(i) {
    // 设置动画，使menu滚动到指定位置。
    let self = this
    
    if (menuToTop[i].animate) {
      
      // 节流操作
      if (timeoutId) {
        clearTimeout(timeoutId)
      }
      timeoutId = setTimeout(() => {
        self.setData({
          leftMenuTop: (menuToTop[i].top - windowHeight)
        })
      }, 50)
    } else {
      
      if (this.data.leftMenuTop === 0) return
      
      this.setData({
        leftMenuTop: 0
      })
    }
  },
  getActiveReacts() {
    wx.createSelectorQuery().selectAll('.menu-active').boundingClientRect(function (rects) {
      return rects[0].top
    }).exec()
  },
  getAllRects() {
    // 获取商品数组的位置信息
    wx.createSelectorQuery().selectAll('.pro-item').boundingClientRect(function (rects) {
      rects.forEach(function (rect) {
        console.log(rect)
        // 这里减去44是根据你的滚动区域距离头部的高度，如果没有高度，可以将其删去
        proListToTop.push(rect.top - 44)
      })
    }).exec()

    // 获取menu数组的位置信息
    wx.createSelectorQuery().selectAll('.menu-item').boundingClientRect(function (rects) {
      wx.getSystemInfo({
        success: function (res) {
          console.log(res);
          windowHeight = res.windowHeight / 2
          // console.log(windowHeight)
          rects.forEach(function (rect) {
            menuToTop.push({
              top: rect.top,
              animate: rect.top > windowHeight
            })
          })
        }
      })
    }).exec()
  },
  // 获取系统的高度信息
  getSystemInfo() {
    let self = this
    wx.getSystemInfo({
      success: function (res) {
        windowHeight = res.windowHeight / 2
      }
    })
  }

})