package com.example.andriod.todolistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.UUID;

public class TodoActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_ID = "todo_id";

    public static Intent newIntent(Context packageContext, UUID todoId){
        Intent intent = new Intent(packageContext, TodoActivity.class);
        intent.putExtra(EXTRA_TODO_ID, todoId);
        return intent;
    }

    /* To decouple the fragment and make it reusable, the TodoFragment has a newInstance
    * method that receive a todoId and returns the fragment */

    protected Fragment createFragment(){
        UUID todoId = (UUID) getIntent().getSerializableExtra(EXTRA_TODO_ID);
        return TodoFragment.newInstance(todoId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            Fragment todoFragment = createFragment();

            fm.beginTransaction()
                    .add(R.id.fragment_container, todoFragment)
                    .commit();
        }
    }
}
