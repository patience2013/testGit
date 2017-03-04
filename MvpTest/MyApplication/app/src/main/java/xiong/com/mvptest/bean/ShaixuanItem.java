package xiong.com.mvptest.bean;

/**
 * Created by lenovo on 2017/2/16.
 */

public class ShaixuanItem {
    private int isCheck;//0未选中 1选中
    private String text;

    public ShaixuanItem(int isCheck, String text) {
        this.isCheck = isCheck;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(int isCheck) {
        this.isCheck = isCheck;
    }
}
