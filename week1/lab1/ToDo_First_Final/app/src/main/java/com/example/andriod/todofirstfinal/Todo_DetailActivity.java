package com.example.andriod.todofirstfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class Todo_DetailActivity extends AppCompatActivity {

    private int currentIndex;

    // Any calling activity would call this static method and pass the necessary
    // key, value data pair in an intent object.
    public static Intent newIntent(Context packageContext, int todoIndex){
        Intent intent = new Intent (packageContext, Todo_DetailActivity.class);
        intent.putExtra(TODO_KEY, todoIndex);
        return intent;
    }

    // name, value pair to be returned in an intent
    private static final String IS_TODO_COMPLETE = "com.example.andriod.isTodoComplete";

    // In case of state change, due to rotating the phone
    // store the mTodoIndex to display the same mTodos element post state change
    // N.B. small amounts of data, typically IDs can be stored as key, value pairs in a Bundle
    // the alternative is to abstract view data to a ViewModel which can be in scope in all
    // Activity states and more suitable for larger amounts of data
    private static final String TODO_KEY = "com.example.andriod.todoKey";
    // override to write the value of mTodoIndex into the Bundle with TODO_INDEX as its key
    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt(TODO_KEY, currentIndex);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(TODO_KEY,0);
        }

        // initialize member TextView so we can manipulate it later
        TextView TodoDetailTextView;
        TodoDetailTextView = (TextView) findViewById(R.id.textViewTodoDetail);

        // get the intent extra int for the todos index
        int mTodoIndex = getIntent().getIntExtra(TODO_KEY, 0);
        updateTextViewTodoDetail(mTodoIndex);

        CheckBox checkboxIsComplete = (CheckBox) findViewById(R.id.checkBoxIsComplete);
        // Register the onClick listener with the generic implementation mTodoListener
        checkboxIsComplete.setOnClickListener(mTodoListener);
    }

    private void updateTextViewTodoDetail (int todoKey){

        final TextView textViewTodoDetail;
        textViewTodoDetail = (TextView) findViewById(R.id.textViewTodoDetail);

        /*
            TODO: refactor to a data layer
         */

        Resources res = getResources();
        String[] descriptions = res.getStringArray(R.array.description);
        // display the first task from mTodo array in the textViewTodoDetail
        textViewTodoDetail.setText(descriptions[todoKey]);
    }

    // Create an anonymous implementation of OnClickListener for all clickable view objects
    private View.OnClickListener mTodoListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // get the clicked object and do something
            switch (view.getId()/*to get clicked view object id**/){
                case R.id.checkBoxIsComplete:
                    CheckBox checkboxIsComplete = (CheckBox) findViewById(R.id.checkBoxIsComplete);
                    setIsComplete (checkboxIsComplete.isChecked());
                    finish();
                    break;
                default:
                    break;
            }
        }
    };

    private void setIsComplete (boolean isChecked){
//        celebrate with a static Toast!
        if (isChecked){
            Toast.makeText(Todo_DetailActivity.this,
                    "Hurray, it's done!", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(Todo_DetailActivity.this,
                    "There is always tomorrow!", Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent();
        intent.putExtra(IS_TODO_COMPLETE, isChecked);
        setResult(RESULT_OK, intent);
    }
}
