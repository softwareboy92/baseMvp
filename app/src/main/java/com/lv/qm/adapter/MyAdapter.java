package com.lv.qm.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.lv.qm.R;

import java.util.List;

/**
 * 作者：create by albert on 2018/11/28 10:44 PM
 * 邮箱：lvhzongdi@iclou.com
 */
public class MyAdapter extends BaseExpandableListAdapter {


    private List<String> groupList;//外层的数据源
    private List<List<String>> childList;//里层的数据源
    private Context context;

    public MyAdapter(Context context, List<String> groupList, List<List<String>> childList) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
    }


    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return childList.get(i).size();
    }

    @Override
    public Object getGroup(int i) {
        return groupList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return childList.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        if (view == null)
            view = View.inflate(context, R.layout.item_group, null);
        TextView textView = view.findViewById(R.id.group_textview);
        ImageView imageView = view.findViewById(R.id.group_iv);
        textView.setText(groupList.get(i));
        if (b){
            imageView.setImageResource(R.drawable.back_blue);
        }else {
            imageView.setImageResource(R.drawable.arrow_back_white);
        }
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        if (view==null)
            view = View.inflate(context,R.layout.item_child,null);
        TextView item_child_tv = view.findViewById(R.id.item_child_tv);
        item_child_tv.setText(childList.get(i).get(i1));
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
