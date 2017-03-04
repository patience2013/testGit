package xiong.com.mvptest.http;

import android.graphics.Bitmap;
import android.util.Log;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Response;
import xiong.com.mvptest.util.ToastUtil;

public class HttpNetwork {

    public static int SUCCESS_RESPONCE_CODE = 0;

    private static HttpNetwork instance;

    public static HttpNetwork getInstance() {
        return instance;
    }


    private static final Gson gson = new Gson();

    public String httpHost;

    private Map<String, Long> requestingPackets = new HashMap<String, Long>();
    private String http_net_server;

    public HttpNetwork(String host, String http_net_server) {
        instance = this;
        this.httpHost = host;
        this.http_net_server = http_net_server;
    }


    // 发送请求的方法
    public <RESP> void request(HttpRequestPacket cmd,
                               NetResponseCallback respCallback) {
        request(cmd, respCallback, null);
    }

    public void request(final HttpRequestPacket cmd,
                        final NetResponseCallback respCallback,
                        final NetErrorCallback errorCallback) {
        request(cmd, respCallback, errorCallback, null);
    }

    public void request(final HttpRequestPacket cmd,
                        final NetResponseCallback respCallback,
                        final NetErrorCallback errorCallback, final NetPacketParse parse) {
        String fullPath = httpHost + cmd.action;
        String cmdJson;
        if (parse == null) {

            cmdJson = gson.toJson(cmd);
        } else {
            cmdJson = parse.parsePacket(cmd);
        }
// 过滤
        if (filterRequest(cmd)) {
            if (errorCallback != null) {
                errorCallback.onError(cmd, "正在加载，请稍后。");
            }
            return;
        }
        OkHttpUtils.postString().url(fullPath).content(cmdJson)
                .mediaType(MediaType.parse("application/json; charset=utf-8")).
                build().execute(new Callback<HttpResponsePacket>() {
            @Override
            public HttpResponsePacket parseNetworkResponse(Response response, int id) throws Exception {
                String string = response.body().string();
                HttpResponsePacket packet = new Gson().fromJson(string, HttpResponsePacket.class);
                return packet;
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                removeRequest(cmd);
                if (errorCallback != null) {
                    errorCallback.onError(cmd, "网络连接不可用，请稍后再试");
                } else {
                    ToastUtil.showToast("网络连接不可用，请稍后再试");
                }
            }

            @Override
            public void onResponse(HttpResponsePacket response, int id) {
                removeRequest(cmd);
                if (respCallback != null) {
                    respCallback.onResponse(cmd, response);
                }
            }
        });
//        GsonRequest gsonRequest = new GsonRequest(fullPath, cmdJson,
//                new Response.Listener<HttpResponsePacket>() {
//
//                    @Override
//                    public void onResponse(HttpResponsePacket arg0) {
//                        removeRequest(cmd);
//                        if (respCallback != null) {
//                            respCallback.onResponse(cmd, arg0);
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError arg0) {
//                removeRequest(cmd);
//                if (errorCallback != null) {
//                    errorCallback.onError(cmd, "网络连接不可用，请稍后再试");
//                } else {
//                    ToastUtil.showToast("网络连接不可用，请稍后再试");
//                }
//            }
//        });
//        cmdRequestQueue.add(gsonRequest);
    }

    // 請求donet返回json格式的服务器
    public void request_net_server(final HttpRequestPacket cmd,
                                   final NetResponseStringCallback respCallback,
                                   final NetErrorCallback errorCallback) {
        request_net_server(cmd, respCallback, errorCallback, null);
    }

