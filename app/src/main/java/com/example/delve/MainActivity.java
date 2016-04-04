package com.example.delve;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.delve.ui.fragments.LandingFragment;

public class MainActivity extends BaseActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateTo(new LandingFragment(), true, false);
    }

    public void navigateTo(Fragment fragment, boolean toAdd, boolean addToBackStack)
    {

        replaceContentFragment(getSupportFragmentManager(), fragment, toAdd, (findViewById(R.id.containerMain)).getId(), 0, 0, 0, 0, addToBackStack);

    }
}
