package com.example.delve;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity
{

    private static final String TAG = BaseActivity.class.getSimpleName();


    /**
     * Replaces a fragment on activity
     *
     * @param fragment       The fragment to show
     * @param containerId    Container ID in layout where fragment needs to be shown
     * @param enterAnim
     * @param exitAnim
     * @param popEnterAnim
     * @param popExitAnim
     * @param addToBackStack true if Back button should empty the fragment | false if otherwise
     */

    protected void replaceContentFragment(FragmentManager fragmentManager, Fragment fragment, boolean toAdd, int containerId, int enterAnim, int exitAnim, int popEnterAnim, int popExitAnim, boolean addToBackStack)
    {

        // Return if fragment to be added BaseUIActivity.javais null
        if (fragment == null)
        {
            throw new IllegalArgumentException("Null fragment passed in " + TAG + "#replaceContentFragment");
        }

        // getting the name of the fragment class
        String mFragmentName = ((Object) fragment).getClass().getSimpleName();


        // replace with new content
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Animation for above ICS
        fragmentTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        if (toAdd)
        {
            fragmentTransaction.add(containerId, fragment, mFragmentName);

        }
        else
        {
            fragmentTransaction.replace(containerId, fragment, mFragmentName);
        }

        if (addToBackStack)
        {
            fragmentTransaction.addToBackStack(mFragmentName);
        }

        fragmentTransaction.commit();
    }

    protected Fragment getFragmentInContainer(int containerId)
    {
        return getSupportFragmentManager().findFragmentById(containerId);
    }

}
