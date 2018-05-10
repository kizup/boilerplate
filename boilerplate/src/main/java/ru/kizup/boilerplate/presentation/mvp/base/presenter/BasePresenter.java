package ru.kizup.boilerplate.presentation.mvp.base.presenter;

import android.support.annotation.Nullable;

import java.net.SocketTimeoutException;

import io.reactivex.SingleTransformer;
import io.reactivex.disposables.CompositeDisposable;
import ru.kizup.boilerplate.presentation.utils.LogUtil;
import ru.kizup.boilerplate.presentation.utils.RxSchedulersAbs;

/**
 * Created by: dpuzikov on 08.05.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class BasePresenter<View extends BaseView> {

    protected final RxSchedulersAbs mRxSchedulersAbs;
    protected CompositeDisposable mCompositeDisposable;

    private boolean isFirstAttach;
    private View mView;

    public BasePresenter(RxSchedulersAbs rxSchedulersAbs) {
        mRxSchedulersAbs = rxSchedulersAbs;
        isFirstAttach = true;
    }

    public void bindView(View view) {
        mView = view;
        mCompositeDisposable = new CompositeDisposable();

        if (isFirstAttach) {
            firstBindView(view);
            isFirstAttach = false;
        }
    }

    protected void firstBindView(View view) {
    }

    public void unbindView() {
        if (mCompositeDisposable != null) mCompositeDisposable.clear();
        mView = null;
    }

    public void destroyView() {
        isFirstAttach = true;
    }

    protected View getView() {
        return mView;
    }

    protected void hideKeyboard() {
        getView().hideKeyboard();
    }

    protected void onError(Throwable throwable) {
        LogUtil.exception(throwable);
    }

    private void showErrorAlert(String message) {
        getView().showErrorMessage(message);
    }

    protected  <T> SingleTransformer<T, T> loadingSingleCompose() {
        return upstream -> upstream.doOnSubscribe(d -> getView().showLoading())
                .doOnEvent((c, t) -> getView().hideLoading());
    }


}
