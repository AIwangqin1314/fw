package com.powerbyyu.firstword;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

public class voider {
   private SoundPool sp;
   private HashMap<Integer, Integer> sounddata;
    private Context mcontext;
    public Boolean isLoaded=false;

public voider(Context context){
    mcontext=context;
}
    //初始化声音
    public void InitSound() {
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        sounddata = new HashMap<Integer, Integer>();
        sounddata.put(1, sp.load(mcontext, R.raw.yur, 1));
        sounddata.put(2, sp.load(mcontext, R.raw.hut, 1));
        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener(){
            @Override
            public void onLoadComplete(SoundPool sound,int sampleId,int status){
                isLoaded=true;
                Log.v("yuyuyu","加载完成"+isLoaded.toString());
                Toast.makeText(mcontext,
                        "音效加载完成！",
                        Toast.LENGTH_SHORT);
            }
        });
    }


    public void playSound(int sound, int number) {
        AudioManager am = (AudioManager) mcontext
                .getSystemService(mcontext.AUDIO_SERVICE);
        float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        float volumnCurrent = am.getStreamVolume(AudioManager.STREAM_MUSIC);
        float volumnRatio = volumnCurrent / audioMaxVolumn;
        Log.v("yuyuyu","播放45");
        sp.play(sounddata.get(sound),
                volumnRatio,// 左声道音量
                volumnRatio,// 右声道音量
                1, // 优先级
                number,// 循环播放次数
                1);// 回放速度，该值在0.5-2.0之间 1为正常速度
    }

//        //2.播放声音
//        if(isLoaded==true)
//    playSound(1,1);
}
