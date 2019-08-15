package com.example.todo_details;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] tasks;
    //    ={"coffee", "lunch", "college", "listening music", "playing", "dinner", "sleep"};
    private TextView textView;
    int currentIndex = 0;
    private static final String TODO_INDEX = "todoIndex";

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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt("TODO_INDEX");
        }
        Resources res = getResources();
        tasks = res.getStringArray(R.array.tasks);

        textView = findViewById(R.id.textView);
        textView.setText(tasks[currentIndex]);
    }



    public void preButton(View view){
        if(currentIndex > 0){
            currentIndex = currentIndex % tasks.length;
        } else{
            currentIndex = tasks.length-1;
        }
        currentIndex = --currentIndex % tasks.length;
        Log.d("MainActivity", "value of current index:" +currentIndex);
        textView.setText(tasks[currentIndex]);
    }

    public void nextButton(View view){
        currentIndex = ++currentIndex % tasks.length;
        Log.d("MainActivity", "Value of current index:" +currentIndex);
        textView.setText(tasks[currentIndex]);
    }
}
