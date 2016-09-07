package com.cotesport.maroc.programme;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cotesport.maroc.programme.Fragments.AutresFragment;
import com.cotesport.maroc.programme.Fragments.BasketballFragment;
import com.cotesport.maroc.programme.Fragments.ContactFragment;
import com.cotesport.maroc.programme.Fragments.FootballFragment;
import com.cotesport.maroc.programme.Fragments.ResultatFragment;
import com.cotesport.maroc.programme.Fragments.VoteFragment;
import com.cotesport.maroc.programme.Models.Football;
import com.cotesport.maroc.programme.Others.Global;
import com.cotesport.maroc.programme.Others.InternetCheking;
import com.cotesport.maroc.programme.Tabs.TabFragment1;
import com.cotesport.maroc.programme.Tabs.TabFragment2;
import com.cotesport.maroc.programme.Tabs.TabFragment3;
import com.cotesport.maroc.programme.Tabs.TabFragment4;
import com.cotesport.maroc.programme.Tabs.TabFragment5;
import com.cotesport.maroc.programme.Tabs.TabFragment6;
import com.cotesport.maroc.programme.Tabs.TabFragment8;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
        implements FootballFragment.OnFragmentInteractionListener,
        BasketballFragment.OnFragmentInteractionListener,
        AutresFragment.OnFragmentInteractionListener,
        ResultatFragment.OnFragmentInteractionListener,
        ContactFragment.OnFragmentInteractionListener,
        VoteFragment.OnFragmentInteractionListener {

    TabsPagerAdapter tabsPagerAdapter;
    ViewPager viewPager;
    Fragment fragment;
    Toolbar toolbar;
    FragmentTransaction transaction;
    FragmentManager manager;
    InternetCheking internetChecker = new InternetCheking(this);
    String lastDate = "";
    int i = 0;
    ProgressDialog dialog;
    Spinner spinner;
    //class members
    String businessType[] = {"Automobile", "Food", "Computers", "Education",
            "Personal", "Travel"};
    ArrayAdapter<String> adapterBusinessType;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        viewPager = (ViewPager) findViewById(R.id.pager);
        tabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(tabsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        //setCurrentButtonStyle(view, R.id.btn_resultat_final);
                        break;
                    case 1:
                        //setCurrentButtonStyle(view, R.id.btn_double_chance);
                        break;
                    case 2:
                        //setCurrentButtonStyle(view, R.id.btn_hdc);
                        break;
                    case 3:
                        //setCurrentButtonStyle(view, R.id.btn_mi_temps);
                        break;
                    case 4:
                        //setCurrentButtonStyle(view, R.id.btn_moins_plus);
                        break;
                    case 5:
                        //setCurrentButtonStyle(view, R.id.btn_total_but);
                        break;
                }

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        spinner = (Spinner) findViewById(R.id.spinner_nav);
        CustomSpinnerAdapter spinAdapter = new CustomSpinnerAdapter(
                getApplicationContext(), getResources().getStringArray(R.array.spinner_list_item_array));
        spinner.setAdapter(spinAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapter, View v,
                                       int position, long id) {

                viewPager.setCurrentItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        /*ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);*/


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);




        /*fragment = new FootballFragment();
        setFragmentManager(fragment);*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        //super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        /*MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_list_item_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);*/
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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    /****
     * Setting fragment manager
     ****/

    /*public void setFragmentManager(Fragment fragment) {

        if (!isFinishing()) {
            manager = getSupportFragmentManager();
            transaction = manager.beginTransaction();
            if ((fragment instanceof FootballFragment)) {
                transaction.replace(R.id.container, fragment, "football_tag");
            } else
                transaction.replace(R.id.container, fragment);
            transaction.commitAllowingStateLoss();
        }
    }*/

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

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.cotesport.maroc.programme/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Home Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.cotesport.maroc.programme/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }

    /****
     * Setting fragment manager end
     ****/


    public class TabsPagerAdapter extends FragmentStatePagerAdapter {
        public TabsPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new TabFragment1();
                case 1:
                    return new TabFragment2();
                case 2:
                    return new TabFragment3();
                case 3:
                    return new TabFragment4();
                case 4:
                    return new TabFragment5();
                case 5:
                    return new TabFragment6();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 6;
        }
    }
// Custom Adapter for Spinner
    public class CustomSpinnerAdapter extends ArrayAdapter<String> {

        private Context context1;
        private String[] data;
        public Resources res;
        LayoutInflater inflater;

        public CustomSpinnerAdapter(Context context, String[] objects) {
            super(context, R.layout.spinner_item, objects);

            context1 = context;
            data = objects;

            inflater = (LayoutInflater) context1
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return getCustomView(position, convertView, parent);
        }

        // This funtion called for each row ( Called data.size() times )
        public View getCustomView(int position, View convertView, ViewGroup parent) {

            View row = inflater.inflate(R.layout.spinner_item, parent, false);

            TextView tvCategory = (TextView) row.findViewById(R.id.tvCategory);

            tvCategory.setText(data[position].toString());

            return row;
        }
    }


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

                /*final WebView browser = (WebView) findViewById(R.id.webView);
                browser.getSettings().setJavaScriptEnabled(true);
                browser.addJavascriptInterface(new MyJavaScriptFootballInterface(HomeActivity.this), "HTMLOUT");
                browser.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        browser.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");

                    }
                });
                browser.loadUrl("https://www.mdjsjeux.ma/cote-sport/play");*/
            }
        });


    }

    /*class MyJavaScriptFootballInterface {
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
    }*/


}