    public void request_net_server(final HttpRequestPacket cmd,
                                   final NetResponseStringCallback respCallback,
                                   final NetErrorCallback errorCallback, final NetPacketParse parse) {
        String fullPath = http_net_server + cmd.action;
        final String params;

        if (parse == null) {
            params = gson.toJson(cmd);
        } else {
            params = parse.parsePacket(cmd);
        }

        // 过滤
        if (filterRequest(cmd)) {
            if (errorCallback != null) {
                if (errorCallback != null) {
                    errorCallback.onError(cmd, "正在加载，请稍后。");
                }
            }
            return;
        }

//        JsonObjectRequest objRequest = new JsonObjectRequest(Method.POST,
//                fullPath, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject obj) {
//                removeRequest(cmd);
//                if (respCallback != null) {
//                    Log.i("contentInfo", obj.toString());
//                    respCallback.onResponse(cmd, obj.toString());
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                removeRequest(cmd);
//                if (errorCallback != null) {
//                    errorCallback.onError(cmd, "网络连接不可用，请稍后再试");
//                } else {
//                    ToastUtil.showToast("网络连接不可用，请稍后再试");
//                }
//            }
//
//        }) {
//            @Override
//            public byte[] getBody() {
//                return params == null ? super.getBody() : params.getBytes();
//            }
//        };
//
//        cmdRequestQueue.add(objRequest);
    }

//    // 請求donet返回json格式的服务器
//    public void request_net_server_package_get(final String url,
//                                               final NetResponsePackageCallback respCallback,
//                                               final NetErrorCallback errorCallback) {
//        request_net_server_package_get(url, respCallback, errorCallback, null);
//    }
//
//    public void request_net_server_package_get(final String url,
//                                               final NetResponsePackageCallback respCallback,
//                                               final NetErrorCallback errorCallback, final NetPacketParse parse) {
//        String fullPath = http_net_server + url;
//
//        JsonObjectRequest objRequest = new JsonObjectRequest(Method.GET,
//                fullPath, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject obj) {
//                if (respCallback != null) {
//                    HttpNewResponse response = new HttpNewResponse();
//                    try {
//                        response.ErrorMessage = obj
//                                .getString("ErrorMessage");
//                        response.StatusCode = obj.getInt("StatusCode");
//                        response.Content = obj.getString("Content");
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                    }
//                    respCallback.onResponse(response);
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                if (errorCallback != null) {
//                    errorCallback.onError(null, "网络连接不可用，请稍后再试");
//                } else {
//                    ToastUtil.showToast("网络连接不可用，请稍后再试");
//                }
//            }
//
//        });
//
//        cmdRequestQueue.add(objRequest);
//    }

//    // 請求donet返回json格式的服务器
//    public void request_net_server_package(final HttpRequestPacket cmd,
//                                           final NetResponsePackageCallback respCallback,
//                                           final NetErrorCallback errorCallback) {
//        request_net_server_package(cmd, respCallback, errorCallback, null);
//    }
//
//    public void request_net_server_package(final HttpRequestPacket cmd,
//                                           final NetResponsePackageCallback respCallback,
//                                           final NetErrorCallback errorCallback, final NetPacketParse parse) {
//        String fullPath = "http://192.168.1.118:8083/api/" + cmd.action;
//        final String params;
//
//        if (parse == null) {
//            params = gson.toJson(cmd);
//        } else {
//            params = parse.parsePacket(cmd);
//        }
//
//        // 过滤
//        if (filterRequest(cmd)) {
//            if (errorCallback != null) {
//                if (errorCallback != null) {
//                    errorCallback.onError(cmd, "正在加载，请稍后。");
//                }
//            }
//            return;
//        }
//
//        JsonObjectRequest objRequest = new JsonObjectRequest(Method.POST,
//                fullPath, null, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject obj) {
//                removeRequest(cmd);
//                if (respCallback != null) {
//                    HttpNewResponse response = new HttpNewResponse();
//                    try {
//                        response.ErrorMessage = obj
//                                .getString("ErrorMessage");
//                        response.StatusCode = obj.getInt("StatusCode");
//                        response.Content = obj.getString("Content");
//                    } catch (Exception e) {
//                        // TODO: handle exception
//                    }
//                    respCallback.onResponse(response);
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                removeRequest(cmd);
//                if (errorCallback != null) {
//                    errorCallback.onError(cmd, "网络连接不可用，请稍后再试");
//                } else {
//                    ToastUtil.showToast("网络连接不可用，请稍后再试");
//                }
//            }
//
//        }) {
//            @Override
//            public byte[] getBody() {
//                return params == null ? super.getBody() : params.getBytes();
//            }
//        };
//
//        cmdRequestQueue.add(objRequest);
//    }
//
//    public void request_net_serverToParams(final HttpRequestPacket cmd,
//                                           final NetResponseStringCallback respCallback,
//                                           final NetErrorCallback errorCallback) {
//        request_net_serverToParams(cmd, respCallback, errorCallback, null);
//    }
//
//    public void request_net_serverToParams(final HttpRequestPacket cmd,
//                                           final NetResponseStringCallback respCallback,
//                                           final NetErrorCallback errorCallback, final NetPacketParse parse) {
//        String fullPath = http_net_server + cmd.action;
//        final String params;
//
//        if (parse == null) {
//            params = gson.toJson(cmd);
//        } else {
//            params = parse.parsePacket(cmd);
//        }
//
//        // 过滤
//        if (filterRequest(cmd)) {
//            if (errorCallback != null) {
//                if (errorCallback != null) {
//                    errorCallback.onError(cmd, "正在加载，请稍后。");
//                }
//            }
//            return;
//        }
//        StringRequest request = new StringRequest(Method.POST, fullPath,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String result) {
//                        removeRequest(cmd);
//                        if (respCallback != null) {
//
//                            respCallback.onResponse(cmd, result);
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError arg0) {
//                removeRequest(cmd);
//                if (errorCallback != null) {
//                    errorCallback.onError(cmd, "网络连接不可用，请稍后再试");
//                } else {
//                    ToastUtil.showToast("网络连接不可用，请稍后再试");
//                }
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> param = gson.fromJson(params,
//                        new TypeToken<Map<String, String>>() {
//                        }.getType());
//                return param;
//            }
//        };
//
//        cmdRequestQueue.add(request);
//    }

    // 过滤请求包，短时间频繁请求相同包并无返回将过滤
    private boolean filterRequest(HttpRequestPacket request) {
        String key = gson.toJson(request);
        if (requestingPackets.containsKey(key)) {
            long requestTime = requestingPackets.get(key);
            if (requestTime < 3 * 1000 + System.currentTimeMillis()) {
                requestingPackets.put(key, System.currentTimeMillis());
                return true;
            } else {
                requestingPackets.remove(key);
            }
        } else {
            requestingPackets.put(key, System.currentTimeMillis());
        }
        return false;
    }

    private void removeRequest(HttpRequestPacket request) {
        String key = gson.toJson(request);
        requestingPackets.remove(key);
    }

    // 返回回调接口
    public interface NetResponseCallback {
        public void onResponse(HttpRequestPacket request,
                               HttpResponsePacket response);
    }

    // 访问出错的回调接口
    public interface NetErrorCallback {
        public void onError(HttpRequestPacket request, String errorMsg);
    }

    // 重载数据包解析
    public interface NetPacketParse {
        public String parsePacket(HttpRequestPacket packet);
    }

    public interface BitmapLoaded {
        public void onBitmapLoaded(Bitmap bitmap);
    }

    // 返回回调接口
    public interface NetResponseStringCallback {
        public void onResponse(HttpRequestPacket request, String response);
    }

//    // 返回回调接口
//    public interface NetResponsePackageCallback {
//        public void onResponse(HttpNewResponse result);
//    }
}
