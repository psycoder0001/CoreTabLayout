package com.deepdroid.coretablayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.PorterDuff;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KOZMOS on 2/18/2018.
 */

public class CoreTabLayout extends LinearLayout {

    private List<Animator> animatorList = new ArrayList<>();
    private List<CoreTabItem> itemList = new ArrayList<>();

    private CoreTabItemSelectionListener itemSelectionListener;
    private CoreTabConfig coreTabConfig;
    private CoreTabItem selectedItem = null;

    public CoreTabLayout(Context context) {
        super(context);
        init(context, null);
    }

    public CoreTabLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CoreTabLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        coreTabConfig = new CoreTabConfig(context.getResources());
        if (isInEditMode()) {
            List<CoreTabItem> dummyItemList = new ArrayList<>();
            dummyItemList.add(new CoreTabItem("", "ItemStart"));
            dummyItemList.add(new CoreTabItem("", "ItemMiddle"));
            dummyItemList.add(new CoreTabItem("", "ItemEnd"));
            setItems(dummyItemList, 0);
        }
    }

    // =============================================================================================
    // GET METHODS
    public List<CoreTabItem> getItemList() {
        return itemList;
    }

    public CoreTabItem getSelectedItem() {
        return selectedItem;
    }

    public int getSelectedItemIndex() {
        return selectedItem.itemIndex;
    }
    // GET METHODS
    // =============================================================================================

    // =============================================================================================
    // SET METHODS
    public void setTabConfig(CoreTabConfig tabConfig, boolean forceRedraw) {
        this.coreTabConfig = tabConfig;
        if (forceRedraw) {
            generateItems();
            setSelectedItem(selectedItem.itemIndex);
        }
    }

    public void setItems(List<CoreTabItem> itemList, int initialSelectionIndex) {
        if (itemList == null || itemList.isEmpty()) {
            return;
        }
        this.itemList = itemList;
        generateItems();
        setSelectedItem(initialSelectionIndex);
    }
    // SET METHODS
    // =============================================================================================

    // =============================================================================================
    // ITEM GENERATION
    private void generateItems() {
        for (CoreTabItem coreTabItem : itemList) {
            addView(generateItemWith(coreTabItem));
        }
    }

    private View generateItemWith(CoreTabItem coreTabItem) {
        // Set items essential properties
        coreTabItem.coreTabItemType = CoreTabItemType.SINGLE;
        if (itemList.size() > 1) {
            if (getChildCount() == 0) {
                coreTabItem.coreTabItemType = CoreTabItemType.START;
            } else if (getChildCount() == itemList.size() - 1) {
                coreTabItem.coreTabItemType = CoreTabItemType.END;
            } else {
                coreTabItem.coreTabItemType = CoreTabItemType.MIDDLE;
            }
        }
        coreTabItem.itemIndex = getChildCount();

        // Generate passive & selected items
        generateItemViewWith(coreTabItem, true);
        generateItemViewWith(coreTabItem, false);

        // Generate item parent
        RelativeLayout itemView = new RelativeLayout(getContext());
        itemView.setLayoutParams(new LayoutParams(0, LayoutParams.WRAP_CONTENT, 1));
        itemView.setClipChildren(false);
        itemView.setClipToPadding(false);
        itemView.addView(coreTabItem.passiveItemTv);
        itemView.addView(coreTabItem.selectedItemTv);
        return itemView;
    }

    private void generateItemViewWith(CoreTabItem coreTabItem, boolean isPassive) {
        TextView itemView = new TextView(getContext());
        itemView.setGravity(Gravity.CENTER);
        setItemSelectionListener(itemView, coreTabItem);
        itemView.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        coreTabItem.setItemView(itemView, isPassive);
        customizeItemView(itemView, coreTabItem, isPassive);
    }

    private void setItemSelectionListener(TextView itemView, final CoreTabItem coreTabItem) {
        itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemSelected(coreTabItem);
            }
        });
    }

    private void customizeItemView(TextView itemView, CoreTabItem coreTabItem, boolean isPassive) {
        itemView.setText(coreTabItem.itemText);
        itemView.setTextSize(TypedValue.COMPLEX_UNIT_PX, coreTabConfig.getTextSize(isPassive));
        itemView.setTextColor(coreTabConfig.getTextColor(isPassive));
        itemView.setBackgroundResource(coreTabConfig.getBackgroundDrawableResId(isPassive, coreTabItem.coreTabItemType));
        filterBackgroundColor(itemView, isPassive);
    }

    private void filterBackgroundColor(TextView itemView, boolean isPassive) {
        int filterColor = coreTabConfig.getBackgroundFilterColor(isPassive);
        if (filterColor == 0) {
            return;
        }
        itemView.getBackground().mutate().setColorFilter(filterColor, PorterDuff.Mode.SRC_IN);
    }
    // ITEM GENERATION
    // =============================================================================================

    // =============================================================================================
    // ITEM SELECTION
    public void setSelectedItem(int indexToBeSelected) {
        if (itemList == null || indexToBeSelected >= itemList.size() || indexToBeSelected < 0) {
            throw new ArrayIndexOutOfBoundsException("Invalid item index"
                    + " || itemList size: " + (itemList == null ? "null" : itemList.size())
                    + " || indexToBeSelected: " + indexToBeSelected
            );
        }
        onItemSelected(itemList.get(indexToBeSelected));
    }

    private void onItemSelected(CoreTabItem coreTabItem) {
        if (itemList.size() == 0) {
            return;
        }
        clearAnimations();
        selectedItem = null;
        if (itemList.size() == 1) {
            reverseSelection(itemList.get(0));
        } else {
            for (CoreTabItem tabItem : itemList) {
                if (tabItem.itemIndex == coreTabItem.itemIndex) {
                    selectItem(tabItem);
                } else {
                    deselectItem(tabItem);
                }
            }
        }
    }

    private void clearAnimations() {
        for (Animator animator : animatorList) {
            animator.cancel();
        }
        animatorList.clear();
    }

    private void reverseSelection(CoreTabItem tabItem) {
        if (tabItem.isSelected) {
            deselectItem(tabItem);
        } else {
            selectItem(tabItem);
        }
    }

    private void selectItem(CoreTabItem tabItem) {
        if (isInEditMode()) {
            tabItem.selectedItemTv.setVisibility(VISIBLE);
            return;
        }
        if (tabItem.selectedItemTv.getAlpha() == 1f && tabItem.selectedItemTv.getVisibility() == VISIBLE && tabItem.isSelected) {
            // Already selected.
            return;
        }
        tabItem.isSelected = true;
        tabItem.selectedItemTv.clearAnimation();
        tabItem.selectedItemTv.setAlpha(0f);
        tabItem.selectedItemTv.setVisibility(VISIBLE);
        ObjectAnimator.ofFloat(tabItem.selectedItemTv, ALPHA, 1f).start();
        selectedItem = tabItem;
        if (itemSelectionListener != null) {
            itemSelectionListener.onItemSelected(selectedItem);
        }
    }

    private void deselectItem(final CoreTabItem tabItem) {
        if (isInEditMode()) {
            tabItem.selectedItemTv.setVisibility(GONE);
            return;
        }
        if (tabItem.selectedItemTv.getVisibility() == INVISIBLE) {
            // Not selected.
            return;
        }
        tabItem.isSelected = false;
        tabItem.selectedItemTv.clearAnimation();
        tabItem.selectedItemTv.setAlpha(1f);
        tabItem.selectedItemTv.setVisibility(VISIBLE);
        final Animator alphaAnim = ObjectAnimator.ofFloat(tabItem.selectedItemTv, ALPHA, 0f);
        alphaAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                tabItem.selectedItemTv.setVisibility(INVISIBLE);
                tabItem.selectedItemTv.setAlpha(0f);
                animatorList.remove(alphaAnim);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
            }
        });
        alphaAnim.start();
        animatorList.add(alphaAnim);
    }

    public void setItemSelectionListener(CoreTabItemSelectionListener itemSelectionListener) {
        this.itemSelectionListener = itemSelectionListener;
    }

    // ITEM SELECTION
    // =============================================================================================
}