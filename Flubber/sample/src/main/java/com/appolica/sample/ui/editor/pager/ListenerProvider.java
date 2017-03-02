package com.appolica.sample.ui.editor.pager;

import com.appolica.sample.ui.editor.pager.animations.OnAnimationSelectedListener;
import com.appolica.sample.ui.editor.pager.interpolators.OnInterpolatorSelectedListener;
import com.appolica.sample.ui.editor.pager.settings.OnFieldChangedListener;

public interface ListenerProvider {
    OnAnimationSelectedListener getAnimationSelectedListener();
    OnInterpolatorSelectedListener getInterpolatorSelectedListener();
    OnFieldChangedListener getFieldChangedListener();
}
