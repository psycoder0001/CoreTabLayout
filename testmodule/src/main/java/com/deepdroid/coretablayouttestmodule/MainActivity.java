package com.deepdroid.coretablayouttestmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.deepdroid.coretablayout.CoreTabItem;
import com.deepdroid.coretablayout.CoreTabItemSelectionListener;
import com.deepdroid.coretablayout.CoreTabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String ITEM_ID_START = "i0";
    private static final String ITEM_ID_MIDDLE = "i1";
    private static final String ITEM_ID_END = "i2";

    private CoreTabLayout coreTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coreTabLayout = (CoreTabLayout) findViewById(R.id.mainCoreTabLayout);
        generateTestItems();
    }

    private void generateTestItems() {
        List<CoreTabItem> itemList = new ArrayList<>();
        itemList.add(new CoreTabItem(ITEM_ID_START, "Item\nStart"));
        itemList.add(new CoreTabItem(ITEM_ID_MIDDLE, "Item\nMiddle0"));
        itemList.add(new CoreTabItem(ITEM_ID_MIDDLE, "Item\nMiddle1"));
        itemList.add(new CoreTabItem(ITEM_ID_END, "Item\nEnd"));
        coreTabLayout.setItems(itemList, 0);
        coreTabLayout.setItemSelectionListener(itemSelectionListener);
    }

    private CoreTabItemSelectionListener itemSelectionListener = new CoreTabItemSelectionListener() {
        @Override
        public void onItemSelected(CoreTabItem tabItem) {

        }
    };
}
