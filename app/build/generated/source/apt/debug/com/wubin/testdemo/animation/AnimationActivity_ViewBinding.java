// Generated code from Butter Knife. Do not modify!
package com.wubin.testdemo.animation;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.wubin.testdemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AnimationActivity_ViewBinding implements Unbinder {
  private AnimationActivity target;

  private View view2131230754;

  private View view2131230745;

  private View view2131230749;

  private View view2131230750;

  private View view2131230753;

  private View view2131230751;

  private View view2131230752;

  private View view2131230746;

  @UiThread
  public AnimationActivity_ViewBinding(AnimationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AnimationActivity_ViewBinding(final AnimationActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.activity_animation_view, "field 'btn_view' and method 'viewClick'");
    target.btn_view = Utils.castView(view, R.id.activity_animation_view, "field 'btn_view'", Button.class);
    view2131230754 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.viewClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_alpha, "field 'btn_alpha' and method 'alphaClick'");
    target.btn_alpha = Utils.castView(view, R.id.activity_animation_alpha, "field 'btn_alpha'", Button.class);
    view2131230745 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.alphaClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_list, "field 'btn_list' and method 'listClick'");
    target.btn_list = Utils.castView(view, R.id.activity_animation_list, "field 'btn_list'", Button.class);
    view2131230749 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.listClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_object, "field 'btn_object' and method 'objectClick'");
    target.btn_object = Utils.castView(view, R.id.activity_animation_object, "field 'btn_object'", Button.class);
    view2131230750 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.objectClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_value, "field 'btn_value' and method 'valueClick'");
    target.btn_value = Utils.castView(view, R.id.activity_animation_value, "field 'btn_value'", Button.class);
    view2131230753 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.valueClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_set, "field 'btn_set' and method 'setClick'");
    target.btn_set = Utils.castView(view, R.id.activity_animation_set, "field 'btn_set'", Button.class);
    view2131230751 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setClick(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_set2, "field 'btn_set2' and method 'setClick2'");
    target.btn_set2 = Utils.castView(view, R.id.activity_animation_set2, "field 'btn_set2'", Button.class);
    view2131230752 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.setClick2(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.activity_animation_layout, "method 'layoutClick'");
    view2131230746 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.layoutClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AnimationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.btn_view = null;
    target.btn_alpha = null;
    target.btn_list = null;
    target.btn_object = null;
    target.btn_value = null;
    target.btn_set = null;
    target.btn_set2 = null;

    view2131230754.setOnClickListener(null);
    view2131230754 = null;
    view2131230745.setOnClickListener(null);
    view2131230745 = null;
    view2131230749.setOnClickListener(null);
    view2131230749 = null;
    view2131230750.setOnClickListener(null);
    view2131230750 = null;
    view2131230753.setOnClickListener(null);
    view2131230753 = null;
    view2131230751.setOnClickListener(null);
    view2131230751 = null;
    view2131230752.setOnClickListener(null);
    view2131230752 = null;
    view2131230746.setOnClickListener(null);
    view2131230746 = null;
  }
}
