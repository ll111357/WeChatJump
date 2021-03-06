package com.ll.wechatjump;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.ll.wechatjump.hack.Hack;
import com.ll.wechatjump.uctoast.ListenClipboardService;

public class MainActivity extends AppCompatActivity {


    private final static String KEY_CONTENT = "content";
    private TextView mTextView;
    private ImageView imageView;
    public static void startForContent(Context context, String content) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(KEY_CONTENT, content);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        mTextView = (TextView) findViewById(R.id.text_view);
        imageView= (ImageView) findViewById(R.id.image);
        Intent intent = getIntent();
        Utils.printIntent("MainActivity::onCreate()", intent);

        tryToShowContent(intent);
        ListenClipboardService.start(this);
        findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bitmap bitmap=ScreenShotUtils.takeScreenShot(MainActivity.this);
                Bitmap bitmap=getBitmap();
                imageView.setImageBitmap(bitmap);
                Hack hack=new Hack();
                hack.find(bitmap);
//                showView();
            }
        });
    }

    private Bitmap getBitmap(){
        return BitmapFactory.decodeResource(getResources(),R.mipmap.test_4);
    }


    private void showView(){
        ListenClipboardService.startForTest(this,"启动成功");
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        Utils.printIntent("MainActivity::onNewIntent()", intent);

        tryToShowContent(intent);
    }

    private void tryToShowContent(Intent intent) {
        String content = intent.getStringExtra(KEY_CONTENT);
        if (!TextUtils.isEmpty(content)) {
            mTextView.setText(content);
        }
    }

}
