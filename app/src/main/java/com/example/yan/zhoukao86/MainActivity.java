package com.example.yan.zhoukao86;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fynn.fluidlayout.FluidLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        private EditText editText;
        private Button but_sousuo,but_clear;
        private FluidLayout fluidLayout;
        private TagFlowLayout tag_sousuo;
        private List<String> list1;

        private KeyAdapter lastAdapter;

        private String mNAME[] = {
                "考拉三周年人气热销榜",
                "电动牙刷",
                "尤妮佳",
                "豆豆鞋",
                "沐浴露",
                "日东红茶",
                "榴莲",
                "电动牙刷",
                "雅诗莱黛",
                "豆豆鞋"

        };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initview();
    }

    private void initview() {
        editText = findViewById(R.id.edit_sousuo);
        but_clear = findViewById(R.id.but_clear);
        but_sousuo = findViewById(R.id.but_sousuo);
        fluidLayout = findViewById(R.id.liushi);
        //流式布局
        list1 = new ArrayList<>();
        list1.add("流感");
        list1.add("失眠");
        list1.add("布诺芬");
        list1.add("鼻炎");

        tag_sousuo = findViewById(R.id.tag_sousuo);

        lastAdapter = new KeyAdapter(list1);
        tag_sousuo.setAdapter(lastAdapter);

        tag_sousuo.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                String key = lastAdapter.getItem(position);
                return true;
            }
        });

        //搜索
        but_sousuo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keywords = editText.getText().toString();

                if (!TextUtils.isEmpty(keywords)){
                    list1.add(keywords);
                    lastAdapter.notifyDataChanged();
                }
            }
        });

        //历史清除
        but_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list1.clear();
                lastAdapter.notifyDataChanged();
            }
        });


        for (int i = 0;i<mNAME.length;i++){
            FluidLayout.LayoutParams params = new FluidLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(12,12,12,12);
            TextView textView = new TextView(this);

            textView.setText(mNAME[i]);
            textView.setTextColor(Color.BLACK);
            textView.setBackgroundResource(R.drawable.flow_yangshi);

            fluidLayout.addView(textView,params);
        }
    }


     class KeyAdapter extends TagAdapter<String> {
         public KeyAdapter(List<String> datas) {
             super(datas);
         }
         @Override
         public View getView(FlowLayout parent, int position, String s) {
             TextView textView = new TextView(MainActivity.this);
             textView.setText(s);
             textView.setBackgroundResource(R.drawable.flow_yangshi);

             return textView;
         }
     }
}
