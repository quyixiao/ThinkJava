package sms;

import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;

public class DHSTMobileHandler {


    private static String Url = "http://www.dh3t.com/json/sms/Submit?method=Submit";
    private static String account = "dh80911";// 用户名（必填）
    private static String password = DigestUtils.md5Hex("zY7MKtqQ");// 密码（必填,明文）
    public static String sign = "【品乐彩店】"; // 短信签名（必填）
    public static String subcode = ""; // 子号码（可选）
    public static String sendtime = ""; // 定时发送时间（可选）

    private static final String SUCCESS = "0";

    /**
     * 数据校验
     *
     * @param mobileMsgTemplate 消息
     */
    public void check(MobileMsgTemplate mobileMsgTemplate) {
    }

    public boolean process(MobileMsgTemplate mobileMsgTemplate) {


        return sender(mobileMsgTemplate);

    }

    public void execute(MobileMsgTemplate mobileMsgTemplate) {

    }


    public static boolean sender(MobileMsgTemplate mobileMsgTemplate) {
        String mobile = mobileMsgTemplate.getMobile();
        String content = mobileMsgTemplate.getContent();

        HttpClient client = new HttpClient();
        PostMethod method = new PostMethod(Url);

        client.getParams().setContentCharset("UTF-8");
        method.setRequestHeader("ContentType", "application/x-www-form-urlencoded;charset=UTF-8");

        String jsonStr = "{\"account\":\"" + account + "\",\n" +
                "\"password\":\"" + password + "\",\n" +
                "\"msgid\":\"\",\n" +
                "\"phones\":\"" + mobile + "\",\n" +
                "\"content\":\"" + content + "\",\n" +
                "\"sign\":\"" + sign + "\",\"subcode\":\"" + subcode + "\",\n" +
                "\"sendtime\":\"" + sendtime + "\"\n" +
                "}";
        method.setRequestBody(jsonStr);
        try {
            client.executeMethod(method);
            String submitResult = method.getResponseBodyAsString();
            System.out.println("提交单条普通短信响应：" + submitResult);
            DHSTResult dhstResult = JSON.parseObject(submitResult, DHSTResult.class);
            if (SUCCESS.equals(dhstResult.getResult())) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("应用异常");
        }
        return false;
    }

    /**
     * 失败处理
     *
     * @param mobileMsgTemplate 消息
     */
    public void fail(MobileMsgTemplate mobileMsgTemplate) {
    }


    public static void main(String[] args) {
        MobileMsgTemplate mobileMsgTemplate = new MobileMsgTemplate();
        mobileMsgTemplate.setMobile("18458195149");
        mobileMsgTemplate.setContent("亲爱的18458195149: 恭喜您注册成功。");
        sender(mobileMsgTemplate);
    }


}
