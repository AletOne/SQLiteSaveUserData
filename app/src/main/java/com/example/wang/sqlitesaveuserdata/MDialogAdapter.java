package com.example.wang.sqlitesaveuserdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.wang.sqlitesaveuserdata.model.FavoriteTv;

import java.util.List;

/**
 * Created by Wang on 2/20/18.
 */

public class MDialogAdapter extends BaseAdapter {
    private List<FavoriteTv> mData;

    private LayoutInflater mInflater;

    public MDialogAdapter(LayoutInflater inflater, List<FavoriteTv> data){
        mInflater = inflater;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.idText = convertView.findViewById(R.id.item_id_textview);
            holder.nameText = convertView.findViewById(R.id.item_name_textview);
            holder.emailText = convertView.findViewById(R.id.item_email_textview);
            holder.favoriteText = convertView.findViewById(R.id.item_ft_textview);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        FavoriteTv favoriteTv = mData.get(position);
        holder.idText.setText(favoriteTv.getId() + "");
        holder.nameText.setText(favoriteTv.getName().equals("") ? "" : favoriteTv.getName());
        holder.emailText.setText(favoriteTv.getEmail().equals("") ? "" : favoriteTv.getEmail());
        holder.favoriteText.setText(favoriteTv.getFavoriteTv().equals("") ? "" : favoriteTv.getFavoriteTv());

        return convertView;
    }

    class ViewHolder{
        TextView idText;
        TextView nameText;
        TextView emailText;
        TextView favoriteText;
    }
}
