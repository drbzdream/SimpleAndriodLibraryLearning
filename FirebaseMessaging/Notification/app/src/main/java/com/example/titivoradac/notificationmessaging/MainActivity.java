package com.example.titivoradac.notificationmessaging;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            for (String key : getIntent().getExtras().keySet()) {
                Object value = getIntent().getExtras().get(key);
                Log.d(TAG, "Key: " + key + " Value: " + value);
            }
        }

//        findViewById(R.id.subscribeButton).setOnClickListener(this);
//        findViewById(R.id.unsubscribeButton).setOnClickListener(this);
//        findViewById(R.id.logTokenButton).setOnClickListener(this);

        mTextView = (TextView) findViewById(R.id.txtcontent);

    }

    public void showToken(View view) {
        // แสดง token มาให้ดูหน่อยเสะ
        mTextView.setText(FirebaseInstanceId.getInstance().getToken());
        Log.i("token: ", FirebaseInstanceId.getInstance().getToken());
    }
    public void subscribe(View view) {
        // สับตะไคร้หัวข้อ news
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        mTextView.setText("subscribed");
    }
    public void unsubscribe(View view) {
        // ยกเลิกสับตะไคร้หัวข้อ news
        FirebaseMessaging.getInstance().unsubscribeFromTopic("news");
        mTextView.setText("unsubscribed");
    }


}
