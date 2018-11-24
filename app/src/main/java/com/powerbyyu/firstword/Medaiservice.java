package com.powerbyyu.firstword;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.InputStream;

public class Medaiservice extends Service {
    public MediaPlayer mediaPlayer;
    public class MyBinder extends Binder {
        Medaiservice getService(){
            return Medaiservice.this;//找到后台服务的指针，返回后台服务实例
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    public Medaiservice(){
        //String uriStr = "android.resource://" + this.getPackageName() + "/"+R.raw.medai;
//        Log.v("yuyu",uriStr);
//        Uri uri=Uri.parse(uriStr);

        //Log.v("yuyu",in.toString());
       // mediaPlayer=MediaPlayer.create(this,R.raw.medai);
       // mediaPlayer.setLooping(true);
        Log.i("yuyu","注册media");
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        // 初始化音乐资源
        try {
            System.out.println("create player");
            // 创建MediaPlayer对象
            mediaPlayer = new MediaPlayer();
            mediaPlayer = MediaPlayer.create(Medaiservice.this, R.raw.medai);
//mediaPlayer.start();
        } catch (IllegalStateException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();//停止歌曲播放
            mediaPlayer.release();//释放mediaPlayer资源
        }
    }
    public void playORpuase(){
        Log.v("yuyuyu","播放");
        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();//暂停
        }
        else{
            mediaPlayer.start();//开始
        }
    }
    public void stop(){
        if(mediaPlayer!=null){
            mediaPlayer.stop();//停止
            try{
                mediaPlayer.prepare();//就绪
                mediaPlayer.seekTo(0);//设置歌曲回到最开始
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
