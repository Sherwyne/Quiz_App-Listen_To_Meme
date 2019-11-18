package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myapplication.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ListenToMeme.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NUMBER + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionTable();
    }

    //FOR UPDATING DATABASE
    //NEED TO INCREMENT DB VERSION
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(DATABASE_VERSION > 1){
            db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
            onCreate(db);
        }
    }

    private void fillQuestionTable() {
        Question q1 = new Question("q1", "q1_a1", "q1_a2", "q1_a3", "q1_a4", "q1_a1");
        addQuestion(q1);
        Question q2 = new Question("q2", "q2_a1", "q2_a2", "q2_a3", "q2_a4", "q2_a1");
        addQuestion(q2);
        Question q3 = new Question("q3", "q3_a1", "q3_a2", "q3_a3", "q3_a4", "q3_a1");
        addQuestion(q3);
        Question q4 = new Question("q4", "q4_a1", "q4_a2", "q4_a3", "q4_a4", "q4_a1");
        addQuestion(q4);
        Question q5 = new Question("q5", "q5_a1", "q5_a2", "q5_a3", "q5_a4", "q5_a1");
        addQuestion(q5);
        Question q6 = new Question("q6", "q6_a1", "q6_a2", "q6_a3", "q6_a4", "q6_a1");
        addQuestion(q6);
        Question q7 = new Question("q7", "q7_a1", "q7_a2", "q7_a3", "q7_a4", "q7_a1");
        addQuestion(q7);
        Question q8 = new Question("q8", "q8_a1", "q8_a2", "q8_a3", "q8_a4", "q8_a1");
        addQuestion(q8);
        Question q9 = new Question("q9", "q9_a1", "q9_a2", "q9_a3", "q9_a4", "q9_a1");
        addQuestion(q9);
        Question q10 = new Question("q10", "q10_a1", "q10_a2", "q10_a3", "q10_a4", "q10_a1");
        addQuestion(q10);
    }

    private void addQuestion(Question question){
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getSound_question());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getImage_option1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getImage_option2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getImage_option3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getImage_option4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NUMBER, question.getAnswer());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions(){
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if(c.moveToFirst()){
            do{
                int question_index = c.getColumnIndex(QuestionsTable.COLUMN_QUESTION);
                int option1_index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION1);
                int option2_index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION2);
                int option3_index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION3);
                int option4_index = c.getColumnIndex(QuestionsTable.COLUMN_OPTION4);
                int answer_index = c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NUMBER);

                Question question = new Question();
                question.setSound_question(c.getString(question_index));
                question.setImage_option1(c.getString(option1_index));
                question.setImage_option2(c.getString(option2_index));
                question.setImage_option3(c.getString(option3_index));
                question.setImage_option4(c.getString(option4_index));
                question.setAnswer(c.getString(answer_index));
                questionList.add(question);

            } while(c.moveToNext());
        }
        c.close();
        return questionList;
    }


}
