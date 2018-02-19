package com.deepdroid.coretablayouttestmodule;

import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v7.app.AppCompatActivity;

import com.deepdroid.coretablayout.CoreTabConfig;
import com.deepdroid.coretablayout.CoreTabConfigBackgroundDrawable;
import com.deepdroid.coretablayout.CoreTabItem;
import com.deepdroid.coretablayout.CoreTabItemSelectionListener;
import com.deepdroid.coretablayout.CoreTabLayout;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // # ESSENTIAL : Find views.
        CoreTabLayout tabLayoutWithSingleItem = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith1Item);
        CoreTabLayout tabLayoutWithTwoItems = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith2Items);
        CoreTabLayout tabLayoutWithThreeItems = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith3Items);
        CoreTabLayout tabLayoutWithFourItems = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith4Items);

        // # ESSENTIAL : Set new tab items.
        tabLayoutWithSingleItem.setItems(getTestItems(1), 0);
        tabLayoutWithTwoItems.setItems(getTestItems(2), 1);
        tabLayoutWithThreeItems.setItems(getTestItems(3), 2);
        tabLayoutWithFourItems.setItems(getTestItems(4), 3);

        // # OPTIONAL - EXAMPLE CODES : Set item selection listener.
        tabLayoutWithSingleItem.setItemSelectionListener(itemSelectionListener);

        // # OPTIONAL - EXAMPLE CODES : Modify visual configurations
        // ( You can modify background drawable, color filter for the background, text color & size for passive & active views. )
        modifyTabLayoutConfigProgrammatically(tabLayoutWithThreeItems);
    }

    // # OPTIONAL - EXAMPLE CODES : Generate dummy data.
    private List<CoreTabItem> getTestItems(int itemCount) {
        List<CoreTabItem> itemList = new ArrayList<>();
        for (int x = 0; x < itemCount; x++) {
            itemList.add(new CoreTabItem(x + "", "Item\nNo:" + x));
        }
        return itemList;
    }

    // # OPTIONAL - EXAMPLE CODES : Customize tab layout visual configuration.
    private void modifyTabLayoutConfigProgrammatically(CoreTabLayout tabLayoutWithThreeItems) {
        // You can use default constructor & then change the config values one by one.
        // CoreTabConfig customTabConfig = new CoreTabConfig(getResources());

        // Or you can generate the config with a new set of values.
        CoreTabConfig customTabConfig = new CoreTabConfig(
                new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_single_item_drawable, R.drawable.core_tab_layout_selected_single_item_drawable)
                , new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_start_item_drawable, R.drawable.core_tab_layout_selected_start_item_drawable)
                , new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_middle_item_drawable, R.drawable.core_tab_layout_selected_middle_item_drawable)
                , new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_passive_end_item_drawable, R.drawable.core_tab_layout_selected_end_item_drawable)
                , getColorWithId(R.color.customPassiveItemFilterColor), getColorWithId(R.color.customSelectedItemFilterColor)
                , getColorWithId(R.color.customPassiveItemTextColor), getColorWithId(R.color.customSelectedItemTextColor)
                , getDimenWith(R.dimen.customPassiveTextSize), getDimenWith(R.dimen.tabLayoutSelectedTextSize)
        );

        // Apply your new config values.
        tabLayoutWithThreeItems.setTabConfig(customTabConfig, true);
    }

    // # OPTIONAL - EXAMPLE CODES : Item selection listener
    private CoreTabItemSelectionListener itemSelectionListener = new CoreTabItemSelectionListener() {
        @Override
        public void onItemSelected(CoreTabItem tabItem) {
            // On tab item selected
        }
    };

    // # OPTIONAL - EXAMPLE CODES
    private @ColorInt
    int getColorWithId(@ColorRes int colorResId) {
        try {
            return getResources().getColor(colorResId);
        } catch (Exception ignored) {
            return 0;
        }
    }

    // # OPTIONAL - EXAMPLE CODES
    private int getDimenWith(int dimenResId) {
        return getResources().getDimensionPixelSize(dimenResId);
    }
}
