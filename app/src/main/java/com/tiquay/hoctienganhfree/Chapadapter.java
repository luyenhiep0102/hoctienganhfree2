package com.tiquay.hoctienganhfree;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tiquay.hoctienganhfree.model.ChapTruyen;

import java.util.ArrayList;

public class Chapadapter extends BaseAdapter {
    private int layout;
    private Context context;
    private ArrayList<ChapTruyen> list;

    public Chapadapter(int layout, Context context, ArrayList<ChapTruyen> list) {
        this.layout = layout;
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder{
        TextView txtTenChap;
        ImageView imgIcon;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();

            holder.txtTenChap = convertView.findViewById(R.id.txtTenChap);
            holder.imgIcon = convertView.findViewById(R.id.imgicon);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ChapTruyen chap = (ChapTruyen) list.get(position);

        holder.txtTenChap.setText(chap.getTenChapTV());
        holder.imgIcon.setImageResource(chap.getHinh());
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list);
//        convertView.startAnimation(animation);
//        Animation animation = AnimationUtils.loadAnimation(context, R.anim.list);
//        convertView.startAnimation(animation);
        return convertView;
    }
}
