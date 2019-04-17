package com.lv.qm.adapter;

import com.lv.monkey.adapter.BaseAdapter;
import com.lv.monkey.adapter.BaseViewHolder;
import com.lv.qm.R;

import org.salient.artplayer.Comparator;
import org.salient.artplayer.MediaPlayerManager;
import org.salient.artplayer.VideoView;
import org.salient.artplayer.ui.ControlPanel;

/**
 * 作者：created by albert on 2018/9/21 17:46
 * 邮箱：lvzhongdi@icloud.com
 *
 * @param
 **/
public class MainAdapter extends BaseAdapter<String> {


    private String list;


    private Comparator mComparator = new Comparator() {
        @Override
        public boolean compare(VideoView videoView) {
            try {
                Object currentData = MediaPlayerManager.instance().getCurrentData();
                //By comparing the position on the list to distinguish whether the same video
                //根据列表位置识别是否同一个视频
                if (currentData != null && videoView != null) {
                    Object data = videoView.getData();
                    return data != null
                            && currentData.equals(list);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    };

    @Override
    protected int bindLayout(int viewType) {
        return R.layout.item_video;
    }

    @Override
    protected void bind(BaseViewHolder holder, String data) {
        list = data;
        VideoView mViewVideo = holder.itemView.findViewById(R.id.video_view);
        mViewVideo.setUp(data, VideoView.WindowType.LIST);
        mViewVideo.setControlPanel(new ControlPanel(mViewVideo.getContext()));
        mViewVideo.setComparator(mComparator);
    }
}
