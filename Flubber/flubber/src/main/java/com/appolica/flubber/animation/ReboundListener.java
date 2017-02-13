package com.appolica.flubber.animation;

import com.facebook.rebound.Spring;

interface ReboundListener {
    public void onSpringAtRest(Spring spring);

    public void onSpringActivate(Spring spring);

    public void onSpringEndStateChange(Spring spring);
}
