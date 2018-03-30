package com.app.standard.app;

//  Created by ruibing.han on 2018/3/29.

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.standard.util.ViewTool;

public class AppViewHolder {
    private final SparseArray views = new SparseArray();
    private View convertView;

    public AppViewHolder(View view) {
        this.convertView = view;
    }

    public View getConvertView() {
        return this.convertView;
    }

    public AppViewHolder setText(int viewId, CharSequence value) {
        TextView view = this.getView(viewId);
        view.setText(value);
        return this;
    }

    public AppViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = this.getView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public AppViewHolder setBackgroundColor(int viewId, int color) {
        View view = this.getView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public AppViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = this.getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public AppViewHolder setTextColor(int viewId, int textColor) {
        TextView view = this.getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public AppViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = this.getView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public AppViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = this.getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public AppViewHolder setAlpha(int viewId, float value) {
        AlphaAnimation alpha = new AlphaAnimation(value, value);
        alpha.setDuration(0L);
        alpha.setFillAfter(true);
        this.getView(viewId).startAnimation(alpha);
        return this;
    }

    public AppViewHolder setVisible(int viewId, boolean visible) {
        View view = this.getView(viewId);
        ViewTool.setVisible(view, visible);
        return this;
    }

    public AppViewHolder setTypeface(int viewId, Typeface typeface) {
        TextView view = this.getView(viewId);
        view.setTypeface(typeface);
        view.setPaintFlags(view.getPaintFlags() | 128);
        return this;
    }

    public AppViewHolder setTypeface(Typeface typeface, int... viewIds) {
        int[] var3 = viewIds;
        int var4 = viewIds.length;

        for (int var5 = 0; var5 < var4; ++var5) {
            int viewId = var3[var5];
            TextView view = this.getView(viewId);
            view.setTypeface(typeface);
            view.setPaintFlags(view.getPaintFlags() | 128);
        }

        return this;
    }

    //-------------------------------------------ProgressBar-------------------------------------------
    public AppViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = this.getView(viewId);
        view.setProgress(progress);
        return this;
    }

    public AppViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = this.getView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public AppViewHolder setMax(int viewId, int max) {
        ProgressBar view = this.getView(viewId);
        view.setMax(max);
        return this;
    }

    /*
     * 点击事件
     *
     * @param viewId
     * @param listener
     * @return
     */
    public AppViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = this.getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    /*
     * 长按
     *
     * @param viewId
     * @param listener
     * @return
     */
    public AppViewHolder setOnLongClickListener(int viewId, View.OnLongClickListener listener) {
        View view = this.getView(viewId);
        view.setOnLongClickListener(listener);
        return this;
    }


    /*
     * CheckBox的切换选中监听
     *
     * @param viewId
     * @param listener
     * @return
     */
    public AppViewHolder setOnCheckedChangeListener(int viewId, CompoundButton.OnCheckedChangeListener listener) {
        CompoundButton view = this.getView(viewId);
        view.setOnCheckedChangeListener(listener);
        return this;
    }

    public AppViewHolder setTag(int viewId, Object tag) {
        View view = this.getView(viewId);
        view.setTag(tag);
        return this;
    }

    public AppViewHolder setTag(int viewId, int key, Object tag) {
        View view = this.getView(viewId);
        view.setTag(key, tag);
        return this;
    }

    public AppViewHolder setChecked(int viewId, boolean checked) {
        View view = this.getView(viewId);
        if (view instanceof CompoundButton) {
            ((CompoundButton) view).setChecked(checked);
        } else if (view instanceof CheckedTextView) {
            ((CheckedTextView) view).setChecked(checked);
        }

        return this;
    }

    public <T extends View> T getView(int viewId) {
        View view = (View) this.views.get(viewId);
        if (view == null) {
            view = this.convertView.findViewById(viewId);
            this.views.put(viewId, view);
        }

        return (T) view;
    }
}
