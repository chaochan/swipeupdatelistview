package com.example.swipeupdatelistview;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private RecyclerAdapter     mAdapter;
    private List<ListDataModel> mTestDataList;
    private LinearLayoutManager mLayoutManager;




    /**
     * Activity生成時通知
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Swipeでの更新通知を受け取る
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.main_swiperefreshlayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);

        // RecyclerView初期化
        mRecyclerView = (RecyclerView)findViewById(R.id.main_recycleview);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // RecyclerView用アダプタ生成、初期化
        mAdapter = new RecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);

        // テスト用データ生成(jacksonでjson→Javaオブジェクトへ変換)
        try {
            byte[] data = Utility.loadRawFile(this, R.raw.testdata);
            mTestDataList = new ObjectMapper().readValue(
                    new String(data),
                    new TypeReference<List<ListDataModel>>() {});
        } catch (IOException e) {
            Toast.makeText(this, "テストデータ生成に失敗しました", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "下に引っ張って更新", Toast.LENGTH_SHORT).show();
    }


    /**
     * Swipeでの更新通知
     */
    @Override
    public void onRefresh() {
        // 更新のクルクルを表示するため、少しウェイト
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 追加
                int addSize = addTestData();

                // 表示位置を保持するため、上に追加されたように見せるため、リスト表示位置調整
                mLayoutManager.scrollToPositionWithOffset(addSize, 0);

                // クルクルを終了
                mSwipeRefreshLayout.setRefreshing(false);

                Toast.makeText(
                        MainActivity.this,
                        String.format("%d件のデータを追加", addSize),
                        Toast.LENGTH_SHORT).show();
            }
        }, 500);
    }


    /**
     * テストデータ追加
     * @return 追加したデータの件数
     */
    private int addTestData() {
        // 追加するデータをテストデータから取り出し
        int addSize = new Random().nextInt(Math.min(10, mTestDataList.size())) + 1;
        List<ListDataModel> addedDataList = mTestDataList.subList(0, addSize);

        // 追加
        mAdapter.addData(addedDataList);

        // 追加したデータをテストデータから削除
        for (int i = 0; i < addSize; i++) {
            mTestDataList.remove(0);
        }

        return addSize;
    }
}
