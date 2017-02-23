package com.appolica.sample.ui.editor.pager;

import com.appolica.sample.ui.editor.pager.animations.OnAnimationSelectedListener;
import com.appolica.sample.ui.editor.pager.interpolators.OnInterpolatorSelectedListener;

public interface ListenerProvider {
    OnAnimationSelectedListener getAnimationSelectedListener();
    OnInterpolatorSelectedListener getInterpolatorSelectedListener();
}
