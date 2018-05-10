package ru.kizup.boilerplate.presentation.ui.base;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by: dpuzikov on 08.05.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class BaseCompatActivity extends AppCompatActivity {

    protected BaseSupportFragment showFragment(BaseSupportFragment fragment, @IdRes int containerId) {
        return showFragment(fragment, containerId, false);
    }

    protected BaseSupportFragment showFragment(BaseSupportFragment fragment, @IdRes int containerId, boolean useTransition) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(useTransition ? FragmentTransaction.TRANSIT_FRAGMENT_OPEN : FragmentTransaction.TRANSIT_NONE)
                .replace(containerId, fragment)
                .commit();
        return fragment;
    }

    protected BaseSupportFragment showFragmentWithBackStack(BaseSupportFragment fragment, @IdRes int containerId) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();

        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.addToBackStack(null);
        transaction.replace(containerId, fragment);
        transaction.commit();
        return fragment;
    }

}
