package com.example.gymclubapp.activity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.gymclubapp.R;
import com.example.gymclubapp.config.ServerConfig;
import com.example.gymclubapp.util.ToastUtil;

public class VideoDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_detail);
        setActivityToolbar(R.id.toolbarVideoDetails, true, false);
        // set video
        VideoView videoView = findViewById(R.id.videoDetail_videoView);
        videoView.setMediaController(new MediaController(this));
        videoView.setVideoURI(Uri.parse(ServerConfig.getAddress("/stream_video")));
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                ToastUtil.showToast(VideoDetailActivity.this, "教程结束啦！");
            }
        });
        videoView.start();
    }
}
