package com.rsa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.api.common.Constants;
import com.api.common.bo.PlatformCallBackReqVO;
import com.api.common.utils.RSA;
import com.api.common.utils.StandardDesUtils;

/**
 * @author : Darben
 * @version : 1.0
 * @Description : 测试
 * @Copyright : Sinaif Software Co.,Ltd.Rights Reserved
 * @Company : 海南新浪爱问普惠科技有限公司
 * @Date : 2018年10月18日 上午11:00:07
 */
public class KingPlatformDemo {


    public static void main(String[] args) {
        rsaTest();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        standedTest();
    }


    public static void rsaTest() {
        PlatformCallBackReqVO vo = new PlatformCallBackReqVO();
        vo.setMethod("api.charge.data");
        vo.setSign_type("RSA");
        vo.setBiz_data("{'order_no':'1584827442684559360'}");
        vo.setBiz_enc("0");
        vo.setDes_key("");
        vo.setApp_id("appid1000000");
        vo.setVersion("1.0");
        vo.setFormat("json");
        vo.setTimestamp(System.currentTimeMillis() + "");
        vo.setReturn_url("");

        try {
            String sign = RSA.getSign(vo, Constants.sina_privatekey);
            String content = RSA.getSignStr(vo);
            System.out.println("content=" + content);
            vo.setSign(sign);
            System.out.println("请求参数：" + JSONObject.toJSONString(vo));
            boolean a = RSA.checkSign(content, sign, Constants.sina_publickey);
            System.out.println(a);

            //如果对方用他们的
            System.out.println("==================================111111111111111==============================================================");
            content = "quyixiao";

            System.out.println("==========content =" + content);
            String encryptContent = RSA.encrypt_privateKey(content, Constants.sina_privatekey);
            System.out.println("encryptContent  =" + encryptContent);

            String decryptContent = RSA.decrypt_publicKey(encryptContent, Constants.sina_publickey);
            System.out.println("decryptContent  =" + decryptContent);

            System.out.println("===================================2222222222222=============================================================");
            System.out.println("++++++++++++++++++++++content =" + content);
            String encrypt = RSA.encrypt(content, Constants.linzi_publickey);
            System.out.println("encrypt =" + encrypt);
            String decrypt = RSA.decrypt(encrypt, Constants.linzi_privatekey);
            System.out.println("decrypt =" + decrypt);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public static void standedTest() {
        PlatformCallBackReqVO vo = new PlatformCallBackReqVO();
        vo.setMethod("api.charge.data");
        vo.setSign_type("RSA");
        vo.setBiz_data("{'order_no':'1584827442684559360'}");
        vo.setBiz_enc("0");
        vo.setDes_key("");
        vo.setApp_id("appid1000000");
        vo.setVersion("1.0");
        vo.setFormat("json");
        vo.setTimestamp(System.currentTimeMillis() + "");
        vo.setReturn_url("");


        try {


            String content = StandardDesUtils.getSignParamStr(JSON.toJSONString(vo));
            String sign = StandardDesUtils.sign(content, Constants.sina_privatekey);



            System.out.println("content=" + content);

            vo.setSign(sign);

            System.out.println("请求参数：" + JSONObject.toJSONString(vo));
            boolean a = StandardDesUtils.checkSign(content, sign, Constants.sina_publickey);
            System.out.println(a);


            System.out.println("===================================33333333333333=============================================================");
            System.out.println("content =" + content);


            String key = StandardDesUtils.generateDesKey();
            System.out.println("key =" + key);

            String encrypt = StandardDesUtils.encrypt(content, key);
            System.out.println("encrypt =" + encrypt);
            String decrypt = StandardDesUtils.decrypt(encrypt, key);
            System.out.println("decrypt =" + decrypt);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



}
