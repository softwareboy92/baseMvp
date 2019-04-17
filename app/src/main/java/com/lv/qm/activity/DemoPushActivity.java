package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ExpandableListView;

import com.lv.qm.R;
import com.lv.qm.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;

public class DemoPushActivity extends AppCompatActivity {


    private ExpandableListView mExpandable_listview;
    private List<String> groupList;
    private List<List<String>> childList;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_push);
        mExpandable_listview = findViewById(R.id.expandable_listview);

        groupList = new ArrayList<>();
        childList = new ArrayList<>();

        groupList.add("第1名");
        List<String> childitem = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            childitem.add("fdjadjaklgjaljfldasj" + i);
        }
        childList.add(childitem);
        groupList.add("第2名");
        List<String> childitem2 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            childitem2.add("dfadfaslgsdlfa" + i);
        }
        childList.add(childitem2);
        groupList.add("第3名");
        List<String> childitem3 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            childitem3.add("fdasjgopweqr" + i);
        }
        childList.add(childitem3);
        groupList.add("第4名");
        List<String> childitem4 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            childitem4.add("qwp[evnz;" + i);
        }
        childList.add(childitem4);


        myAdapter = new MyAdapter(this, groupList, childList);
        mExpandable_listview.setAdapter(myAdapter);


    }
}
