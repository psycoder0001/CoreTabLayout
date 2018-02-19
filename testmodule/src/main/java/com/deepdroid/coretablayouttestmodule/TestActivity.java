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

        // Find views
        CoreTabLayout tabLayoutWithSingleItem = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith1Item);
        CoreTabLayout tabLayoutWithTwoItems = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith2Items);
        CoreTabLayout tabLayoutWithThreeItems = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith3Items);
        CoreTabLayout tabLayoutWithFourItems = (CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith4Items);

        // Modify visual configurations ( You can modify background drawable, color filter for the background, text color & size for passive & active views. )
        modifyTabLayoutConfigProgrammatically(tabLayoutWithThreeItems);

        // Set new tab items
        setTestItems(tabLayoutWithSingleItem, 1);
        setTestItems(tabLayoutWithTwoItems, 2);
        setTestItems(tabLayoutWithThreeItems, 3);
        setTestItems(tabLayoutWithFourItems, 4);
    }

    private void modifyTabLayoutConfigProgrammatically(CoreTabLayout tabLayoutWithThreeItems) {
        // You can generate the config with the default set values & then change them one by one programmatically
        // CoreTabConfig customTabConfig = new CoreTabConfig(getResources());

        // You can generate the config with a new set of values.
        CoreTabConfig customTabConfig = new CoreTabConfig(
                new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_single_bg, R.drawable.core_tab_layout_single_fg)
                , new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_start_bg, R.drawable.core_tab_layout_start_fg)
                , new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_middle_bg, R.drawable.core_tab_layout_middle_fg)
                , new CoreTabConfigBackgroundDrawable(R.drawable.core_tab_layout_end_bg, R.drawable.core_tab_layout_end_fg)
                , getColorWithId(R.color.tabLayoutPassiveItemFilterColor), getColorWithId(R.color.tabLayoutSelectedItemFilterColor)
                , getColorWithId(R.color.tabLayoutPassiveItemTextColor), getColorWithId(R.color.tabLayoutSelectedItemTextColor)
                , getDimenWith(R.dimen.tabLayoutPassiveTextSize), getDimenWith(R.dimen.tabLayoutSelectedTextSize)
        );

        // Apply your new config values.
        tabLayoutWithThreeItems.setTabConfig(customTabConfig, true);
    }

    private void setTestItems(CoreTabLayout coreTabLayout, int itemCount) {
        List<CoreTabItem> itemList = new ArrayList<>();
        for (int x = 0; x < itemCount; x++) {
            itemList.add(new CoreTabItem(x + "", "Item\nNo:" + x));
        }
        coreTabLayout.setItems(itemList, itemCount - 1);
        coreTabLayout.setItemSelectionListener(itemSelectionListener);
    }

    private CoreTabItemSelectionListener itemSelectionListener = new CoreTabItemSelectionListener() {
        @Override
        public void onItemSelected(CoreTabItem tabItem) {
            // On tab item selected
        }
    };

    private @ColorInt
    int getColorWithId(@ColorRes int colorResId) {
        return getResources().getColor(colorResId);
    }

    private int getDimenWith(int dimenResId) {
        return getResources().getDimensionPixelSize(dimenResId);
    }
}
