package com.deepdroid.coretablayouttestmodule;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

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

        setTestItems((CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith1Item), 1);
        setTestItems((CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith2Items), 2);
        setTestItems((CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith3Items), 3);
        setTestItems((CoreTabLayout) findViewById(R.id.testCoreTabLayoutWith4Items), 4);
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

        }
    };
}
