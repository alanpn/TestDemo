/*
 * Copyright 2014 Soichiro Kashima
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.wubin.observablescrollviewmodule;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * This example shows how to handle scroll events on both the parent Activity and Fragments.
 * (Handling FAB is not the main purpose)
 *
 * SlidingTabLayout and SlidingTabStrip are from google/iosched:
 * https://github.com/google/iosched
 */
public class ViewPagerTabScrollViewWithFabActivity extends ViewPagerTabScrollViewActivity {

    @Override
    protected NavigationAdapter newViewPagerAdapter() {
        return new NavigationAdapter(getSupportFragmentManager());
    }

    private static class NavigationAdapter extends ViewPagerTabScrollViewActivity.NavigationAdapter {
        public NavigationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        protected Fragment newFragment() {
            return new ViewPagerTabScrollViewWithFabFragment();
        }
    }
}
