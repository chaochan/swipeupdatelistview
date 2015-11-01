package com.example.swipeupdatelistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * RecyclerView用のアダプタ
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private Context         mContext;
    private LayoutInflater  mInflater;


    /**
     * コンストラクタ
     */
    public RecyclerAdapter(Context context) {
        mContext    = context;
        mInflater   = LayoutInflater.from(context);
    }


    /**
     * ViewHolderの生成要求
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.recyclerview_item, parent, false));
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }


    /**
     * 表示データ項目数を返す
     */
    @Override
    public int getItemCount() {
        return 10;
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
