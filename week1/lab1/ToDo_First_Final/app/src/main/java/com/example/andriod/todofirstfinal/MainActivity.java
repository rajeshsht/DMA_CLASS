package com.example.andriod.todofirstfinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int IS_SUCCESS = 0;
    private String[] tasks;
    private int currentIndex = 0;

    public static final String TAG = "TodoActivity";

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
        // call the super class onCreate to complete the creation of Activity
        // like the view hierarchy
        super.onCreate(savedInstanceState);
        Log.d(TAG, "*** just to say the PC is onCreate!");

        // set the user interface layout for this Activity
        // the layout file is defined in the project res/layout/activity_todo.xml file
        setContentView(R.layout.activity_main);

        // check for saved state due to changes such as rotation or back button
        // and restore any saved state such as the todo index
        if (savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(TODO_KEY, 0);
        }

        // initialize member TextView so we can manipulate it later
        final TextView textViewTodo;
        textViewTodo = (TextView) findViewById(R.id.textViewTodo);

        setTextViewComplete("");


        /*
            TODO: Refactor to data layer
        */

        Resources res = getResources();
        tasks = res.getStringArray(R.array.tasks);
        // display the first task from mTodo array in the textViewTodo
        textViewTodo.setText(tasks[currentIndex]);


        Button buttonPrev = (Button) findViewById(R.id.buttonPrev);
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex > 0){
                    currentIndex = currentIndex % tasks.length;
                }else{
                    currentIndex = tasks.length;
                }

                currentIndex = (currentIndex - 1) % tasks.length;
                textViewTodo.setText(tasks[currentIndex]);
                setTextViewComplete("");
            }
        });

        Button buttonNext = (Button) findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentIndex = (currentIndex + 1) % tasks.length;
                textViewTodo.setText(tasks[currentIndex]);
                setTextViewComplete("");
            }
        });

        Button buttonTodoDetail = (Button) findViewById(R.id.buttonTodoDetail);
        buttonTodoDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Note, the child class being called has a static method determining the parameter
                // to be passed to it in the intent object
                Intent intent = Todo_DetailActivity.newIntent(MainActivity.this, currentIndex);
                // second param requestCode identifies the call as there could be many "intents"
                startActivityForResult(intent, IS_SUCCESS);
                // The result will come back through
                // onActivityResult(requestCode, resultCode, Intent) method
            }
        });
    }

    /*

       requestCode is the integer request code originally supplied to startActivityForResult
       resultCode is the integer result code returned by the child activity through its setResult()
       intent date attached with intent "extras"
   */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent intent) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IS_SUCCESS){
            if (intent != null){
                boolean isTodoComplete = intent.getBooleanExtra(IS_TODO_COMPLETE,false);
                updateTodoComplete(isTodoComplete);
            }else{
                Toast.makeText(this, "Back button pressed", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "requestCode in intent doesnot match the parent request...",
                    Toast.LENGTH_SHORT).show();
        }
    }

    private void updateTodoComplete(boolean is_todo_complete){
        final TextView textViewTodo;
        textViewTodo = (TextView) findViewById(R.id.textViewTodo);

        if(is_todo_complete){
            textViewTodo.setBackgroundColor(
                    ContextCompat.getColor(this, R.color.backgroundSuccess)
            );
            textViewTodo.setTextColor(
                    ContextCompat.getColor(this, R.color.colorSuccess)
            );
            setTextViewComplete("\u2713");
        }
    }

    private void setTextViewComplete(String message){
        final TextView textViewComplete;
        textViewComplete = (TextView) findViewById(R.id.textViewComplete);

        textViewComplete.setText(message);
    }

}
