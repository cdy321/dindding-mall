package cn.cdy.crowd.controller;

import cn.cdy.crowd.api.MySqlRemoteService;
import cn.cdy.crowd.config.payProperties;
import cn.cdy.crowd.entity.vo.OrderProjectVO;
import cn.cdy.crowd.entity.vo.OrderVO;
import cn.cdy.crowd.util.ResultEntity;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;
@Controller
public class payController {
    @Autowired
    private payProperties payProperties;
    @Autowired
    private MySqlRemoteService mySqlRemoteService;
    @ResponseBody
    @RequestMapping("/generate/order")
    public String generateOrder(HttpSession session, OrderVO orderVO) throws AlipayApiException {
        OrderProjectVO orderProjectVO = (OrderProjectVO)session.getAttribute("orderProjectVO");
        System.out.println(orderProjectVO);
        orderVO.setOrderProjectVO(orderProjectVO);
        String time = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String user = UUID.randomUUID().toString().replace("-", "").toUpperCase();
        String orderNum = time + user;
        orderVO.setOrderNum(orderNum);
        Double orderAmount = (double) (orderProjectVO.getSupportPrice() * orderProjectVO.getReturnCount() + orderProjectVO.getFreight());
        orderVO.setOrderAmount(orderAmount);
        session.setAttribute("orderVO",orderVO);
        System.out.println(orderVO);
        return sendRequestToAliPay(orderNum, orderAmount, orderProjectVO.getProjectName(), orderProjectVO.getReturnContent());
    }

    private String sendRequestToAliPay(String outTradeNo, Double totalAmount, String subject, String body) throws AlipayApiException{
        AlipayClient alipayClient = new DefaultAlipayClient(
                payProperties.getGatewayUrl(), payProperties.getAppId(), payProperties.getMerchantPrivateKey(), "json", payProperties.getCharset(), payProperties.getAlipayPublicKey(), payProperties.getSignType());
               //??????????????????
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(payProperties.getReturnUrl());
        alipayRequest.setNotifyUrl(payProperties.getNotifyUrl());
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ outTradeNo +"\"," + "\"total_amount\":\""+ totalAmount +"\"," + "\"subject\":\""+ subject +"\"," + "\"body\":\""+ body +"\"," + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        return alipayClient.pageExecute(alipayRequest).getBody();
    }

    @ResponseBody
    @RequestMapping("/return")
    public String returnUrlMethod(HttpServletRequest request,HttpSession session) throws AlipayApiException, UnsupportedEncodingException {
         // ??????????????? GET ??????????????????
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // ???????????????????????????????????????????????????
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType()); //?????? SDK ????????????
         // ????????????????????????????????????????????????????????????????????????
        if(signVerified) {
        // ???????????????
            String orderNum = new
                    String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
          // ??????????????????
            String payOrderNum = new
                    String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
         // ????????????
            String orderAmount = new
                    String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            OrderVO orderVO = (OrderVO)session.getAttribute("orderVO");
            orderVO.setPayOrderNum(payOrderNum);
            ResultEntity<String> resultEntity = mySqlRemoteService.saveOrderRemote(orderVO);
            return "trade_no:"+payOrderNum+"<br/>out_trade_no:"+orderNum+"<br/>total_amount:"+orderAmount;
        }else {
          // ?????????????????????????????????
            return "????????????";
        }
    }

    @RequestMapping("/notify")
    public void notifyUrlMethod(HttpServletRequest request) throws
            UnsupportedEncodingException, AlipayApiException {
          //??????????????? POST ??????????????????
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
             //???????????????????????????????????????????????????
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        boolean signVerified = AlipaySignature.rsaCheckV1(
                params, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType());
        if(signVerified) {//????????????
             //???????????????
            String out_trade_no = new
                    String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
             //??????????????????
            String trade_no = new
                    String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //????????????
            String trade_status = new
                    String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            System.out.println("out_trade_no="+out_trade_no);
            System.out.println("trade_no="+trade_no);
            System.out.println("trade_status="+trade_status);
        }else {
                    //????????????
                   //???????????????????????????????????????????????????????????????
                   //String sWord = AlipaySignature.getSignCheckContentV1(params);
            System.out.println("????????????");
        }
    }


}
