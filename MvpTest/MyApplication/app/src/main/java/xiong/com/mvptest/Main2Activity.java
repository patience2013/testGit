package xiong.com.mvptest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import xiong.com.mvptest.bean.User;

public class Main2Activity extends AppCompatActivity {
    private Button bt_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bt_show = (Button) findViewById(R.id.bt_show);
        User user = (User) getIntent().getSerializableExtra("user");
        bt_show.setText(user.getRealname());
    }
}
