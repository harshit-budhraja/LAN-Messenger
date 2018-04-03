package com.arachnisapps.lanmessenger;

import android.content.DialogInterface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServerMessaging extends AppCompatActivity {
    private String chat=null;
    WifiManager wm;
    private String myIP = "127.0.0.1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servermessaging);
        wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        myIP = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        final TextView chatarea = (TextView) findViewById(R.id.chatarea);
        final EditText messagetext = (EditText) findViewById(R.id.messagetext);
        Button sendmessage = (Button) findViewById(R.id.sendmessage);
        chatarea.setMovementMethod(new ScrollingMovementMethod());

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newmessage = messagetext.getText().toString();
                chat = chatarea.getText().toString() + "\n" + myIP + ": " + newmessage;
                chatarea.setText(chat);
                chatarea.setTextColor(getResources().getColor(R.color.black));
                messagetext.setText(null);
            }
        });

    }

    protected void showDialog(String myTitle, String myContent){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(myContent)
                .setTitle(myTitle)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
