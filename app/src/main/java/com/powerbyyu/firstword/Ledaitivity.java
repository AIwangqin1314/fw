package com.powerbyyu.firstword;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class Ledaitivity extends Activity{

    public static Context context ;
    private Button button;
    private TextView textView;
    private TcpClient ledclient=null;
    private String  serverIP = "192.168.1.15";
    private int serverPort = 8080;
    private String tcpdata="  ";
    private EditText editText;
    private TcpServer tcpServer;
    private int Port = 8080;
    private MyBroadcastReceiver myBroadcastReceiver;
    private final MyHandler myHandler = new MyHandler(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledactivity);
        context = this;
        editText=(EditText)findViewById(R.id.blackOutNumber);
        button=(Button)findViewById(R.id.ledbuttom);
        textView=(TextView)findViewById(R.id.textView2);
        textView.setText("几个号我要开会贵子子子子非哟一 ");
        handlerlinit();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_main=new Intent(Ledaitivity.this,MainActivity.class);
//                startActivity(intent_main);
                tcpdata=editText.getText().toString();
                Log.v("yuyu",tcpdata);
                textView.setText(tcpdata);
                //ledclient.send(tcpdata);
                //
                new Thread( new Runnable(){
                    @Override
                    public void run(){
                        tcpServer.SST.get(1).send("4657gyfyfED通过打广告法规");
                        Log.v("yuyuyu","发送消失");
                    }
                }).start();
                //
            }
        });
        tcpServer=new TcpServer(Port);
        new Thread(tcpServer).start();
//        ledclient=new TcpClient(serverIP,serverPort);
//       // exec.execute(ledclient);
//       new  Thread(ledclient).start();
//        //new Thread(new TcpClient(serverIP,serverPort)).start();
    }
private void handlerlinit(){
//        handler=new Handler(){
//            public void handleMessage(Message msg){
//                super.handleMessage(msg);
//                switch (msg.what){
//                    case 1:
//                        Log.v("yuyuyu","成功");
//                        textView.setText("cggfuyrt");
//                        break;
//                }
//            }
//        };

    IntentFilter intentFilter = new IntentFilter("tcpClientReceiver");
    myBroadcastReceiver =new MyBroadcastReceiver();
    registerReceiver(myBroadcastReceiver,intentFilter);
}
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String mAction = intent.getAction();
            Log.v("yuyuyu","进入广播");
            switch (mAction){
                case "tcpClientReceiver":
                    String msg = intent.getStringExtra("tcpClientReceiver");
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = msg;
                    myHandler.sendMessage(message);
                    Log.v("yuyuyu","发送客户端接受");
                    break;
                case "tcpServerReceiver":
                    String msgss = intent.getStringExtra("tcpServerReceiver");
                    Message messagess = Message.obtain();
                    messagess.what = 2;
                    messagess.obj = msgss;
                    myHandler.sendMessage(messagess);
                    Log.v("yuyuyu","发送服务器端data");
                    break;
            }
        }
    }
    private class MyHandler extends android.os.Handler{
        private WeakReference<Ledaitivity> mActivity;

        MyHandler(Ledaitivity activity){
            mActivity = new WeakReference<Ledaitivity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            Log.v("yuyuyu","进入handle");
            if (mActivity != null){
                switch (msg.what){
                    case 1:
                        Log.v("yuyuyu","输出dd");
                        textView.setText(msg.obj.toString());
                        break;
                    case 2:
                        textView.setText(msg.obj.toString());
                        break;
                }
            }
        }
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
