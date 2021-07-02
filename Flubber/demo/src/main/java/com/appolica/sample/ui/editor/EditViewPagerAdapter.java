package com.appolica.sample.ui.editor;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.appolica.sample.ui.editor.pager.AnimationBodyProvider;
import com.appolica.sample.ui.editor.pager.EditorFragmentType;
import com.appolica.sample.ui.editor.pager.ListenerProvider;
import com.appolica.sample.ui.editor.pager.animations.AnimationsFragment;
import com.appolica.sample.ui.editor.pager.interpolators.InterpolatorsFragment;
import com.appolica.sample.ui.editor.pager.settings.SettingsFragment;

import java.util.ArrayList;

public class EditViewPagerAdapter extends FragmentPagerAdapter {

    private final Context context;

    private ArrayList<String> registeredFragmentTags = new ArrayList<>();

    private ListenerProvider listenerProvider;
    private AnimationBodyProvider animationBodyProvider;

    public EditViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;

        for (int i = 0; i < getCount(); i++) {
            registeredFragmentTags.add(null);
        }

    }

    @Override
    public CharSequence getPageTitle(int position) {
        final EditorFragmentType fragmentType = EditorFragmentType.values()[position];

        return context.getString(fragmentType.getNameId());
    }

    @Override
    public Fragment getItem(int position) {
        final EditorFragmentType fragmentType = EditorFragmentType.values()[position];

        return fragmentType.getInstance();
    }

    @Override
    public int getCount() {
        return EditorFragmentType.values().length;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final Fragment item = (Fragment) super.instantiateItem(container, position);

        switch (EditorFragmentType.values()[position]) {
            case ANIMATIONS:
                ((AnimationsFragment) item).setSelectedListener(listenerProvider.getAnimationSelectedListener());
                break;
            case INTERPOLATORS:
                ((InterpolatorsFragment) item).setSelectedListener(listenerProvider.getInterpolatorSelectedListener());
                break;
            case SETTINGS:
                ((SettingsFragment) item).setFieldChangedListener(listenerProvider.getFieldChangedListener());
                break;
        }

        if (item instanceof AnimationBodyHolder) {
            ((AnimationBodyHolder) item).setAnimationBody(animationBodyProvider.getAnimationBody());
        }

        registeredFragmentTags.set(position, item.getTag());

        return item;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragmentTags.remove(position);
        super.destroyItem(container, position, object);
    }

    public void setListenerProvider(ListenerProvider listenerProvider) {
        this.listenerProvider = listenerProvider;
    }

    public void setAnimationBodyProvider(AnimationBodyProvider animationBodyProvider) {
        this.animationBodyProvider = animationBodyProvider;
    }

    public ArrayList<String> getRegisteredFragmentTags() {
        return registeredFragmentTags;
    }
}
