package top.yunp.drivingtest.reader;

import android.content.Context;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;

/**
 * Created by plter on 6/22/17.
 */

public class QuestionsReadUtil {

    public static List<Question> read(Context context) throws SAXException {
        try {
            return new QuestionsReader(context.getAssets().open("questions.xml")).getQuestions();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
