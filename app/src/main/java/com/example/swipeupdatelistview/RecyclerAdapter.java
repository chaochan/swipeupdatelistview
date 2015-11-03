package com.example.swipeupdatelistview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * RecyclerView用のアダプタ
 */
public class RecyclerAdapter extends RecyclerView.Adapter {

    private LayoutInflater              mInflater;
    private ArrayList<ListDataModel>    mShowDataList;


    /**
     * コンストラクタ
     */
    public RecyclerAdapter(Context context) {
        mInflater       = LayoutInflater.from(context);
        mShowDataList   = new ArrayList<>();
    }


    /**
     * ViewHolderの生成要求
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mInflater.inflate(R.layout.recyclerview_item, parent, false));
    }


    /**
     * 表示View用のデータ設定要求
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListDataModel data = mShowDataList.get(mShowDataList.size() - position - 1);    // リストの降順で表示させる
        ((ViewHolder)holder).bindData(data);
    }


    /**
     * 表示データ項目数を返す
     */
    @Override
    public int getItemCount() {
        return mShowDataList.size();
    }


    /**
     * リスト表示を行うデータを追加する(複数)
     */
    public void addData(List<ListDataModel> list) {
        // 表示データ追加
        mShowDataList.addAll(list);
        notifyDataSetChanged();
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;
        private TextView mDateView;
        private TextView mMessageView;

        /**
         * コンストラクタ
         */
        public ViewHolder(View itemView) {
            super(itemView);

            mNameView    = (TextView)itemView.findViewById(R.id.text_name);
            mDateView    = (TextView)itemView.findViewById(R.id.text_date);
            mMessageView = (TextView)itemView.findViewById(R.id.text_message);
        }

        /**
         * 表示データを設定する
         */
        public void bindData(ListDataModel data) {
            mNameView.setText(data.name);
            mDateView.setText(data.date);
            mMessageView.setText(data.message);
        }
    }
}
