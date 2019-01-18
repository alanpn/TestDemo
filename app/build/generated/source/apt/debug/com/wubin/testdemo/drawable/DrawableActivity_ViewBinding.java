// Generated code from Butter Knife. Do not modify!
package com.wubin.testdemo.drawable;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DrawableActivity_ViewBinding implements Unbinder {
  private DrawableActivity target;

  private View view2131230756;

  @UiThread
  public DrawableActivity_ViewBinding(DrawableActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DrawableActivity_ViewBinding(final DrawableActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, 2131230756, "field 'tv' and method 'onClick'");
    target.tv = Utils.castView(view, 2131230756, "field 'tv'", TextView.class);
    view2131230756 = view;
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
    DrawableActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tv = null;

    view2131230756.setOnClickListener(null);
    view2131230756 = null;
  }
}
