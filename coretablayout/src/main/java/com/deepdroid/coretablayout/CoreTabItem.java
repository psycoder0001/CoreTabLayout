package com.deepdroid.coretablayout;

import android.widget.TextView;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabItem {

    String itemText;
    String itemId;
    int itemIndex;
    boolean isSelected;
    CoreTabItemType coreTabItemType;
    TextView passiveItemTv;
    TextView selectedItemTv;

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

    public TextView getPassiveItemTv() {
        return passiveItemTv;
    }

    public TextView getActiveItemTv() {
        return selectedItemTv;
    }

    void setItemView(TextView itemView, boolean isPassive) {
        if (isPassive) {
            passiveItemTv = itemView;
        } else {
            selectedItemTv = itemView;
        }
    }
}