package com.android.example.wordlistsql;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditorWordView;
    private WordListOpenHelper mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mEditorWordView = ((EditText) findViewById(R.id.search_word));
        mTextView = ((TextView) findViewById(R.id.search_result));
        mDB = new WordListOpenHelper(this);
    }

    public void showResult(View view)
    {
        String word = mEditorWordView.getText().toString();
        mTextView.setText("Result for " + word + ":\n\n");

        //search untuk database
        Cursor cursor = mDB.search(word);

        if (cursor != null & cursor.getCount() > 0) {
            cursor.moveToFirst();
            int index;
            String result;
            //Untuk Iterasi cursor
            do {
                index = cursor.getColumnIndex(WordListOpenHelper.KEY_WORD);
                result = cursor.getString(index);
                mTextView.append(result + "\n");
            } while (cursor.moveToNext()); //RETURN BENAR ATAU SALAH
            cursor.close();
        } else {
            mTextView.append(getString(R.string.no_result));
        }
    }
}
