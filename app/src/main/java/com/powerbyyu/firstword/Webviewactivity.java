package com.powerbyyu.firstword;

import android.app.Activity;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class Webviewactivity extends Activity {
    private WebView webView;
    private Button button;
    private int nubber=0;
    private String ual="http://118.24.196.206:8080/o/";
    private String ualmain="http://118.24.196.206:8080/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewactivity);
        webView=(WebView)findViewById(R.id.webview);
        button=(Button)findViewById(R.id.button);
        initWebView();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nubber++;
                if (nubber==2){nubber=0;}
                if(nubber==1){webView.loadUrl(ual);}
                else {webView.loadUrl(ualmain);}
            }
        });
        webView.loadUrl(this.ualmain);
    }
    private void initWebView() {
        webView.setWebChromeClient(new WebChromeClient());

        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //webSettings.setUserAgentString(UA);
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置适应Html5的一些方法
        webSettings.setDomStorageEnabled(true);

        //设置自适应屏幕，两者合用
//       webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
//        webSettings.setLoadWithOverviewMode(true);
        //webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        // webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

//        webSettings.setTextZoom(40);
        //webSettings.setMinimumFontSize(80);

//        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);// 不使用缓存
        //webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);//把html中的内容放大webview等宽的一列中
//        webSettings.setAppCacheEnabled(true);
//        webSettings.setDomStorageEnabled(true);

        // webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        //     webView.setHorizontalScrollBarEnabled(false);
        //webView.setInitialScale(150);//m5
        webView.setInitialScale(115);//m3


//






        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        //webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        //webSettings.setAllowFileAccess(true); //设置可以访问文件
        //webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        //webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        //webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

//        webSettings.setUseWideViewPort(true);
//        webSettings.setLoadWithOverviewMode(true);

        webSettings.setMediaPlaybackRequiresUserGesture(false);//禁止触摸播放视频


        webView.setWebViewClient(new WebViewClient() {
//            @Override
////            public void onPageFinished(WebView view, String url) {
////                super.onPageFinished(view, url);
////            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();//忽略ssl证书错误,继续加载网页
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String javascript = "javascript:function ResizeImages() {" +
                        "var myimg,oldwidth;" +
                        "var maxwidth = document.body.clientWidth;" +
                        "for(i=0;i <document.images.length;i++){" +
                        "myimg = document.images[i];" +
                        "if(myimg.width > maxwidth){" +
                        "oldwidth = myimg.width;" +
                        "myimg.width = maxwidth;" +
                        "}" +
                        "}" +
                        "}";
                //String width = String.valueOf(ScreenUtils.widthPixels(mContext));
                view.loadUrl(javascript);
                view.loadUrl("javascript:ResizeImages();");
            }

//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                LogUtil.log("shouldOverrideUrlLoading", url);
//                view.loadUrl(url);
//
//                return true;
//            }
        });
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
