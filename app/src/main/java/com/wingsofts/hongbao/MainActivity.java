package com.wingsofts.hongbao;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final String[] datas= {"好友1","好友2","好友3","好友4","好友1","好友2","好友3","好友4","好友1","好友2","好友3","好友4","好友1","好友2","好友3","好友4","好友1","好友2","好友3","好友4"};
    private HBListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (HBListView) findViewById(R.id.lv);

        final View header = LayoutInflater.from(this).inflate(R.layout.view_header, null);
        mListView.addHeaderView(header);
        final MyImageView image = (MyImageView) header.findViewById(R.id.imageView);
        header.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mListView.changeSize(image);
                header.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        mListView.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,datas));
        mListView.setOnSuccessListener(new HBListView.OnSuccessListener() {
            @Override
            public void onSuccess() {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("恭喜中奖！抽到了疼逊聊天气泡！").setNegativeButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });
    }
}
