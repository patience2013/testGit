package xiong.com.mvptest.acivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import xiong.com.mvptest.R;
import xiong.com.mvptest.bean.User;

/**
 * Created by 62416 on 2016/10/13.
 */
public class SecoundActivity extends AppCompatActivity {
    private User user;
    private TextView tv_show;
    private Button bt_back;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.secound);
        bt_back = (Button) findViewById(R.id.bt_back);
        user = (User) getIntent().getSerializableExtra("User");
        bt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
