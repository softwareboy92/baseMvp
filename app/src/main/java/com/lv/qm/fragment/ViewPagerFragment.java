package com.lv.qm.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lv.qm.R;

/**
 * 作者：created by albert on 2018/12/26 14:41
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class ViewPagerFragment extends Fragment {

    private int pageNo;

    public static ViewPagerFragment getInstanceFragment(int pageNo) {
        ViewPagerFragment fragment = new ViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("pageNo", pageNo);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            pageNo = bundle.getInt("pageNo");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_viewpager, container, false);
        TextView textView = view.findViewById(R.id.textview);
        if ((pageNo % 2) == 0) {
            textView.setBackgroundColor(Color.parseColor("#E28790"));
        }else {
            textView.setBackgroundColor(Color.parseColor("#D9B127"));
        }
        textView.setText("当前页码：" + pageNo);
        return view;
    }
}
