package com.powerbyyu.firstword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Ledaitivity extends Activity{
    private Button button;
    private TextView textView;
    private TcpClient ledclient=null;
    private String  serverIP = "192.168.1.17";
    private int serverPort = 8080;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledactivity);
        button=(Button)findViewById(R.id.ledbuttom);
        textView=(TextView)findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_main=new Intent(Ledaitivity.this,MainActivity.class);
//                startActivity(intent_main);
ledclient.send("fgyhtfyfg");
            }
        });
        ledclient=new TcpClient(serverIP,serverPort);
       // exec.execute(ledclient);
       new  Thread(ledclient).start();
        //new Thread(new TcpClient(serverIP,serverPort)).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
