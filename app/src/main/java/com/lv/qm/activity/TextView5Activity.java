package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.lv.qm.R;
import com.lv.qm.fragment.BottomDialogFragment;

public class TextView5Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view5);
        Button button = findViewById(R.id.view5_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomDialogFragment fragment = new BottomDialogFragment();
                fragment.show(getFragmentManager(), "tag01");
            }
        });
    }

}
