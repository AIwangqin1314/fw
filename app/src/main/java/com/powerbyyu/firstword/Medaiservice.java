package com.powerbyyu.firstword;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;


import java.io.InputStream;
import java.util.HashMap;

public class Medaiservice extends Service {
    public MediaPlayer mediaPlayer;
    private SoundPool sp;
    private voider voiderii;
    private HashMap<Integer, Integer> sounddata;
    public Boolean isLoaded=false;
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
        voiderii=new voider(Medaiservice.this);

        //String uriStr = "android.resource://" + this.getPackageName() + "/"+R.raw.medai;
//        Log.v("yuyu",uriStr);
//        Uri uri=Uri.parse(uriStr);

        //Log.v("yuyu",in.toString());
       // mediaPlayer=MediaPlayer.create(this,R.raw.medai);
       // mediaPlayer.setLooping(true);
        Log.i("yuyu","注册media");
    }
    //初始化声音
//    public void InitSound() {
//        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
//        sounddata = new HashMap<Integer, Integer>();
//        sounddata.put(1, sp.load(this, R.raw.yur, 1));
//        sounddata.put(2, sp.load(this, R.raw.yur, 1));
//        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
//            @Override
//            public void onLoadComplete(SoundPool sound,int sampleId,int status){
//                isLoaded=true;
//                Log.v("yuyuyu","加载完成11"+isLoaded.toString());
////                Toast.makeText(this,
////                        "音效加载完成！",
////                        Toast.LENGTH_SHORT);
//            }
//        });
//    } public void playSound(int sound, int number) {
//        AudioManager am = (AudioManager) this
//                .getSystemService(this.AUDIO_SERVICE);
//        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
//        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
//        float volumnRatio = volumnCurrent / audioMaxVolumn;
//        Log.v("yuyuyu","播放45");
//        sp.play(sounddata.get(sound),
//                volumnRatio,// 左声道音量
//                volumnRatio,// 右声道音量
//                1, // 优先级
//                number,// 循环播放次数
//                1);// 回放速度，该值在0.5-2.0之间 1为正常速度
//    }
//
//    //        //2.播放声音
////        if(isLoaded==true)
////    playSound(1,1);
    @Override
    public void onCreate() {
        voiderii.InitSound();
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
    //public void hiy(){if (isLoaded){Log.v("yuyuyu","无加载");}else {InitSound();Log.v("yuyuyu","初始胡");}}
    public void soupool(){
        Log.v("yuyuyu",isLoaded.toString());
        if (voiderii.isLoaded){voiderii.playSound(1,1);Log.v("yuyuyu","播放");}
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
