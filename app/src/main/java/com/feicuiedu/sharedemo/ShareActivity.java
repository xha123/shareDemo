package com.feicuiedu.sharedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;

public class ShareActivity extends AppCompatActivity {

    @Bind(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        webView.loadUrl("https://sojump.com/jq/10686276.aspx");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }
        });

    }

    @OnClick({R.id.iv_QQ, R.id.iv_sina, R.id.iv_qzone, R.id.iv_wechat, R.id.iv_facebook})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_QQ:
                break;
            case R.id.iv_sina:
                shareSina();
                break;
            case R.id.iv_qzone:
                break;
            case R.id.iv_wechat:
                break;
            case R.id.iv_facebook:
                break;
        }
    }

    private void shareSina(){

        /**
         * 1. shareSDK进行初始化
         * 2. 设置分享的内容
         * 3. 初始化分享的平台:比如新浪的平台
         * 4. 设置结果的回调
         * 5. 执行分享
         *
         */
        // 1. shareSDK进行初始化
        ShareSDK.initSDK(this);
        // 2. 设置分享的内容
        SinaWeibo.ShareParams shareParams = new SinaWeibo.ShareParams();
        // 设置分享的文本
        shareParams.setText("分平台的新浪");
        // 设置分享的图片
//        shareParams.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        // 3. 初始化分享的平台:比如新浪的平台
        Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        // 4. 设置结果的回调
        weibo.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // 分享完成
                /**
                 * 操作成功
                 */
                Toast.makeText(ShareActivity.this, "分享成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                // 分享错误
                Toast.makeText(ShareActivity.this, "分享错误", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                // 分享取消
                Toast.makeText(ShareActivity.this, "分享取消", Toast.LENGTH_SHORT).show();
            }
        });

        // 5. 执行分享
        weibo.share(shareParams);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
