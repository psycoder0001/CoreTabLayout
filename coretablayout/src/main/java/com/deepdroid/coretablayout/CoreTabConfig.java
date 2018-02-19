package com.deepdroid.coretablayout;

import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabConfig {
    public CoreTabConfigBackgroundDrawable singleItemBackgroundDrawable;
    public CoreTabConfigBackgroundDrawable startItemBackgroundDrawable;
    public CoreTabConfigBackgroundDrawable middleItemBackgroundDrawable;
    public CoreTabConfigBackgroundDrawable endItemBackgroundDrawable;

    public @ColorInt
    int passiveFilterColor;
    public @ColorInt
    int selectedFilterColor;
    public @ColorInt
    int passiveTextColor;
    public @ColorInt
    int selectedTextColor;

    public int passiveTextSize;
    public int selectedTextSize;

    public CoreTabConfig(Resources resources) {
        try {
            passiveFilterColor = resources.getColor(R.color.tabLayoutPassiveItemFilterColor);
        } catch (Resources.NotFoundException ignored) {
        }
        try {
            selectedFilterColor = resources.getColor(R.color.tabLayoutSelectedItemFilterColor);
        } catch (Resources.NotFoundException ignored) {
        }
        passiveTextColor = resources.getColor(R.color.tabLayoutPassiveItemTextColor);
        selectedTextColor = resources.getColor(R.color.tabLayoutSelectedItemTextColor);

        passiveTextSize = resources.getDimensionPixelSize(R.dimen.tabLayoutPassiveTextSize);
        selectedTextSize = resources.getDimensionPixelSize(R.dimen.tabLayoutSelectedTextSize);

        singleItemBackgroundDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_single_bg, R.drawable.core_tab_layout_single_fg);
        startItemBackgroundDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_start_bg, R.drawable.core_tab_layout_start_fg);
        middleItemBackgroundDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_middle_bg, R.drawable.core_tab_layout_middle_fg);
        endItemBackgroundDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_end_bg, R.drawable.core_tab_layout_end_fg);
    }

    public CoreTabConfig(CoreTabConfigBackgroundDrawable singleItemBackgroundDrawable
            , CoreTabConfigBackgroundDrawable startItemBackgroundDrawable
            , CoreTabConfigBackgroundDrawable middleItemBackgroundDrawable
            , CoreTabConfigBackgroundDrawable endItemBackgroundDrawable
            , int passiveFilterColor, int selectedFilterColor
            , int passiveTextColor, int selectedTextColor
            , int passiveTextSize, int selectedTextSize) {
        this.singleItemBackgroundDrawable = singleItemBackgroundDrawable;
        this.startItemBackgroundDrawable = startItemBackgroundDrawable;
        this.middleItemBackgroundDrawable = middleItemBackgroundDrawable;
        this.endItemBackgroundDrawable = endItemBackgroundDrawable;
        this.passiveFilterColor = passiveFilterColor;
        this.selectedFilterColor = selectedFilterColor;
        this.passiveTextColor = passiveTextColor;
        this.selectedTextColor = selectedTextColor;
        this.passiveTextSize = passiveTextSize;
        this.selectedTextSize = selectedTextSize;
    }

    public float getTextSize(boolean isPassive) {
        return isPassive ? passiveTextSize : selectedTextSize;
    }

    @ColorInt
    public int getTextColor(boolean isPassive) {
        return isPassive ? passiveTextColor : selectedTextColor;
    }

    @ColorInt
    public int getBackgroundFilterColor(boolean isPassive) {
        return isPassive ? passiveFilterColor : selectedFilterColor;
    }

    @DrawableRes
    public int getBackgroundDrawableResId(boolean isPassive, CoreTabItemType coreTabItemType) {
        switch (coreTabItemType) {
            default:
                return singleItemBackgroundDrawable.getBackgroundDrawableResId(isPassive);
            case START:
                return startItemBackgroundDrawable.getBackgroundDrawableResId(isPassive);
            case MIDDLE:
                return middleItemBackgroundDrawable.getBackgroundDrawableResId(isPassive);
            case END:
                return endItemBackgroundDrawable.getBackgroundDrawableResId(isPassive);
        }
    }
}