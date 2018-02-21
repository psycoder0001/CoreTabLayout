package com.deepdroid.coretablayout;

import android.content.res.Resources;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabConfig {
    public boolean isAnimationEnabled = true;

    public CoreTabConfigBackgroundDrawable singleItemDrawable;
    public CoreTabConfigBackgroundDrawable startItemDrawable;
    public CoreTabConfigBackgroundDrawable middleItemDrawable;
    public CoreTabConfigBackgroundDrawable endItemDrawable;

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

        singleItemDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_single_item_drawable, R.drawable.core_tab_layout_selected_single_item_drawable);
        startItemDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_start_item_drawable, R.drawable.core_tab_layout_selected_start_item_drawable);
        middleItemDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_middle_item_drawable, R.drawable.core_tab_layout_selected_middle_item_drawable);
        endItemDrawable = new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_end_item_drawable, R.drawable.core_tab_layout_selected_end_item_drawable);
    }

    public CoreTabConfig(boolean isAnimationEnabled
            , CoreTabConfigBackgroundDrawable singleItemDrawable
            , CoreTabConfigBackgroundDrawable startItemDrawable
            , CoreTabConfigBackgroundDrawable middleItemDrawable
            , CoreTabConfigBackgroundDrawable endItemDrawable
            , @ColorInt int passiveFilterColor, @ColorInt int selectedFilterColor
            , @ColorInt int passiveTextColor, @ColorInt int selectedTextColor
            , int passiveTextSize, int selectedTextSize) {
        this.isAnimationEnabled = isAnimationEnabled;
        this.singleItemDrawable = singleItemDrawable;
        this.startItemDrawable = startItemDrawable;
        this.middleItemDrawable = middleItemDrawable;
        this.endItemDrawable = endItemDrawable;
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
                return singleItemDrawable.getBackgroundDrawableResId(isPassive);
            case START:
                return startItemDrawable.getBackgroundDrawableResId(isPassive);
            case MIDDLE:
                return middleItemDrawable.getBackgroundDrawableResId(isPassive);
            case END:
                return endItemDrawable.getBackgroundDrawableResId(isPassive);
        }
    }
}