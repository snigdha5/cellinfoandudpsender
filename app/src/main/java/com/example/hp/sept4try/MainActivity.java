package com.example.hp.sept4try;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.CellInfo;
import android.telephony.TelephonyManager;
import android.view.ActionMode;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    TextView textIP =(TextView)findViewById(R.id.text2);
    TextView textPort = (TextView)findViewById(R.id.text4);
    TextView textMsg = (TextView)findViewById(R.id.textmsg);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        String text = "Some text for our TextView";
        textView1.setText(text);

        //Get the instance of TelephonyManager
        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        List<CellInfo> neighboringCellInfo = tm.getAllCellInfo();
        text = "Size of list is " + neighboringCellInfo.size();
        for (int i = 0; i < neighboringCellInfo.size(); i++) {
            text += "\n\n";
            text += "\n" + neighboringCellInfo.get(i);
        }
        textView1.setText(text);//displaying the information in the textView

//        changetext();
    }

    private void changetext() {
        textIP.setText("14.139.54.203");
        textPort.setText("5077");
        textMsg.setText("Sending Packets");
    }

    public void sendData(View view){
        Context context = getApplicationContext();

        String host = textIP.getText().toString();
        String port = textPort.getText().toString();
        String msg = textMsg.getText().toString();

        String uriString = "udp://" + host + ":" + port + "/";

        uriString += Uri.encode(msg);
        Uri uri = Uri.parse(uriString);
        Intent intent =(new Intent(Intent.ACTION_SENDTO,uri));
        intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(intent);


    }
}
