package com.example.hp.sept4try;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class SendtoUriActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        final Uri uri = intent.getData();

        UdpSender udpSender = new UdpSender();
        udpSender.SendTo(this.getApplicationContext(), uri);
        finish();
    }
}
