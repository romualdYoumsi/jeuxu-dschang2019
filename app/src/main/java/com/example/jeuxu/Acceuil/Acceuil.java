package com.example.jeuxu.Acceuil;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.example.jeuxu.Fragment_Autres.Athletisme;
import com.example.jeuxu.Fragment_Autres.Basket;
import com.example.jeuxu.Fragment_Autres.FanClub;
import com.example.jeuxu.Fragment_Autres.Football;
import com.example.jeuxu.Fragment_Autres.HandBall;
import com.example.jeuxu.Fragment_Autres.Handisport;
import com.example.jeuxu.Fragment_Autres.Judo;
import com.example.jeuxu.Fragment_Autres.Luttes;
import com.example.jeuxu.Fragment_Autres.Tennis;
import com.example.jeuxu.Fragment_Autres.Tennis_Table;
import com.example.jeuxu.Fragment_Autres.VolleyBall;
import com.example.jeuxu.R;

import java.util.ArrayList;
import java.util.List;

public class Acceuil extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        setTitle("Actualiter");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

       // setSupportActionBar(toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        setTitle(R.string.competition);
    }
    private void setupViewPager(ViewPager viewPager) {
       ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new Football(), getString(R.string.foot));
        adapter.addFragment(new HandBall(), getString(R.string.hand));
        adapter.addFragment(new Basket(), getString(R.string.basket));
        adapter.addFragment(new VolleyBall(), getString(R.string.volley));
        adapter.addFragment(new Tennis(), getString(R.string.tennis));
        adapter.addFragment(new Tennis_Table(), getString(R.string.tennisTable));
        adapter.addFragment(new Luttes(), getString(R.string.luttes));
        adapter.addFragment(new Handisport(), getString(R.string.handisport));
        adapter.addFragment(new Athletisme(), getString(R.string.athletisme));
        adapter.addFragment(new Judo(), getString(R.string.judo));
        adapter.addFragment(new FanClub(), getString(R.string.fan));


        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
