package com.arachnisapps.lanmessenger;

import android.content.DialogInterface;
import android.media.audiofx.BassBoost;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Messaging extends AppCompatActivity {
    private String chat=null;
    WifiManager wm;
    private String myIP = "127.0.0.1";
    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new Client();
        setContentView(R.layout.activity_messaging);
        wm = (WifiManager) getSystemService(WIFI_SERVICE);
        myIP = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
        Toast.makeText(getApplicationContext(),(CharSequence) Selection.hostIP,Toast.LENGTH_SHORT).show();

        final TextView chatarea = (TextView) findViewById(R.id.chatarea);
        final EditText messagetext = (EditText) findViewById(R.id.messagetext);
        Button sendmessage = (Button) findViewById(R.id.sendmessage);
        chatarea.setMovementMethod(new ScrollingMovementMethod());

        sendmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newmessage = messagetext.getText().toString();
                chat = chatarea.getText().toString() + "\n" + myIP + ": " + newmessage;
                sendtoserver(Selection.hostIP,chat);
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