package top.yunp.drivingtest.reader;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by plter on 6/21/17.
 */

public class QuestionsReader {

    private Document document;
    private List<Question> questions = null;
    private Context context;

    public QuestionsReader(Context context, int resid, String baseDir) {
        this.context = context;
        InputStream in = context.getResources().openRawResource(resid);
        parseInputStream(in, baseDir);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QuestionsReader(Context context, InputStream in, String baseDir) {
        this.context = context;
        parseInputStream(in, baseDir);
    }

    /**
     * 将JSON字符串解析为问题列表
     *
     * @param context
     * @param jsonString
     * @param baseDir    问题所在的目录
     * @return
     */
    public static List<Question> parseJsonString(Context context, String jsonString, String baseDir) {
        List<Question> questions = null;

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            questions = new ArrayList<>();
            JSONArray jsonQuestions = jsonObject.getJSONArray("questions");
            for (int i = 0; i < jsonQuestions.length(); i++) {
                JSONObject jsonQuestion = jsonQuestions.getJSONObject(i);
                questions.add(new Question(
                                context,
                                baseDir,
                                jsonQuestion.optString("title", null),
                                jsonQuestion.optString("a", null),
                                jsonQuestion.optString("b", null),
                                jsonQuestion.optString("c", null),
                                jsonQuestion.optString("d", null),
                                jsonQuestion.optString("answer", null),
                                jsonQuestion.optString("type", null),
                                jsonQuestion.optString("description", null),
                                jsonQuestion.optString("image", null),
                                jsonQuestion.optString("video", null)
                        )
                );
            }
        } catch (JSONException e) {
            e.printStackTrace();

            new AlertDialog.Builder(context)
                    .setTitle("警告")
                    .setMessage("数据格式错误，请联系开发者")
                    .show();
        }

        return questions;
    }

    public static String encodeQuestionsToJsonObject(List<Question> questions) {
        String result = null;
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        try {
            obj.put("questions", arr);

            for (int i = 0; i < questions.size(); i++) {
                arr.put(questions.get(i).toJSONObject());
            }

            result = obj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<Question> cloneQuestions(List<Question> input) {
        List<Question> output = new ArrayList<>();
        output.addAll(input);
        return output;
    }

    private void parseInputStream(InputStream in, String baseDir) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            questions = parseJsonString(getContext(), sb.toString(), baseDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Context getContext() {
        return context;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
