package sms;

import java.io.Serializable;

public class DHSTResult implements Serializable{

    private String msgid;

    private String result;

    private String desc;

    private String failPhones;


    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFailPhones() {
        return failPhones;
    }

    public void setFailPhones(String failPhones) {
        this.failPhones = failPhones;
    }
}
