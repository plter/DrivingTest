package top.yunp.drivingtest.reader;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by plter on 6/22/17.
 */

public class QuestionsReadUtil {

    public static final String SUBJECT1_PATH = "subject1/";
    public static final String SUBJECT4_PATH = "subject4/";
    private static List<Question> subject1Questions = null;
    private static List<Question> subject4Questions = null;

    public static List<Question> readQuestions(Context context, String baseDir, String fileName) {
        List<Question> questions = null;
        try {
            InputStream in = context.getAssets().open(baseDir + fileName);
            questions = new QuestionsReader(context, in, baseDir).getQuestions();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();

            new AlertDialog.Builder(context)
                    .setTitle("警告")
                    .setMessage("关键数据找不到，该程序无法运行，请联系开发者")
                    .setPositiveButton("好的", null)
                    .show();
        }
        return questions;
    }


    /**
     * 如果已经读到了数据，该函数将会缓存该数据，再次执行该函数时将直接从缓存中读取
     *
     * @param context
     * @return
     * @throws SAXException
     */
    public static List<Question> readSubject1(Context context) {
        if (subject1Questions == null) {
            subject1Questions = readQuestions(context, SUBJECT1_PATH, "questions.json");
        }
        return subject1Questions;
    }

    public static List<Question> readSubject4(Context context) {
        if (subject4Questions == null) {
            subject4Questions = readQuestions(context, SUBJECT4_PATH, "questions.json");
        }
        return subject4Questions;
    }
}
