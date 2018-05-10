package ru.kizup.boilerplate.presentation.mvp.base.presenter;

import android.support.annotation.StringRes;

/**
 * Created by: dpuzikov on 08.05.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public interface BaseView {

    void showLoading();

    void hideLoading();

    void showErrorMessage(String message);

    void showErrorMessage(@StringRes int messageId);

    void hideKeyboard();

}
