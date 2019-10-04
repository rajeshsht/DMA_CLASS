package com.example.andriod.todofragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class TodoFragment extends Fragment {
    private Todo mTodo;
    private EditText mEditTextTitle;
    private Button mButtonDate;
    private CheckBox mCheckBoxIsComplete;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTodo = new Todo();
        //TODO: refactor
        mTodo.setTitle("Teat title");
        mTodo.setIsComplete(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        mEditTextTitle = (EditText) view.findViewById(R.id.todo_title);
        mEditTextTitle.setText(mTodo.getTitle());
        mEditTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //This line is intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                Toast.makeText(getActivity(), "text changed", Toast.LENGTH_SHORT).show();
                mTodo.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //This line is intentionally left blank
            }
        });

        mButtonDate = (Button) view.findViewById(R.id.todo_date);
        mButtonDate.setText(mTodo.getDate().toString());
        mButtonDate.setEnabled(false);

        mCheckBoxIsComplete = (CheckBox) view.findViewById(R.id.todo_complete);
        mCheckBoxIsComplete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Log.d("DEBUG **** TodoFragment", "called onCheckedChanged");
                Toast.makeText(getActivity(), "click checkbox"+isChecked, Toast.LENGTH_SHORT).show();
                Log.d("DEBUG **** TodoFragment", ""+mTodo.isIsComplete());
                mTodo.setIsComplete(isChecked);
                Log.d("DEBUG **** TodoFragment", ""+mTodo.isIsComplete());
            }
        });

        return view;
    }
}
