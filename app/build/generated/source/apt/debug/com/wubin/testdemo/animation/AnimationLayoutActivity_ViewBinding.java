// Generated code from Butter Knife. Do not modify!
package com.wubin.testdemo.animation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ListView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wubin.testdemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnimationLayoutActivity_ViewBinding implements Unbinder {
  private AnimationLayoutActivity target;

  private View view2131230747;

  @UiThread
  public AnimationLayoutActivity_ViewBinding(AnimationLayoutActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnimationLayoutActivity_ViewBinding(final AnimationLayoutActivity target, View source) {
    this.target = target;

    View view;
    target.lv = Utils.findRequiredViewAsType(source, R.id.activity_animation_layout_list, "field 'lv'", ListView.class);
    view = Utils.findRequiredView(source, R.id.activity_animation_layout_btn, "method 'onClick'");
    view2131230747 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AnimationLayoutActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.lv = null;

    view2131230747.setOnClickListener(null);
    view2131230747 = null;
  }
}
