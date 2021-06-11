package com.skysharing.api.response;

import com.alibaba.fastjson.JSONObject;
import com.skysharing.api.request.GetShowSignUrlRequest;

/**
 * <p>GetShowSignUrlResponse class.</p>
 *
 * @author zhan
 * @version $Id: $Id
 */
public class GetShowSignUrlResponse extends CassPayResponse<GetShowSignUrlRequest> {
    /**
     *
     */
    public String base64URL;
    /**
     *
     */
    public String url;
    /**
     *
     */
    public String weChatH5URL;

    /**
     * <p>Constructor for GetShowSignUrlResponse.</p>
     *
     * @param response a {@link com.alibaba.fastjson.JSONObject} object.
     */
    public GetShowSignUrlResponse(JSONObject response) {
        super(response);
        this.base64URL = this.content.getString("base64_url");
        this.url = this.content.getString("url");
        this.weChatH5URL = this.content.getString("wechat_h5_url");
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "GetShowSignUrlResponse{" +
                "base64URL='" + base64URL + '\'' +
                ", url='" + url + '\'' +
                ", weChatH5URL='" + weChatH5URL + '\'' +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", content=" + content +
                '}';
    }
}
