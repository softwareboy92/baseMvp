package com.lv.qm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lv.qm.R;
import com.lv.qm.adapter.MainAdapter;

import java.util.ArrayList;
import java.util.List;

public class RecycleActivity extends AppCompatActivity {


    private RecyclerView mRecyclerView;
    MainAdapter adapter;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        mRecyclerView = findViewById(R.id.recview);


        adapter = new MainAdapter();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);



        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");
        list.add("http://v9-dy-y.ixigua.com/efb92480ddbb4f622c339867e531e244/5bf945d3/video/m/220d9eb68da90f548a2bd7103d208cee1c611610128d00008acb94fdb052/?rc=MzVqa2Z0NmQ2aTMzZWkzM0ApQHRoaGR1KTo8Nzo8MzQzMzU2NDU0NDVvQGgzdSlAZjN1KXB6YnMxaDFwekApNTRkcnJicy4xYWFwXy0tNS0vc3MtbyNqdDppMUEtMS0vNi0uLTM2MS41LTojbyM6YS1xIzpgYmJeZl5fdGJiXmA1Ljo%3D");

        adapter.setData(list);
        adapter.notifyDataSetChanged();
    }
}
