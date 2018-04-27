package com.deepdroid.coretablayout;

import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabItem {

    String itemText;
    String itemId;
    int itemIndex;
    boolean isSelected;
    int imgRes;
    CoreTabItemType coreTabItemType;
    private TextView passiveItemTv;
    private TextView selectedItemTv;
    private ImageView passiveItemImg;
    private ImageView selectedItemImg;

    public CoreTabItem(String itemId, String itemText) {
        this.itemId = itemId;
        this.itemText = itemText;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemText() {
        return itemText;
    }

    public int getItemIndex() {
        return itemIndex;
    }

    public boolean isSelected() {
        return isSelected;
    }
    
    void setItemView(View itemView, boolean isPassive) {
        if (isPassive) {
            if (itemView instanceof TextView) {
                passiveItemTv = (TextView) itemView;
            } else {
                passiveItemImg = (ImageView) itemView;
            }
        } else {
            if (itemView instanceof TextView) {
                selectedItemTv = (TextView) itemView;
            } else {
                selectedItemImg = (ImageView) itemView;
            }
        }
    }

    public View getSelectedItemView() {
        return selectedItemTv != null ? selectedItemTv : selectedItemImg;
    }

    public View getPassiveItemView() {
        return passiveItemTv != null ? passiveItemTv : passiveItemImg;
    }

    public CoreTabItem setImage(@DrawableRes int imgRes) {
        this.imgRes = imgRes;
        return this;
    }
}