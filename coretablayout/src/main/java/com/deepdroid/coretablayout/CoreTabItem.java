package com.deepdroid.coretablayout;

import android.widget.TextView;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabItem {

    public String itemText;
    public String itemId;
    int itemIndex;
    CoreTabItemType coreTabItemType;
    TextView passiveItemTv;
    TextView selectedItemTv;

    public CoreTabItem(String itemId, String itemText) {
        this.itemId = itemId;
        this.itemText = itemText;
    }

    public void setItemView(TextView itemView, boolean isPassive) {
        if (isPassive) {
            passiveItemTv = itemView;
        } else {
            selectedItemTv = itemView;
        }
    }
}