package com.app.recycler.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.view.View;

import com.chad.library.adapter.base.animation.BaseAnimation;

//自定义RecyclerView的条目展示的动画效果
public class MyAnimation implements BaseAnimation {
    @Override
    public Animator[] getAnimators(View view) {
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "scaleY", 1, 3.0f, 1),
                ObjectAnimator.ofFloat(view, "scaleX", 1, 3.0f, 1)
        };
    }
}
