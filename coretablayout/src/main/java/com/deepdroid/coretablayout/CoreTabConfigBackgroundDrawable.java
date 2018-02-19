package com.deepdroid.coretablayout;

import android.support.annotation.DrawableRes;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabConfigBackgroundDrawable {
    public @DrawableRes
    int passiveDrawableResId;
    public @DrawableRes
    int selectedDrawableResId;

    public CoreTabConfigBackgroundDrawable(@DrawableRes int passiveDrawableResId, @DrawableRes int selectedDrawableResId) {
        this.passiveDrawableResId = passiveDrawableResId;
        this.selectedDrawableResId = selectedDrawableResId;
    }

    @DrawableRes
    public int getBackgroundDrawableResId(boolean isPassive) {
        return isPassive ? passiveDrawableResId : selectedDrawableResId;
    }
}
