package com.powerbyyu.firstword;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity{
    private int birthday=0;
    private int date=0;
    private int month=0;
    private TextView showtext;
    private ImageView wechatview;
    private Button   inter;
    private Button   led;
    private EditText inputword;
    private  AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        showtext=(TextView)findViewById(R.id.showtext);
        wechatview=(ImageView)findViewById(R.id.wechatview);
        inter=(Button)findViewById(R.id.inter);
        led=(Button)findViewById(R.id.button2);
        led.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_led=new Intent(MainActivity.this,Ledaitivity.class);
                startActivity(intent_led);
            }
        });
        inputword=(EditText)findViewById(R.id.inputword);
        inittext();
        initbuttun();
        initwechatview();
        init_inputword();
    }
    private void inittext()//初始化公式显示框
    {
        String text="魔法测试\n" +
                "请按以下规则计算，然后输入计算后的数字，我将告诉你的生日。\n" +
                "规则：（您生日的月份x4+9）x25+您出生的日期=计算的结果\n" +
                "列如：08.10  (8x4+9)x25+10";
        //showtext.setText(text);
        showtext.getPaint().setFakeBoldText(true);//加粗

        SpannableStringBuilder sb = new SpannableStringBuilder(text);
        sb.setSpan(new ForegroundColorSpan(Color.RED),0,4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ForegroundColorSpan(Color.MAGENTA),4,35, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ForegroundColorSpan(Color.BLUE), 35, 66, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//背景色
        sb.setSpan(new BackgroundColorSpan(Color.GREEN), 66, sb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        showtext.setTextSize(15);
        showtext.setText(sb);

    }
    private void initbuttun(){//初始化按键 添加监听
        inter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rebackmessage();

            }
        });
    }
    private  void initwechatview(){//初始化微信二维码显示
        wechatview.setScaleType(ImageView.ScaleType.CENTER_CROP);
        wechatview.setImageResource(R.drawable.bir);
    }
    private void init_inputword(){//初始化输入框
         alertDialog=new AlertDialog.Builder(this)//?????????????
                                    .setIcon(R.mipmap.ic_launcher)
                                    .setTitle("计算结果")
                                    .setCancelable(false)//点击框外不消失
                                    .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .setNegativeButton("返回", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .create();
    }
    private void rebackmessage(){//计算输入值，返回弹窗显示
        inputword.clearFocus();
        //隐藏软金牌
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

        birthday=Integer.parseInt(inputword.getText().toString())-225;
        month=birthday/100;
        date=birthday%100;
        if(birthday<225){
            alertDialog.setTitle("错误");
            alertDialog.setMessage("您输入的计算结果错误，请输入正确的计算结果！");
            alertDialog.show();
            return;
        }
        //Toast.makeText(MainActivity.this,month+" "+date,Toast.LENGTH_LONG).show();
        if(month<1||month>12||date<1||date>31){
            alertDialog.setTitle("错误");
            alertDialog.setMessage("您输入的计算结果错误，请输入正确的计算结果！");
            alertDialog.show();
            return;
        }
        if (month==2&&(date<1||date>28)){
            alertDialog.setTitle("错误");
            alertDialog.setMessage("您输入的计算结果错误，请输入正确的计算结果！");
            alertDialog.show();
            return;
        }
        if (month%2==0&&date==31){
            alertDialog.setTitle("错误");
            alertDialog.setMessage("您输入的计算结果错误，请输入正确的计算结果！");
            alertDialog.show();
            return;
        }
        alertDialog.setMessage("你的生日是"+month+"月"+date+"号\n"+"生日灯已开启！");
        alertDialog.show();
        if (month==11&&date==5)
        {
            Intent intent=new Intent(MainActivity.this,Webviewactivity.class);
            startActivity(intent);
        }
    }
    private void regetfocus(){
        inputword.setFocusable(true);

        inputword.setFocusableInTouchMode(true);

        inputword.requestFocus();
        birthday=0;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
