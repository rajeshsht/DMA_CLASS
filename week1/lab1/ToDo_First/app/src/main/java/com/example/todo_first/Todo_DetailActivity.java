package com.example.todo_first;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Todo_DetailActivity extends AppCompatActivity {

    //    int currentIndex =0;
    private TextView textView;
    private String[] description;
    public static final String IS_TODO_COMPLETE = "com.example.is_todo_complete";
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

        CheckBox checkBoxIsComplete = (CheckBox) findViewById(R.id.checkBox);
        checkBoxIsComplete.setOnClickListener(mTodoListener);
    }

    private View.OnClickListener mTodoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.checkBox:
                    CheckBox checkBoxIsComplete = (CheckBox) findViewById(R.id.checkBox);
                    setIsComplete(checkBoxIsComplete.isChecked());
                    finish();
                    break;
                default:
                    break;
            }
        }
    };


    public void setIsComplete(boolean isChecked) {
       if(isChecked){
           Toast.makeText(Todo_DetailActivity.this, "Hurray, it's done!", Toast.LENGTH_LONG).show();
       }else {
           Toast.makeText(Todo_DetailActivity.this, "There is always tomorrow!", Toast.LENGTH_LONG).show();
       }
        Intent intent = new Intent();
        intent.putExtra(IS_TODO_COMPLETE, isChecked);
        setResult(RESULT_OK,intent);
    }

}
