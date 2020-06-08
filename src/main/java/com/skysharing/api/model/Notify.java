package com.skysharing.api.model;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Notify {
    public Response response;
    public String sign;

    public Notify(String json) throws Exception {
        JSONObject j = JSON.parseObject(json);
        if (!j.containsKey("sign")) {
            throw new Exception("响应中无 sign 键");
        }
        if (!j.containsKey("response")) {
            throw new Exception("响应中无 response 键");
        }
        this.sign = j.getString("sign");
        JSONObject jr = j.getJSONObject("response");
        this.response = new Response();
        this.response.contentRaw = jr.getString("content");
        this.response.charset = jr.getString("charset");
        this.response.notifyTime = jr.getString("notifyTime");
        this.response.notifyType = jr.getInteger("notifyType");
        this.response.signType = jr.getString("signType");
        JSONObject jrc = jr.getJSONObject("content");
        this.response.content = new Content();
        this.response.content.resourceID = jrc.getString("resourceID");
        this.response.content.pushType = jrc.getInteger("pushType");
        this.response.content.notifyUrl = jrc.getString("notifyUrl");
        this.response.content.createdAt = jrc.getInteger("createdAt");
        this.response.content.orderSN = jrc.getString("orderSN");
        this.response.content.rbUUID = jrc.getString("rbUUID");
        this.response.content.sendData = new SendData();
        this.response.content.sendData.status = jrc.getJSONObject("sendData").getString("status");
    }

    @Override
    public String toString() {
        return "Notify{" +
                "response=" + response +
                ", sign='" + sign + '\'' +
                '}';
    }
}

class Response {
    public String charset;
    public String contentRaw;
    public Content content;
    public String notifyTime;
    public Number notifyType;
    public String signType;

    @Override
    public String toString() {
        return "NotifyResponse{" +
                "charset='" + charset + '\'' +
                ", contentRaw='" + contentRaw + '\'' +
                ", content=" + content +
                ", notifyTime='" + notifyTime + '\'' +
                ", notifyType=" + notifyType +
                ", signType='" + signType + '\'' +
                '}';
    }
}

class Content {
    public String resourceID;
    public Number pushType;
    public String notifyUrl;
    public SendData sendData;
    public Number createdAt;
    public String orderSN;
    public String rbUUID;

    @Override
    public String toString() {
        return "NotifyContent{" +
                "resourceID='" + resourceID + '\'' +
                ", pushType=" + pushType +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", sendData=" + sendData +
                ", createdAt=" + createdAt +
                ", orderSN='" + orderSN + '\'' +
                ", rbUUID='" + rbUUID + '\'' +
                '}';
    }
}

class SendData {
    public String status;

    @Override
    public String toString() {
        return "SendData{" +
                "status='" + status + '\'' +
                '}';
    }
}


