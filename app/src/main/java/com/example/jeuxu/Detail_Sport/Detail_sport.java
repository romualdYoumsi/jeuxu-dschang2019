package com.example.jeuxu.Detail_Sport;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Classe.Joueur;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.Detail_Sport.Fragment_Detail.Composition;
import com.example.jeuxu.Detail_Sport.Fragment_Detail.Statist;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Detail_sport extends AppCompatActivity {
    TextView txt_date_renc, txt_equipe_one, txt_coatch_one, txt_score_total,
            txt_equipe_two, txt_coatch_two, txt_stade_renc, txt_statu_renc;
    ImageView img_favoris, img_equipe_one, img_equipe_two, img_share;


    private SwipeRefreshLayout refresh;
    private ShareActionProvider shareActionProvider;


    TabLayout tablayout_detail;
    ViewPager viewpager_detail;
    ImageButton btn_share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sport);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        txt_date_renc = (TextView) findViewById(R.id.txt_date_renc);
        txt_equipe_one = (TextView) findViewById(R.id.txt_equipe_one);
        txt_coatch_one = (TextView) findViewById(R.id.txt_coatch_one);
        txt_equipe_two = (TextView) findViewById(R.id.txt_equipe_two);
        txt_stade_renc = (TextView) findViewById(R.id.txt_stade_renc);
        txt_score_total = (TextView) findViewById(R.id.txt_score_total);
        txt_coatch_two = (TextView) findViewById(R.id.txt_coatch_two);
        txt_coatch_one = (TextView) findViewById(R.id.txt_coatch_one);
        txt_statu_renc = (TextView) findViewById(R.id.txt_statu_renc);


        img_favoris = (ImageView) findViewById(R.id.img_favoris);
        img_equipe_one = (ImageView) findViewById(R.id.img_equipe_one);
        img_equipe_two = (ImageView) findViewById(R.id.img_equipe_two);
        btn_share = (ImageButton) findViewById(R.id.btn_share);


//        refresh = (SwipeRefreshLayout) findViewById(R.id.recharger);
//        refresh.setOnRefreshListener(this);


        tablayout_detail = (TabLayout) findViewById(R.id.tablayout_detail);
        viewpager_detail = (ViewPager) findViewById(R.id.viewpager_detail);
        setupViewPager(viewpager_detail);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        tablayout_detail.setupWithViewPager(viewpager_detail);
        display();
    }

    private void setupViewPager(ViewPager viewpager_detail) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Composition(), getString(R.string.composition));
        adapter.addFragment(new Statist(), getString(R.string.statistique));

        viewpager_detail.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_like:

                break;

            case R.id.action_share:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Download this App!. ");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void share(String nameApp, String imagePath, String message) {
        try {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("image/jpeg");
            List<ResolveInfo> resInfo = getPackageManager()
                    .queryIntentActivities(share, 0);
            if (!resInfo.isEmpty()) {
                for (ResolveInfo info : resInfo) {
                    Intent targetedShare = new Intent(
                            android.content.Intent.ACTION_SEND);
                    targetedShare.setType("image/jpeg"); // put here your mime
                    // type
                    if (info.activityInfo.packageName.toLowerCase().contains(
                            nameApp)
                            || info.activityInfo.name.toLowerCase().contains(
                            nameApp)) {
                        targetedShare.putExtra(Intent.EXTRA_SUBJECT,
                                "Sample Photo");
                        targetedShare.putExtra(Intent.EXTRA_TEXT, message);
                        targetedShare.putExtra(Intent.EXTRA_STREAM,
                                Uri.fromFile(new File(imagePath)));
                        targetedShare.setPackage(info.activityInfo.packageName);
                        targetedShareIntents.add(targetedShare);
                    }
                }
                Intent chooserIntent = Intent.createChooser(
                        targetedShareIntents.remove(0), "Select app to share");
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                        targetedShareIntents.toArray(new Parcelable[] {}));
                startActivity(chooserIntent);
            }
        } catch (Exception e) {
            Log.v("VM",
                    "Exception while sending image on" + nameApp + " "
                            + e.getMessage());
        }
    }
public void shareWhatsapp( View v){

    Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
    shareIntent.setType("text/html");
    shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, (String) v.getTag(R.string.app_name));
    shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, (String) v.getTag(R.mipmap.ic_launcher));
    //shareIntent.putExtra(Intent.EXTRA_STREAM, uri);

    PackageManager pm = v.getContext().getPackageManager();
    List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
    for (final ResolveInfo app : activityList) {
        if ((app.activityInfo.name).contains("com.whatsapp")) {
            final ActivityInfo activity = app.activityInfo;
            final ComponentName name = new ComponentName(
                    activity.applicationInfo.packageName, activity.name);
            shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            shareIntent.setComponent(name);
            v.getContext().startActivity(shareIntent);
            break;
        }
    }
}



    public void display() {
        if (Common.currentRencontre != null) {

            Picasso.with(this)
                    .load(Common.currentRencontre.getEquipesList().get(0).link)
                    .into(img_equipe_one);
            Picasso.with(this)
                    .load(Common.currentRencontre.getEquipesList().get(1).link)
                    .into(img_equipe_two);
            txt_equipe_one.setText(Common.currentRencontre.getEquipesList().get(0).nomEquipe);
            txt_equipe_two.setText(Common.currentRencontre.getEquipesList().get(1).nomEquipe);
            txt_stade_renc.setText(Common.currentRencontre.getLieu().getNom_lieu());
            txt_date_renc.setText(Common.currentRencontre.getDate());
            List<Joueur> joueur1 = Common.currentRencontre.getEquipesList().get(0).joueurList;
            List<Joueur> joueur2 = Common.currentRencontre.getEquipesList().get(1).joueurList;
            txt_coatch_one.setText(Common.currentRencontre.getEquipesList().get(0).getNomManager());
            txt_coatch_two.setText(Common.currentRencontre.getEquipesList().get(1).getNomManager());

            String score = getScroreJoueurs(joueur1) + " - " + getScroreJoueurs(joueur2);
            if (Common.currentRencontre.getCommencer() == 1) {
                txt_statu_renc.setText(getString(R.string.encour));
            } else if (Common.currentRencontre.getTerminer() == 1) {
                txt_statu_renc.setText(getString(R.string.termine));
            } else {
                txt_statu_renc.setText(getString(R.string.avenir));

            }
            txt_score_total.setText(score);

        }

    }

    private String getScroreJoueurs(List<Joueur> joueurs) {
        int a = 0;
        for (int i = 0; i < joueurs.size(); i++) {
            a += joueurs.get(i).nombre_buts.size();

        }
        return String.valueOf(a);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

//    @Override
//    public void onRefresh() {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                display();
//                refresh.setRefreshing(false);
//
//            }
//        }, 2000);
//    }

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
