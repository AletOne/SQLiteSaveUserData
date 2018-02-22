package com.example.wang.sqlitesaveuserdata;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ListView;

import com.example.wang.sqlitesaveuserdata.model.FavoriteTv;

import java.util.List;

/**
 * Created by Wang on 2/20/18.
 */

public class ListViewDialog extends Dialog {

    private final Context mContext;
    private ListView mListView;
    private List<FavoriteTv> mData;
    private MDialogAdapter mAdapter;

    public ListViewDialog(@NonNull Context context, List<FavoriteTv> data) {
        super(context);
        mContext = context;
        mData = data;
        initView();
        initData();
    }

    private void initView(){
       View v = View.inflate(mContext, R.layout.content_dialog, null);
       mListView = v.findViewById(R.id.result_lv);
       setContentView(v);
    }

    private void initData(){
        mAdapter = new MDialogAdapter(getLayoutInflater(), mData);
        mListView.setAdapter(mAdapter);
    }
}
