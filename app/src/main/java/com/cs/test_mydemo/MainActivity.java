package com.cs.test_mydemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.cs.test_mydemo.adapter.MyAdapter;
import com.cs.test_mydemo.entry.News;
import com.cs.test_mydemo.http.BaseRetrofit;
import com.cs.test_mydemo.http.MnAPIService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private RecyclerView recycle;
    private MyAdapter adapter;
    private List<News.ResultBean.DataBean> list = new ArrayList<>();
    private Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        recycle = (RecyclerView) findViewById(R.id.recycle);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycle.setLayoutManager(layoutManager);
        adapter = new MyAdapter(MainActivity.this, list);
        recycle.setAdapter(adapter);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                MnAPIService mnAPIService = BaseRetrofit.getMnAPIService();
                Call<News> call = mnAPIService.getData("top", "ce18f1786cf2e609acbc076a4b6a2df5");
                call.enqueue(new Callback<News>() {
                    @Override
                    public void onResponse(Call<News> call, Response<News> response) {
                        News body = response.body();
                        if (response.isSuccessful()) {
                            if (body != null) {
                                list.addAll(body.getResult().getData());
                                adapter.notifyDataSetChanged();
                            }else {

                            }

                        }else {
                            Toast.makeText(MainActivity.this, "请求失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<News> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "请求失败的参数"+t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });



                break;
        }
    }
}
