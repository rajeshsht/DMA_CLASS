package com.example.todo_first2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        textView.setText(""+counter);
    }

    public void toastClick(View view) {
        if (view.getId() == R.id.button)
            Toast.makeText(this, "This is ToastClick", Toast.LENGTH_LONG).show();
        else if (view.getId() == R.id.btn_clickme) {
            counter++;
            textView.setText("" + counter);
        }
        else if (view.getId() == R.id.btn_reset) {
            counter = 0;
            textView.setText("" + counter);
        }
    }

//    OR

//    public void toastClick(View view){
//        Toast.makeText(this, "This is start", Toast.LENGTH_LONG).show();
//    }
//
//    public void clickMe(View view){
//        counter++;
//        textView.setText(""+counter);
//    }
//
//    public void Reset(View view){
//        counter = 0;
//        textView.setText(""+counter);
//    }
}
