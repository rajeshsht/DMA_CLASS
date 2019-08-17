package com.example.todo_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

public class Todo_DetailActivity extends AppCompatActivity {

    //    int currentIndex =0;
    private TextView textView;
    private String[] description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

//        Intent intent = getIntent();

        Resources res = getResources();
        description = res.getStringArray(R.array.description);

        int currentIndex = getIntent().getIntExtra(MainActivity.TODO_INDEX, 0);


        textView = findViewById(R.id.textView2);
//        textView.setText(""+currentIndex);
        textView.setText(description[currentIndex]);
    }
}
