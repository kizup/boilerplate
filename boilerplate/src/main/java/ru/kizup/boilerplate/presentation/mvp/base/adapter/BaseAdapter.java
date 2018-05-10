package ru.kizup.boilerplate.presentation.mvp.base.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.kizup.boilerplate.presentation.utils.OnItemClickListener;

/**
 * Created by: kizup on 08.05.18.
 */

public abstract class BaseAdapter<Item> extends RecyclerView.Adapter<BaseViewHolder> {

    @Nullable OnItemClickListener<Item> mOnItemClickListener;
    @NonNull Context mContext;

    private LayoutInflater mLayoutInflater;
    private List<Item> mItems;

    public BaseAdapter(@NonNull Context context,
                       @Nullable OnItemClickListener<Item> listener) {
        mContext = context;
        mOnItemClickListener = listener;
        mLayoutInflater = LayoutInflater.from(context);
        mItems = new ArrayList<>();
    }

    public void setItems(List<Item> items) {
        setItems(items, true);
    }

    public void setItems(List<Item> items, boolean notifyAdapter) {
        if (mItems == null) mItems = new ArrayList<>();
        mItems.clear();
        mItems.addAll(items);
        if (notifyAdapter) {
            notifyDataSetChanged();
        }
    }

    protected View inflate(@LayoutRes int layoutId, ViewGroup parent) {
        return mLayoutInflater.inflate(layoutId, parent, false);
    }

    public List<Item> getItems() {
        return mItems;
    }

    @Nullable
    protected Item getItem(int position) {
        return mItems == null ? null : mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems == null ? 0 : mItems.size();
    }
}
