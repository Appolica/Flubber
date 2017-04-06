package com.appolica.sample.ui.main;

import com.appolica.flubber.Flubber;
import com.appolica.sample.ui.animation.CustomAnimationBody;
import com.appolica.sample.utils.StringUtils;

public class AnimationsListItemModel {
    private CustomAnimationBody animationBody;
    private String animationName;
    private String interpolatorName;

    public AnimationsListItemModel() {
    }

    public AnimationsListItemModel(CustomAnimationBody animationBody) {
        this.animationBody = animationBody;

        final Flubber.AnimationProvider animation = animationBody.getAnimation();
        final Flubber.InterpolatorProvider interpolator = animationBody.getInterpolator();
        final String animationName = StringUtils.getNormalizedEnumName(((Flubber.AnimationPreset) animation));
        final String interpolatorName = StringUtils.getNormalizedEnumName(((Flubber.Curve) interpolator));

        setAnimationName(animationName);
        setInterpolatorName(interpolatorName);
    }

    public CustomAnimationBody getAnimationBody() {
        return animationBody;
    }

    public void setAnimationBody(CustomAnimationBody animationBody) {
        this.animationBody = animationBody;

        final Flubber.AnimationProvider animation = animationBody.getAnimation();
        final Flubber.InterpolatorProvider interpolator = animationBody.getInterpolator();
        final String animationName = StringUtils.getNormalizedEnumName(((Flubber.AnimationPreset) animation));
        final String interpolatorName = StringUtils.getNormalizedEnumName(((Flubber.Curve) interpolator));

        setAnimationName(animationName);
        setInterpolatorName(interpolatorName);
    }

    public String getAnimationName() {
        return animationName;
    }

    public void setAnimationName(String animationName) {
        this.animationName = animationName;
    }

    public String getInterpolatorName() {
        return interpolatorName;
    }

    public void setInterpolatorName(String interpolatorName) {
        this.interpolatorName = interpolatorName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AnimationsListItemModel) {
            final AnimationsListItemModel query = (AnimationsListItemModel) obj;
            return animationBody.equals(query.getAnimationBody())
                    && animationName.equals(query.getAnimationName())
                    && interpolatorName.equals(query.getInterpolatorName());
        }

        return super.equals(obj);
    }
}
