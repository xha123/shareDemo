package com.feicuiedu.sharedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

public class LoginActivity extends AppCompatActivity {

    /*
    shareSDK的三方登录
    1. 要功能、不要数据
    2. 要数据，不要功能
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        //进行登录
        login();
    }

    private void login(){
        /**
         * 1. 初始化ShareSDK
         * 2. 获取登录的平台
         * 3. 设置结果回调
         * 4. 进行授权：单独的授权，授权并获取用户信息
         *
         * * 移除授权
         */
        ShareSDK.initSDK(this);
        Platform weibo = ShareSDK.getPlatform(QQ.NAME);
        //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
        weibo.setPlatformActionListener(new CustomActionListener() {
            @Override
            void onUIComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                // 遍历输出用户信息
                Iterator ite =hashMap.entrySet().iterator();
                while (ite.hasNext()) {
                    Map.Entry entry = (Map.Entry)ite.next();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    Log.i("TAG",key+"： "+value);
                }
                // 做UI操作等

                // 获取数据之后，可以将数据进行传递
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }

            @Override
            void onUIError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            void onUICancel(Platform platform, int i) {

            }
        });
        weibo.showUser(null);
        weibo.removeAccount();
    }
}
