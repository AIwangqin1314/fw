package com.powerbyyu.firstword;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class Ledaitivity extends Activity{

    public static Context context ;
    private Button button;
    private Button soupool;
    private TextView textView;
    private TcpClient ledclient=null;
    private String  serverIP = "192.168.1.15";
    private int serverPort = 8080;
    private String tcpdata="sjiauhihihi登记卡机";
    private EditText editText;
    private EditText cilenttext;
    private TcpServer tcpServer;
    private int Port = 8080;
    private MyBroadcastReceiver myBroadcastReceiver;
    private final MyHandler myHandler = new MyHandler(this);
    private Medaiservice.MyBinder medaiservice=null;
    private String clientnum="0";
    private int clientnumber=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ledactivity);
        context = this;
        editText=(EditText)findViewById(R.id.clienttblackOutNumberext);
        cilenttext=(EditText)findViewById(R.id.clienttext);
        button=(Button)findViewById(R.id.ledbuttom);
        textView=(TextView)findViewById(R.id.textView2);
        textView.setText(getHostIP());
        handlerlinit();
        mediaplerint();
        soupoolhini();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent_main=new Intent(Ledaitivity.this,MainActivity.class);
//                startActivity(intent_main);
                tcpdata=editText.getText().toString();
                Log.v("yuyu",tcpdata);
                new Thread( new Runnable(){
                    @Override
                    public void run(){
                        Log.v("yuyuyus","打印"+Integer.parseInt(clientnum));
                        if (tcpServer.SST.size()==0||tcpServer.SST==null){Log.v("yuyuyus","无连接"+Integer.parseInt(clientnum));}
                        else {
                            if (tcpServer.SST.size()<Integer.parseInt(clientnum)){Log.v("yuyuyus","客户端小于"+Integer.parseInt(clientnum));}
                            else {
                                tcpServer.SST.get(Integer.parseInt(clientnum)).send(tcpdata);
                                Log.v("yuyuyus","发送"+Integer.parseInt(clientnum));
                            }
                        }

                    }
                }).start();

                //medaiservice.getService
                //medaiservice.getService().soupool();
                medaiservice.getService().playORpuase();
               //jhjj.playORpuase();
            }
        });
        tcpServer=new TcpServer(Port);
        new Thread(tcpServer).start();

//        ledclient=new TcpClient(serverIP,serverPort);
//       // exec.execute(ledclient);
//       new  Thread(ledclient).start();
//        //new Thread(new TcpClient(serverIP,serverPort)).start();
    }
    private void soupoolhini(){
        soupool=(Button)findViewById(R.id.button3);
        soupool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clientnum=cilenttext.getText().toString();
                if (clientnum.equals(""))clientnum="0";
               //Toast toast= Toast.makeText(Ledaitivity,clientnum,Toast.LENGTH_SHORT).show();
                Log.v("yuyuyus","客服端设置"+cilenttext.getText().toString());
                medaiservice.getService().soupool(2,2);
            }
        });
    }
    private void mediaplerint(){
        Intent intent=new Intent(this,Medaiservice.class);
        //startService(intent);
        bindService(intent,sc,BIND_AUTO_CREATE);
        //mediaPlayer=new MediaPlayer();
        //mediaPlayer=MediaPlayer.create(this,R.raw.medai);
        //mediaPlayer.start();
        //mediaPlayer.prepare();
        //mediaPlayer.isLooping();
    }
    private ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            medaiservice=(Medaiservice.MyBinder)service;
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            medaiservice=null;
        }
    };
    private void handlerlinit(){
        IntentFilter intentFilter = new IntentFilter("tcpServerReceiver");
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
                case "ip+p":
                    String msgsip = intent.getStringExtra("tcpServerReceiver");
                    Message messageip = Message.obtain();
                    messageip.what = 3;
                    messageip.obj = messageip;
                    myHandler.sendMessage(messageip);
                    Log.v("yuyuyus","ip+ppp斤斤计较");
                    clientnumber++;
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
                    case 3:
                        textView.setText(getHostIP()+msg.obj.toString()+clientnumber);

                        break;
                }
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBroadcastReceiver);
        if (1==1){unbindService(sc);}
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
       * 获取ip地址
          * @return
          */
        public String getHostIP() {
         String hostIp = null;
         try {
             Enumeration nis = NetworkInterface.getNetworkInterfaces();
            InetAddress ia = null;
             while (nis.hasMoreElements()) {
             NetworkInterface ni = (NetworkInterface) nis.nextElement();
             Enumeration<InetAddress> ias = ni.getInetAddresses();
             while (ias.hasMoreElements()) {
             ia = ias.nextElement();
             if (ia instanceof Inet6Address) {
             continue;// skip ipv6
                 }
             String ip = ia.getHostAddress();
            if (!"127.0.0.1".equals(ip)) {
                     hostIp = ia.getHostAddress();
                    break;
                                    }
                                                         }
                                                 }
         }
         catch (SocketException e) {
                    Log.i("FuncTcpServer", "SocketException");
                     e.printStackTrace();
         }
        return hostIp;
        }

}
