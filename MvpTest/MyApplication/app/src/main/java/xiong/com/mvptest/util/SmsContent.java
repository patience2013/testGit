package xiong.com.mvptest.util;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsContent extends ContentObserver {
	public static final String SMS_URI_INBOX = "content://sms/inbox";
	private Activity activity = null;
	private String smsContent = "";
	private EditText verifyText = null;
	
	public SmsContent(Activity activity, Handler handler, EditText verifyText) {
		super(handler);
		this.activity = activity;
		this.verifyText = verifyText;
	}

	@Override
	public void onChange(boolean selfChange) {
		super.onChange(selfChange);
		Cursor cursor = null;// 光标
		// 读取收件箱中指定号码的短信
		cursor = activity.managedQuery(Uri.parse(SMS_URI_INBOX), new String[] {
				"_id", "address", "body", "read" }, "address=? and read=?",
				new String[] { "1065902020720238565", "0" }, "date desc");
		if (cursor != null) {// 如果短信为未读模式
			cursor.moveToFirst();
			if (cursor.moveToFirst()) {
				String smsbody = cursor
						.getString(cursor.getColumnIndex("body"));
				System.out.println("smsbody=======================" + smsbody);

				// 这里我是要获取自己短信服务号码中的验证码~~
				Pattern pattern = Pattern.compile("[a-zA-Z0-9]{4}");
				Matcher matcher = pattern.matcher(smsbody);// String
				// body="测试验证码2346ds";
				if (matcher.find()) {
					smsContent = matcher.group().substring(0, 4);// 获取短信的内容
					verifyText.setText(smsContent);
				}
			}
		}
	}
}
