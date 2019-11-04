package com.maosong.component.net;

import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
//import org.json.JSONException;
//import org.json.JSONObject;

import java.io.IOException;

/**
 * 响应业务拦截
 **/
public class ResponseInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        if (response.code() != 200)
            return response;
        Response.Builder builder = response.newBuilder();
        Response clone = builder.build();
        ResponseBody body = clone.body();
        if (body != null) {
            MediaType mediaType = body.contentType();
            if (mediaType != null && isText(mediaType)) {
                String resp = body.string();
                try {
                    JSONObject object = JSON.parseObject(resp);
                    int code = object.getIntValue("code");
                    String msg = object.getString("msg");
                    String action = object.getString("action");
                    if (TextUtils.equals(action, "")) {

                    }
                    if (code == NetConstant.RESULT_CODE_TOKEN_ERROR || code == NetConstant.RESULT_CODE_LOGINOTHER || code == NetConstant.RESULT_CODE_TOKEN_NO_USER)
                        throw new TokenException(object.getString("msg"), code);
                    if (code == NetConstant.RESULT_CODE_SUCCESS || code == NetConstant.RESULT_CODE_REGISTED) {
                        body = ResponseBody.create(mediaType, resp);
                        return response.newBuilder().body(body).build();
                    } else {
                        throw new ApiException(code, object.getString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }


    private boolean isText(MediaType mediaType) {
        return mediaType.type() != null && mediaType.type().equals("text") || mediaType.subtype() != null &&
                (mediaType.subtype().equals("json") || mediaType.subtype().equals("xml") || mediaType.subtype().equals("html")
                        || mediaType.subtype().equals("webviewhtml") || mediaType.subtype().equals("x-www-form-urlencoded"));
    }
}
