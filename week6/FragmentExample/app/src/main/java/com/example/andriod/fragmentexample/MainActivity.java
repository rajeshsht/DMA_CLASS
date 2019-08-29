package com.example.andriod.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private boolean toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        BlankFragment frag = new BlankFragment();
//
//        fm.beginTransaction()
//                .add(R.id.fragment_container, frag)
//                .commit();

        showFragment(BlankFragment2.class);
    }

    private void showFragment(Class fragmentClass) {
        Fragment frag = null;
        try{
            frag = (Fragment) fragmentClass.newInstance();

        }catch (IllegalAccessException e){
            e.printStackTrace();
        }catch (InstantiationException e){
            e.printStackTrace();
        }

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, frag).commit();
    }

    public void changeFragment(View view) {
        if(toggle)
            showFragment(BlankFragment.class);
            else{
                showFragment(BlankFragment2.class);

            }
        toggle = !toggle;
    }
}
