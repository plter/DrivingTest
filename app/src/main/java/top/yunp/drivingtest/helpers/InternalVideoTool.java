package top.yunp.drivingtest.helpers;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by plter on 7/5/17.
 */

public class InternalVideoTool {

    public static void showAssetVideoTo(Context context, String assetPath, String tmpVideoFileName, VideoView videoView) {
        File videoFile = new File(context.getFilesDir(), tmpVideoFileName);
        if (!videoFile.exists()) {
            try {
                videoFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (videoFile.exists()) {
            try {

                InputStream inputStream = context.getAssets().open(assetPath);

                byte[] buf = new byte[2048];
                FileOutputStream fos = new FileOutputStream(videoFile);
                int count = -1;
                while ((count = inputStream.read(buf)) != -1) {
                    fos.write(buf, 0, count);
                }
                fos.close();
                inputStream.close();

                videoView.setVideoURI(Uri.fromFile(videoFile));
                videoView.start();
                videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            new AlertDialog.Builder(context)
                    .setTitle("警告")
                    .setMessage("关键位置不可写，无法播放视频")
                    .setPositiveButton("确定", null)
                    .show();
        }
    }
}
