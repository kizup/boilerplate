package ru.kizup.boilerplate.presentation.ui.base;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.kizup.boilerplate.presentation.mvp.base.presenter.BaseView;
import ru.kizup.boilerplate.presentation.utils.Utils;

/**
 * Created by: dpuzikov on 08.05.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class BaseSupportFragment extends Fragment
        implements BaseView {

    @LayoutRes
    public abstract int getLayoutId();

    public abstract void onPostViewCreated(@NonNull View view,
                                           @Nullable Bundle savedInstanceState);

    private View mRootView;
    private BaseCompatActivity mBaseActivity;
    private List<Pair<View, String>> mSharedViews;
    private Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mBaseActivity = (BaseCompatActivity) context;
        mSharedViews = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutId(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        onPostViewCreated(view, savedInstanceState);
    }

    protected void addSharedView(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mSharedViews.add(Pair.create(view, view.getTransitionName()));
        }
    }

    public boolean onBackPressed() {
        return true;
    }

    @Override
    public void hideKeyboard() {
        Utils.hideKeyboard(mRootView);
    }

    @Override
    public void showLoading() {}

    @Override
    public void showErrorMessage(String error) {
    }

    @Override
    public void showErrorMessage(int messageId) {
    }

    @Override
    public void hideLoading() {}

    protected BaseCompatActivity getBaseActivity() {
        return mBaseActivity;
    }

    public void showErrorLoading() {}

    protected <T extends View> T findViewById(@IdRes int id) {
        return mRootView.findViewById(id);
    }

    protected void setToolbar(Toolbar toolbar) {
        setToolbar(toolbar, true);
    }

    protected void setToolbar(Toolbar toolbar, boolean showBackArrow) {
        mToolbar = toolbar;

//        if (showBackArrow) {
//            mToolbar.setNavigationIcon(R.drawable.ic_arrow_back);
//        }

        mBaseActivity.setSupportActionBar(mToolbar);
    }

    protected void setTitle(CharSequence title) {
        if (mBaseActivity.getSupportActionBar() != null) {
            mBaseActivity.getSupportActionBar().setTitle(title);
        }
    }

    protected void showBackArrow() {
        if (mBaseActivity.getSupportActionBar() != null) {
            ActionBar actionBar = mBaseActivity.getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
//            if (mToolbar.getNavigationIcon() != null) {
//                mToolbar.getNavigationIcon().setColorFilter(ContextCompat.getColor(mBaseActivity, R.color.colorAccent),
//                        PorterDuff.Mode.SRC_ATOP);
//            }
        }
    }


    public List<Pair<View, String>> getSharedViews() {
        return mSharedViews;
    }

}
