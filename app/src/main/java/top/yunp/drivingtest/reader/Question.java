package top.yunp.drivingtest.reader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.appcompat.app.AlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by plter on 6/21/17.
 */

public class Question {

    private String baseDir;
    private String title, a, b, c, d, answer, type, description, image, video;
    private Bitmap imageBitmap = null;
    private Context context;

    /**
     * 构造一个问题
     *
     * @param context
     * @param baseDir     问题所在的根路径
     * @param title       问题标题
     * @param a           答案A选项
     * @param b           答案B选项
     * @param c           答案C选项
     * @param d           答案D选项
     * @param answer      正确答案
     * @param type        问题的类型，可能为single(单选),judge(判断),multi(多选)
     * @param description 问题的讲述
     * @param image       图片路径
     * @param video       视频路径
     */
    public Question(Context context, String baseDir, String title, String a, String b, String c, String d, String answer, String type, String description, String image, String video) {
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
        this.baseDir = baseDir;
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

    public String getBaseDir() {
        return baseDir;
    }

    public Bitmap getImageBitmap() {
        if (getImage() != null && imageBitmap == null) {
            try {
                InputStream in = getContext().getAssets().open(baseDir + getImage());
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

    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("title", title);
            obj.put("a", a);
            obj.put("b", b);
            obj.put("c", c);
            obj.put("d", d);
            obj.put("description", description);
            obj.put("answer", answer);
            obj.put("type", type);
            obj.put("image", image);
            obj.put("video", video);
        } catch (JSONException e) {
            e.printStackTrace();

            obj = null;
        }
        return obj;
    }
}
