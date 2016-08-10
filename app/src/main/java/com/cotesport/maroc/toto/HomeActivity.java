package com.cotesport.maroc.toto;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.cotesport.maroc.toto.Fragments.AutresFragment;
import com.cotesport.maroc.toto.Fragments.BasketballFragment;
import com.cotesport.maroc.toto.Fragments.ContactFragment;
import com.cotesport.maroc.toto.Fragments.FootballFragment;
import com.cotesport.maroc.toto.Fragments.ResultatFragment;
import com.cotesport.maroc.toto.Fragments.VoteFragment;
import com.cotesport.maroc.toto.Models.Football;
import com.cotesport.maroc.toto.Others.Global;
import com.cotesport.maroc.toto.Others.InternetCheking;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        FootballFragment.OnFragmentInteractionListener,
        BasketballFragment.OnFragmentInteractionListener,
        AutresFragment.OnFragmentInteractionListener,
        ResultatFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        VoteFragment.OnFragmentInteractionListener {

    Fragment fragment;
    Toolbar toolbar;
    FragmentTransaction transaction;
    FragmentManager manager;
    InternetCheking internetChecker = new InternetCheking(this);
    String lastDate = "";
    int i = 0;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        setTitle(this.getResources().getString(R.string.football));
        initDrawerNavigation();
        fragment = new FootballFragment();
        setFragmentManager(fragment);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    /****
     * Drawer navigation start
     ****/
    public void initDrawerNavigation() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_football);
        navigationView.setNavigationItemSelectedListener(this);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_football:
                setTitle(this.getResources().getString(R.string.football));
                setListFootball();
                //Fragment f = getSupportFragmentManager().findFragmentByTag("football_tag");
                break;

            case R.id.nav_basketball:
                setTitle(this.getResources().getString(R.string.basketball));
                fragment = new BasketballFragment();
                setFragmentManager(fragment);
                break;

            case R.id.nav_autres:
                setTitle(this.getResources().getString(R.string.autres));
                fragment = new AutresFragment();
                setFragmentManager(fragment);
                break;

            case R.id.nav_resultats:
                setTitle(this.getResources().getString(R.string.resultats));
                fragment = new ResultatFragment();
                setFragmentManager(fragment);
                break;

            case R.id.nav_contact:
                if (!(fragment instanceof ContactFragment)) {
                    setTitle(this.getResources().getString(R.string.contact));
                    fragment = new ContactFragment();
                    setFragmentManager(fragment);
                }
                break;

            case R.id.nav_vote:
                if (!(fragment instanceof VoteFragment)) {
                    setTitle(this.getResources().getString(R.string.vote));
                    fragment = new VoteFragment();
                    setFragmentManager(fragment);
                }
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**** Drawer navigation end ****/

    /****
     * Setting fragment manager
     ****/

    public void setFragmentManager(Fragment fragment) {

        if(!isFinishing()){
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            if ((fragment instanceof FootballFragment)) {
                transaction.replace(R.id.container, fragment, "football_tag");
            } else
                transaction.replace(R.id.container, fragment);
            transaction.commitAllowingStateLoss();
        }
    }

    public void refreshFragmentManager() {
        Fragment fragment = getSupportFragmentManager().findFragmentByTag("football_tag");
        manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();
        transaction.detach(fragment);
        transaction.attach(fragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
    }

    /****
     * Setting fragment manager end
     ****/


    public void setListFootball() {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!internetChecker.isInternetAvailable()) {
                    Toast.makeText(HomeActivity.this, "Aucune connexion internet.", Toast.LENGTH_LONG).show();
                    finish();
                }

                dialog = ProgressDialog.show(HomeActivity.this, "Chargement en cours...",
                        "Cette opération peut durer quelques secondes.", true);

                final WebView browser = (WebView) findViewById(R.id.webView);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.addJavascriptInterface(new MyJavaScriptFootballInterface(HomeActivity.this), "HTMLOUT");
                browser.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        browser.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");

                    }
                });
                browser.loadUrl("https://www.mdjsjeux.ma/cote-sport/play");
            }
        });


    }

    class MyJavaScriptFootballInterface {
        Context context;

        MyJavaScriptFootballInterface(Context context) {
            this.context = context;
        }

        @SuppressWarnings("unused")
        @JavascriptInterface
        public void processHTML(String html) {

            ((Global) getApplication()).getListeFootball().clear();
            Document doc = Jsoup.parse(html);
            Element response = doc.getElementById("catsport-1");
            Elements element3 = response.getElementsByTag("tr");

            for (Element e : element3) {

                Football bet = new Football();
                if (e.getElementsByAttributeValue("class", "date_games").size() > 0) {
                    lastDate = e.getElementsByAttributeValue("class", "date_games").get(0).text();
                }

                if (e.getElementsByTag("td").size() > 1) {
                    bet.setDateBet(lastDate);
                    i = 2;
                    Elements ssss3 = e.getElementsByTag("td");
                    for (Element e2 : ssss3) {
                        if (i == 2) {
                            bet.HeureDebut = e2.text();
                            i++;
                        } else if (i == 3) {
                            bet.NumeroBet = e2.text();
                            i++;
                        } else if (i == 4) {
                            bet.MinBets = e2.text();
                            i++;
                        } else if (i == 5) {
                            bet.HcpEq1 = e2.text();
                            i++;
                        } else if (i == 6) {
                            bet.Equipe1 = e2.text();
                            i++;
                        } else if (i == 7) {
                            bet.Equipe2 = e2.text();
                            i++;
                        } else if (i == 8) {
                            bet.HcpEq2 = e2.text();
                            i++;
                        } else if (i == 9) {
                            bet.Bet1 = e2.text();
                            i++;
                        } else if (i == 10) {
                            bet.Betx = e2.text();
                            i++;
                        } else if (i == 11) {
                            bet.Bet2 = e2.text();
                            i++;
                        } else if (i == 12) {
                            bet.DoubleChance1x = e2.text();
                            i++;
                        } else if (i == 13) {
                            bet.DoubleChance12 = e2.text();
                            i++;
                        } else if (i == 14) {
                            bet.DoubleChancex2 = e2.text();
                            i++;
                        } else if (i == 15) {
                            bet.Hcp1 = e2.text();
                            i++;
                        } else if (i == 16) {
                            bet.Hcpx = e2.text();
                            i++;
                        } else if (i == 17) {
                            bet.Hcp2 = e2.text();
                            i++;
                        } else if (i == 18) {
                            bet.Mitemps1 = e2.text();
                            i++;
                        } else if (i == 19) {
                            bet.Mitempsx = e2.text();
                            i++;
                        } else if (i == 20) {
                            bet.Mitemps2 = e2.text();
                            i++;
                        } else if (i == 21) {
                            bet.moins = e2.text();
                            i++;
                        } else if (i == 22) {
                            bet.plus = e2.text();
                            i++;
                        } else if (i == 23) {
                            bet.But01 = e2.text();
                            i++;
                        } else if (i == 24) {
                            bet.But23 = e2.text();
                            i++;
                        } else if (i == 25) {
                            bet.But4Plus = e2.text();
                            i++;
                        } else if (i == 26) {
                            bet.MitempsResultatFinal11 = e2.text();
                            i++;
                        } else if (i == 27) {
                            bet.MitempsResultatFinalx1 = e2.text();
                            i++;
                        } else if (i == 28) {
                            bet.MitempsResultatFinal21 = e2.text();
                            i++;
                        } else if (i == 29) {
                            bet.MitempsResultatFinal1x = e2.text();
                            i++;
                        } else if (i == 30) {
                            bet.MitempsResultatFinalxx = e2.text();
                            i++;
                        } else if (i == 31) {
                            bet.MitempsResultatFinal2x = e2.text();
                            i++;
                        } else if (i == 32) {
                            bet.MitempsResultatFinal12 = e2.text();
                            i++;
                        } else if (i == 33) {
                            bet.MitempsResultatFinalx2 = e2.text();
                            i++;
                        } else if (i == 34) {
                            bet.MitempsResultatFinal22 = e2.text();
                            i++;
                        } else if (i == 35) {
                            bet.ScoreExact = e2.text();
                            i++;
                        }
                    }
                    ((Global) getApplication()).getListeFootball().add(bet);
                }
            }

            if (dialog != null && dialog.isShowing()) {
                fragment = new FootballFragment();
                setFragmentManager(fragment);
                dialog.dismiss();
            }
        }
    }


}
