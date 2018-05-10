package ru.kizup.boilerplate.presentation.utils;

import io.reactivex.CompletableTransformer;
import io.reactivex.FlowableTransformer;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.SingleTransformer;

/**
 * Created by: dpuzikov on 08.05.18.
 * e-mail: kizup.diman@gmail.com
 * Skype: kizupx
 */

public abstract class RxSchedulersAbs {

    public abstract Scheduler io();
    public abstract Scheduler ui();

    public <T> SingleTransformer<T, T> getIoToUiSingleTransformer() {
        return upstream -> upstream.subscribeOn(io())
                .observeOn(ui());
    }

    public <T> ObservableTransformer<T, T> getIoToUiObservableTransformer() {
        return upstream -> upstream.subscribeOn(io())
                .observeOn(ui());
    }

    public CompletableTransformer getIoToUiCompletableTransformer() {
        return upstream -> upstream.subscribeOn(io())
                .observeOn(ui());
    }

    public <T> FlowableTransformer<T, T> getIoToUiFlowableTransformer() {
        return upstream -> upstream.subscribeOn(io())
                .observeOn(ui());
    }

}
