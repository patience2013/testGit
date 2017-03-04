package xiong.com.mvptest.bean;

import java.io.Serializable;

/**
 * Created by 62416 on 2016/10/12.
 */

public class User implements Serializable{
    public String id; // 编号
    public String name; // 昵称
    public String realname; // 真实姓名
    public String mail; // 邮箱
    public String mobile; // 手机账户
    public String head; // 头像
    public int isVip; // vip帐户
    public int sex; // 0：女 、 1：男
    public long birthday; // 生日
    public String tel; // 手机号
    public int type; // 用户认证类型 0：默认，没有认证 1：个人认证用户，2：企业认证用户
    public int account_status;
//    public CountInfo count; // 统计信息

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public int getIsVip() {
        return isVip;
    }

    public void setIsVip(int isVip) {
        this.isVip = isVip;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getAccount_status() {
        return account_status;
    }

    public void setAccount_status(int account_status) {
        this.account_status = account_status;
    }
}
