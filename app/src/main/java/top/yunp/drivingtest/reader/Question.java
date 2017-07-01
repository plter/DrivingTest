package top.yunp.drivingtest.reader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by plter on 6/21/17.
 */

public class Question {

    private String title, a, b, c, d, answer, type, description, image, video;
    private Bitmap imageBitmap = null;
    private Context context;

    public Question(Context context, String title, String a, String b, String c, String d, String answer, String type, String description, String image, String video) {
        this.title = title;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.answer = answer;
        this.type = type;
        this.description = description;
        this.image = image;
        this.video = video;
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public String getAnswer() {
        return answer;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

    public Context getContext() {
        return context;
    }

    public Bitmap getImageBitmap() {
        if (getImage() != null && imageBitmap == null) {
            try {
                InputStream in = getContext().getAssets().open(QuestionsReadUtil.SUBJECT1_PATH + getImage());
                imageBitmap = BitmapFactory.decodeStream(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                new AlertDialog.Builder(getContext())
                        .setTitle("警告")
                        .setMessage("关键数据找不到，可能软件已经损坏，请尝试联系开发者修复该软件")
                        .setPositiveButton("好的", null)
                        .show();
            }
        }
        return imageBitmap;
    }

    public void recycleBitmap() {
        if (imageBitmap != null) {
            imageBitmap.recycle();
            imageBitmap = null;
        }
    }
}
