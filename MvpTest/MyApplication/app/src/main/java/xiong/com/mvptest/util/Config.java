package xiong.com.mvptest.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


import xiong.com.mvptest.App;

public class Config {
    private static Config instance = new Config();

    public static Config getInstance() {
        return instance;
    }

    public static final String UID = "uid";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username";
    public static final String AUTOLOGIN = "autologinflag";
    public static final String FIRST_LANUCHER = "firstLauncher";
    public static final String CHECK_STATE = "checked";
    public static final String SOLVE_NUM = "solve_num";
    public static final String LAST_SEND_CODE = "last_send_code"; // 最后一次发送验证码时间

    public static final String UUID = "uuid";
    public static final String UUID_TYPE = "uuid_type";
    public static final String THIRD_LOGIN = "third_login";
    public static final String FSecurityCode = "C39N23J94P2JIWJ8";
    //艺易拍包名称
    public static final String APPPACKAGERNAME = "com.paiyiy";
    // 服务器的网站
    public static final String SERVERNAME = "www.yiyipai.cn";
    // 微信的包名称
    public static final String WECHAPACKAGERNAME = "com.tencent.mm";
    // qq的包名
    public static final String QQPACKAGERNAME = "com.tencent.mobileqq";
    public static final String TOUCHUSURL = "http://www.yiyipai.cn/webview/contact.html"; // 联系我们web-view链接地址
    SharedPreferences sharedPreferences;

    // 创建sharedpreference 来保存一些简单数据
    public Config() {
        sharedPreferences = App.getInstance()
                .getSharedPreferences("config", 0);
    }

    /**
     * 保存本地化
     *
     * @param name
     * @param value
     */
    public void set(String name, String value) {
        Editor editor = sharedPreferences.edit();
        editor.putString(name, value);

        editor.commit();
    }

    public void delete(String name) {
        Editor editor = sharedPreferences.edit();
        editor.remove(name);
        editor.commit();
    }

    public String get(String name) {
        try {
            return sharedPreferences.getString(name, "");
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 取数据
     *
     * @param name
     * @param defValue
     * @return
     */
    public String get(String name, String defValue) {
        try {
            return sharedPreferences.getString(name, defValue);
        } catch (Exception e) {
            return defValue;
        }
    }

    public void setBoolean(String name, Boolean value) {
        Editor editor = sharedPreferences.edit();
        editor.putBoolean(name, value);
        editor.commit();
    }

    public boolean getBoolean(String name) {
        try {
            return sharedPreferences.getBoolean(name, false);
        } catch (Exception e) {
            return false;
        }
    }

    // 查询sp 里面 key为name的值
    public boolean getBoolean(String name, Boolean defValue) {
        try {
            return sharedPreferences.getBoolean(name, defValue);
        } catch (Exception e) {
            return defValue;
        }
    }

    public void setInt(String name, int value) {
        Editor editor = sharedPreferences.edit();
        editor.putInt(name, value);
        editor.commit();
    }

    public int getInt(String name, int def) {
        try {
            return sharedPreferences.getInt(name, def);
        } catch (Exception e) {
            return def;
        }
    }

    public boolean contains(String name) {
        return sharedPreferences.contains(name);
    }
}
