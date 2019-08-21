package com.example.todo_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] tasks;
    //    ={"coffee", "lunch", "college", "listening music", "playing", "dinner", "sleep"};
    private TextView textView;
    private static final String TAG = "MainActivity";
    int currentIndex = 0;
    //    private static final String TODO_INDEX = "todoIndex";
    public static final String TODO_KEY = "com.example.todoIndex";

    private Button detailButton;

    @Override
    protected void onStart() {
        Log.d("MainActivity", "onStart()");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d("MainActivity", "onResume()");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.d("MainActivity", "onStop()");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d("MainActivity", "onDestory()");
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        Log.d("MainActivity", "onPause()");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle onSaveInstanceState) {
        super.onSaveInstanceState(onSaveInstanceState);
        onSaveInstanceState.putInt("TODO_INDEX", currentIndex);
        Log.d("MainActivity", "onSaveInstanceState");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        call the super class onCreate to complete the creation of Activity
//        like the view hierarchy

        super.onCreate(savedInstanceState);
        Log.d(TAG, "Just to say the PC is in onCreate!");

        //set the user interface layout for this Activity
//        the layout file is defined in the project res/layout/activity_,main.xml file
        setContentView(R.layout.activity_main);

//      check for saved state due to changes such as rotation or back button
//        and restore any saved state such as the todo index
        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("TODO_KEY");
        }

        // initialize member TextView so we can manipulate it later
        final TextView textView;
        textView = findViewById(R.id.textView);

        Resources res = getResources();
        tasks = res.getStringArray(R.array.tasks);
//        display the first task from tasks array in the textView
        textView.setText(tasks[currentIndex]);

//        setTextViewComplete("");


        Button buttonNext = (Button) findViewById(R.id.next_button);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % tasks.length;
                textView.setText(tasks[currentIndex]);
                Log.d(TAG, "Value of current index:" + currentIndex);
            }
        });

        Button buttonPrevious = (Button) findViewById(R.id.pre_button);
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentIndex > 0) {
                    currentIndex = currentIndex % tasks.length;
                } else {
                    currentIndex = tasks.length;
                }
                currentIndex = (currentIndex - 1) % tasks.length;
                textView.setText(tasks[currentIndex]);
                Log.d(TAG, "Value of current index:" + currentIndex);
            }
        });

        Button detailButton = (Button) findViewById(R.id.btn_detail);
        detailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Todo_DetailActivity.class);
                intent.putExtra(TODO_KEY, currentIndex);
                startActivity(intent);
            }
        });
    }
//    public void preButton(View view){
//        if(currentIndex > 0){
//            currentIndex = currentIndex % tasks.length;
//        } else{
//            currentIndex = tasks.length;
//        }
//        currentIndex = --currentIndex % tasks.length;
//        textView.setText(tasks[currentIndex]);
//        Log.d("MainActivity", "value of current index:" +currentIndex);
//    }
//
//    public void nextButton(View view){
//        currentIndex = ++currentIndex % tasks.length;
//        textView.setText(tasks[currentIndex]);
//        Log.d("MainActivity", "Value of current index:" +currentIndex);
//    }

}