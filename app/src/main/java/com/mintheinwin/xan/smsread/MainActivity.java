package com.mintheinwin.xan.smsread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
/**
 * Created by xan on 6/29/2017.
 *
 */
public class MainActivity extends AppCompatActivity implements OnSmsReceivedInterface {
    TextView txtMessageOTP_Token;
    SmsBroadcastReceiver receiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMessageOTP_Token = (TextView) findViewById(R.id.smsOTP);
        receiver=new SmsBroadcastReceiver();
        receiver.setOnSmsReceivedListener(this);

    }

    @Override
    public void onSmsReceived(String messageText) {
        txtMessageOTP_Token.setText(messageText);
    }
}
