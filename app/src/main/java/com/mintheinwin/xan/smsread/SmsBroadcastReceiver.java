package com.mintheinwin.xan.smsread;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xan on 6/29/2017.
 *
 */

public class SmsBroadcastReceiver extends BroadcastReceiver {
    private static OnSmsReceivedInterface mOnMessageReceived;
    public static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    // Get Just onlyNumber in the message
    public Pattern p = Pattern.compile("(|^)\\d+");

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ACTION)) {
            Bundle data = intent.getExtras();
            Object[] pdus = (Object[]) data.get("pdus");
            for (int i = 0; i < pdus.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
                String sender = smsMessage.getDisplayOriginatingAddress();
                String phoneNumber = smsMessage.getDisplayOriginatingAddress();
                String messageBody = smsMessage.getMessageBody();
                try {
                    if (mOnMessageReceived != null&&messageBody != null) {
                        Matcher m = p.matcher(messageBody);
                        if (m.find()) {
                            mOnMessageReceived.onSmsReceived(m.group(0));
                        } else {
                        }
                    }
                } catch (Exception e) {
                }
              /*  if (mOnMessageReceived != null) {
                    mOnMessageReceived.onSmsReceived(messageBody);
                }*/

            }
        }

    }
    public void setOnSmsReceivedListener(Context context) {
        this.mOnMessageReceived = (OnSmsReceivedInterface) context;
    }
}
