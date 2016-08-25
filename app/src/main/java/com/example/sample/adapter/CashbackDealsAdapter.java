package com.example.sample.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sample.data.PagePracelable;
import com.example.sample.fragment.PagerFragment;

import java.util.ArrayList;

/**
 * Created by manjula on 8/13/16.
 */
public class CashbackDealsAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 9;
    private String tabTitles[] = new String[] { "LinearLayout", "RelativeLayout", "TableLayout",  "FrameLayout", "GridLayout", "Tab6",  "Tab7", "Tab8", "Tab9" };
    private ArrayList<PagePracelable> listPageObj = new ArrayList<>();
    public CashbackDealsAdapter(FragmentManager fm) {
        super(fm);

        for (int i=0; i < tabTitles.length; i++) {
            PagePracelable page = new PagePracelable();
            page.setPage_id(i);
            page.setPage_name(tabTitles[i]);
            listPageObj.add(page);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return PagerFragment.newInstance(listPageObj.get(position));
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}
