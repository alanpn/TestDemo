// Generated code from Butter Knife. Do not modify!
package com.example.wubin.kotlinModule.view;

import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public final class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  private View view2131230745;

  private View view2131230746;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(final MainActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, 2131230745, "method 'onClick'");
    view2131230745 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
    view = Utils.findRequiredView(source, 2131230746, "method 'onClick'");
    view2131230746 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClick(p0);
      }
    });
  }

  @Override
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view2131230745.setOnClickListener(null);
    view2131230745 = null;
    view2131230746.setOnClickListener(null);
    view2131230746 = null;
  }
}
