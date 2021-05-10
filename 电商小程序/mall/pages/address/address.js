// pages/address/address.js

const app = getApp();
 
Page({
  data: {
    elevatorFlag: 0,
    nameValue: '',
    phoneValue: '',
    region: ["省", "市", "区"],
    regionFlag: 1,
    textareaValue: '',
    floorValue: 0,
    remarksValue: '',
    addressStatus: 0,
    userID: 0
  },
  //保存地址
  submitAddress:function(e){
    var name = e.detail.value.name;
    var phoneNum = e.detail.value.phonenum;
    var district = e.detail.value.district;
    var detailAddress = e.detail.value.detailAddress;
    var floor = e.detail.value.floor;
    var beizhu = e.detail.value.beizhu;
    var userVO = wx.getStorageSync('userkey');
    var district_ = district[1]+district[2];
    console.log(district)
    console.log(district_);
    console.log(userVO);
    var that = this;
    
    wx.request({
      url: 'https://www.8935758.xyz/add/user/address/info.json',
      header:{
        'content-Type':'application/json'
      },
      method:'POST',
      data:{
        name:name,
        phoneNum:phoneNum,
        district:district_,
        detailaddress:detailAddress,
        floor:floor,
        beizhu:beizhu,
        userVO:userVO
      },
      success:function(res){
        console.log(res);
        if(res.statusCode!=200||res.data==null){
          wx.showToast({
            title: '请先登录',
            icon:'error',
            duration:1500
          })
        }else{
          wx.showToast({
            title: '保存成功',
            icon:'success',
            duration:1500
          })
        }
      }
    })
  },
  //数组转json
  
  onLoad: function (){
    let self = this;
    this.setData({ userID: app.globalData.userID });
  },
  changeIconStatu: function () {
    var self = this;
    this.setData({ elevatorFlag: !self.data.elevatorFlag});
  },
  getNameValue: function (e) {
    this.setData({ nameValue: e.detail.value });
  },
  getPhoneValue: function (e) {
    this.setData({ phoneValue: e.detail.value });
  },
  bindRegionChange: function (e) {
    this.setData({ region: e.detail.value, regionFlag: 0 });
  },
  getTextareaValue: function (e) {
    this.setData({ textareaValue: e.detail.value });
  },
  getFloorValue: function (e) {
    this.setData({ floorValue: e.detail.value });
  },
  getRemarksValue: function (e) {
    this.setData({ remarksValue: e.detail.value });
  },
  defaultChange: function (e){
    if (e.detail.value){
      this.setData({ addressStatus: 1 });
    }else{
      this.setData({ addressStatus: 0 });    
    }
  },
});