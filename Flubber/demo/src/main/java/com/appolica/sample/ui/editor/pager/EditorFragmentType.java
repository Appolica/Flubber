package com.appolica.sample.ui.editor.pager;

import android.support.v4.app.Fragment;

import com.appolica.sample.R;
import com.appolica.sample.ui.editor.pager.animations.AnimationsFragment;
import com.appolica.sample.ui.editor.pager.interpolators.InterpolatorsFragment;
import com.appolica.sample.ui.editor.pager.settings.SettingsFragment;

public enum EditorFragmentType {
    ANIMATIONS(AnimationsFragment.class, AnimationsFragment.TAG, R.string.tab_animation),
    INTERPOLATORS(InterpolatorsFragment.class, InterpolatorsFragment.TAG, R.string.tab_curve),
    SETTINGS(SettingsFragment.class, SettingsFragment.TAG, R.string.tab_settings);

    private Class<? extends Fragment> fragmentClass;
    private String tag;

    private int nameId;

    EditorFragmentType(Class<? extends Fragment> fragmentClass, String tag, int nameId) {
        this.fragmentClass = fragmentClass;
        this.tag = tag;
        this.nameId = nameId;
    }

    public Fragment getInstance() {
        try {
            return fragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    public String getTag() {
        return tag;
    }

    public int getNameId() {
        return nameId;
    }

}
