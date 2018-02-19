# CoreTabLayout
This library contains a customizable tab layout with dynamic item size.
![Optional Text](../master/ss.png)

**Features:**
* Fully customizable tab items visual.
* Dynamic item size ( 1 to ~ )

### I. How to add in the project?
I.A. Add next lines in your **projects build.gradle** file:
```
allprojects {
    repositories {
        maven { url "http://dl.bintray.com/psycoder0001/AndroidLibs" }
    }
}
```

I.B. Add next line in modules build.gradle file:
```
    implementation 'com.deepdroid.coretablayout:coretablayout:1.0.2'
```

### II. How to use?
II.A. Attach to your xml layout.
```xml
    <com.deepdroid.coretablayout.CoreTabLayout
        android:id="@+id/testCoreTabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:orientation="horizontal"/>
```

II.B. Set the items in your java codes.
```java
    CoreTabLayout testCoreTabLayout = (CoreTabLayout) findViewById(R.id.testCoreTabLayout);
    List<CoreTabItem> itemList = new ArrayList<>();
    itemList.add(new CoreTabItem("itemId0", "ItemLabel 0"));
    itemList.add(new CoreTabItem("itemId1", "ItemLabel 1"));
    itemList.add(new CoreTabItem("itemId2", "ItemLabel 2"));
    testCoreTabLayout.setItems(itemList, initialSelectedItemIndex);
    testCoreTabLayout.setItemSelectionListener(new CoreTabItemSelectionListener() {
        @Override
        public void onItemSelected(CoreTabItem tabItem) {
            // Do stuff
        }
    });
```

### III. How to customize?
III.A. Through the xml attributes
```xml
    <com.deepdroid.coretablayout.CoreTabLayout
        android:id="@+id/testCoreTabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="15dp"
        android:orientation="horizontal"
        app:passiveItemFilterColor="#555"
        app:passiveItemTextColor="#0af"
        app:passiveTextSize="13sp"
        app:selectedItemFilterColor="#AAA"
        app:selectedItemTextColor="#08d"
        app:selectedTextSize="14sp"/>
    <!--app:selectedSingleItemDrawable="@drawable/yourDrawable"-->
    <!--app:selectedStartItemDrawable="@drawable/yourDrawable"-->
    <!--app:selectedMiddleItemDrawable="@drawable/yourDrawable"-->
    <!--app:selectedEndItemDrawable="@drawable/yourDrawable"-->
```

III.B. Through the java codes
```java
    private void modifyTabLayoutConfigProgrammatically(CoreTabLayout coreTabLayout) {
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
        coreTabLayout.setTabConfig(customTabConfig, true);
    }
```

