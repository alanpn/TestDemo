// Generated code from Butter Knife. Do not modify!
package com.wubin.testdemo.tabLayout;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.wubin.testdemo.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TabLayouActivity_ViewBinding implements Unbinder {
  private TabLayouActivity target;

  @UiThread
  public TabLayouActivity_ViewBinding(TabLayouActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TabLayouActivity_ViewBinding(TabLayouActivity target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.activity_tablayout_tab, "field 'tabLayout'", TabLayout.class);
    target.tabLayout1 = Utils.findRequiredViewAsType(source, R.id.activity_tablayout_tab1, "field 'tabLayout1'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.activity_tablayout_vp, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TabLayouActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tabLayout = null;
    target.tabLayout1 = null;
    target.viewPager = null;
  }
}
