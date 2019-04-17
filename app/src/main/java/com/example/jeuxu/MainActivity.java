package com.example.jeuxu;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import static maes.tech.intentanim.CustomIntent.customType;

import com.example.jeuxu.Acceuil.Acceuil;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Acceuil_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Athletisme_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Basket_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.FanClub_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Football_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.HandBall_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Handisport_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Judo_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Tennis_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.Tennis_table_acc;
import com.example.jeuxu.Acceuil.Fragment_Acceuil.VolleyBall_acc;
import com.example.jeuxu.Actualites.Actualites;
import com.example.jeuxu.Classement.Classement;
import com.example.jeuxu.Decouvrir.Decouvrir_site;
import com.example.jeuxu.Decouvrir.Gallerie;
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
import com.example.jeuxu.Profile.Profil;
import com.example.jeuxu.Sante.liste_pharmatie;
import com.example.jeuxu.Securiter.Securite;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(R.string.actualite);

        setSupportActionBar(toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        setTitle(R.string.competition);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        adapter.addFragment(new Acceuil_acc(), getString(R.string.general));
        adapter.addFragment(new Football_acc(), getString(R.string.foot));
        adapter.addFragment(new Basket_acc(), getString(R.string.basket));
        adapter.addFragment(new HandBall_acc(), getString(R.string.hand));
        adapter.addFragment(new Judo_acc(), getString(R.string.judo));
        adapter.addFragment(new Tennis_acc(), getString(R.string.tennis));
        adapter.addFragment(new Tennis_table_acc(), getString(R.string.tennisTable));
        adapter.addFragment(new VolleyBall_acc(), getString(R.string.volley));
        adapter.addFragment(new Luttes(), getString(R.string.luttes));
        adapter.addFragment(new Handisport_acc(), getString(R.string.handisport));
        adapter.addFragment(new FanClub_acc(), getString(R.string.fan));
        adapter.addFragment(new Athletisme_acc(), getString(R.string.athletisme));


        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent i = new Intent(this, MainActivity.class);
            customType(MainActivity.this, "left-to-right");

            startActivity(i);

        } else if (id == R.id.nav_discover_site) {
            Intent i = new Intent(this, Decouvrir_site.class);
            startActivity(i);

        } else if (id == R.id.nav_heat) {
            Intent i = new Intent(this, liste_pharmatie.class);
            startActivity(i);

        } else if (id == R.id.nav_village_des_jeux) {
            Intent i = new Intent(this, Gallerie.class);
            startActivity(i);

        } else if (id == R.id.nav_logement) {
            Intent i = new Intent(this, Gallerie.class);
            startActivity(i);

        } else if (id == R.id.nav_moment) {
            Intent i = new Intent(this, Gallerie.class);
            startActivity(i);

        } else if (id == R.id.nav_security) {

            Intent i = new Intent(this, Securite.class);
            startActivity(i);
        } else if (id == R.id.nav_actualiter) {
            Intent i = new Intent(this, Actualites.class);
            startActivity(i);

        } else if (id == R.id.nav_cptlive) {
            Intent i = new Intent(this, Acceuil.class);
            String s = String.valueOf(getApplicationContext());
            Log.d("TABLE", s);
            startActivity(i);
            customType(MainActivity.this, "up-to-bottom");

        } else if (id == R.id.nav_profils) {
            Intent i = new Intent(this, Profil.class);
            String s = String.valueOf(getApplicationContext());
            Log.d("TABLE", s);
            startActivity(i);
            customType(MainActivity.this, "up-to-bottom");

        }
        else if (id == R.id.nav_classemt) {
            Intent i = new Intent(this, Classement.class);
            String s = String.valueOf(getApplicationContext());
            Log.d("TABLE", s);
            startActivity(i);
            customType(MainActivity.this, "up-to-bottom");

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
