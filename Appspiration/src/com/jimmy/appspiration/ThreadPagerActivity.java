package com.jimmy.appspiration;

import com.jimmy.view.SlidingTabLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
/**
 * 
 * Home Screen Activity
 */
public class ThreadPagerActivity extends FragmentActivity {
	
    static final int NUM_ITEMS = 4;
    MyAdapter mAdapter;
    ViewPager mPager;
    SlidingTabLayout tabs;
    CharSequence Titles[]={"Categories","Today", "Newest", "Weekly Top"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_pager);

        mAdapter = new MyAdapter(this, getSupportFragmentManager(), Titles);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);
        
        tabs = (SlidingTabLayout) findViewById(R.id.sliding_tab_layout);
        tabs.setDistributeEvenly(false);
        tabs.setViewPager(mPager);
        mPager.setCurrentItem(1);

    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_search:
            	//do something
                return true;
            case R.id.action_create:
            	//do something
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    
    public class MyAdapter extends FragmentPagerAdapter {
    	
    	CharSequence Titles[];
    	Context context = null;
    	
        public MyAdapter(Context context, FragmentManager fm, CharSequence mTitles[]) {
            super(fm);
            this.context=context;
            this.Titles = mTitles;
        }

        @Override
        public int getCount() {
            return ThreadPagerActivity.NUM_ITEMS;
        }

        @Override
        public Fragment getItem(int position) {
            return ArrayListFragment.newInstance(position);
        }
        
        public CharSequence getPageTitle(int position) {
        	return Titles[position];
        }
    }

    public static class ArrayListFragment extends ListFragment {
        int mNum;

        /**
         * Create a new instance of CountingFragment, providing "num"
         * as an argument.
         */
        static ArrayListFragment newInstance(int num) {
            ArrayListFragment f = new ArrayListFragment();

            // Supply num input as an argument.
            Bundle args = new Bundle();
            args.putInt("num", num);
            f.setArguments(args);

            return f;
        }

        /**
         * When creating, retrieve this instance's number from its arguments.
         */
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mNum = getArguments() != null ? getArguments().getInt("num") : 1;
        }
        
        static String getTitle(Context context, int position) {
            return(String.format(context.getString(R.string.hint), position + 1));
          }

        /**
         * The Fragment's UI is just a simple text view showing its
         * instance number.
         */
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.thread_list, container, false);
            return v;
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
          //  setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, Cheeses.sCheeseStrings));
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            Log.i("FragmentList", "Item clicked: " + id);
        }
    }
}