package top.yunp.drivingtest.reader;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

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
    private List<Question> questions = new ArrayList<>();
    private Context context;

    public QuestionsReader(Context context, int resid) throws SAXException {
        this.context = context;
        InputStream in = context.getResources().openRawResource(resid);
        parseInputStream(in);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public QuestionsReader(Context context, InputStream in) throws SAXException {
        this.context = context;
        parseInputStream(in);
    }

    private void parseInputStream(InputStream in) throws SAXException {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            try {
                JSONObject jsonObject = new JSONObject(sb.toString());
                JSONArray jsonQuestions = jsonObject.getJSONArray("questions");
                for (int i = 0; i < jsonQuestions.length(); i++) {
                    JSONObject jsonQuestion = jsonQuestions.getJSONObject(i);
                    questions.add(new Question(
                                    getContext(),
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

                new AlertDialog.Builder(getContext())
                        .setTitle("警告")
                        .setMessage("数据格式错误，请联系开发者")
                        .show();
            }
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
