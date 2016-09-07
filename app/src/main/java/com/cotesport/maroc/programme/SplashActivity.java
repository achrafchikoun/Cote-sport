package com.cotesport.maroc.programme;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cotesport.maroc.programme.Models.Football;
import com.cotesport.maroc.programme.Others.Global;
import com.cotesport.maroc.programme.Others.InternetCheking;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = "SplashActivity";
    private String API_URL = "http://achrafchikoun.com/cote_sport_api/Match/getMatchs";
    InternetCheking internet = new InternetCheking(this);
    Football bet;
    String lastDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if( !internet.isInternetAvailable() )
        {
            Toast.makeText(SplashActivity.this, "Aucune connexion internet.", Toast.LENGTH_LONG).show();
            finish();
        }

        getAllMatchs();

        /*final WebView browser = (WebView) findViewById(R.id.webView);
        browser.setVisibility(View.GONE);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.addJavascriptInterface(new MyJavaScriptFootballInterface(SplashActivity.this), "HTMLOUT");
        browser.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
                browser.loadUrl("javascript:window.HTMLOUT.processHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');");
            }
        });
        browser.loadUrl("https://www.mdjsjeux.ma/cote-sport/play");*/

    }

    private void getAllMatchs() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                API_URL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try
                        {
                            ((Global) getApplication()).getListeFootball().clear();
                            for (int i = 0; i < response.length(); i++)
                            {
                                bet = new Football();
                                bet.setDateBet(response.getJSONObject(i).getString("DateBet"));
                                bet.setHeureDebut(response.getJSONObject(i).getString("HeureDebut"));
                                bet.setNumeroBet(response.getJSONObject(i).getString("NumeroBet"));
                                bet.setMinBets(response.getJSONObject(i).getString("MinBets"));
                                bet.setHdcp(response.getJSONObject(i).getString("Hdcp"));
                                bet.setEquipe1(response.getJSONObject(i).getString("Equipe1"));
                                bet.setEquipe2(response.getJSONObject(i).getString("Equipe2"));
                                bet.setBet1(response.getJSONObject(i).getString("Bet1"));
                                bet.setBetx(response.getJSONObject(i).getString("Betx"));
                                bet.setBet2(response.getJSONObject(i).getString("Bet2"));
                                bet.setDoubleChance1x(response.getJSONObject(i).getString("DoubleChance1x"));
                                bet.setDoubleChance12(response.getJSONObject(i).getString("DoubleChance12"));
                                bet.setDoubleChancex2(response.getJSONObject(i).getString("DoubleChancex2"));
                                bet.setHcp1(response.getJSONObject(i).getString("Hcp1"));
                                bet.setHcpx(response.getJSONObject(i).getString("Hcpx"));
                                bet.setHcp2(response.getJSONObject(i).getString("Hcp2"));
                                bet.setMitemps1(response.getJSONObject(i).getString("Mitemps1"));
                                bet.setMitempsx(response.getJSONObject(i).getString("Mitempsx"));
                                bet.setMitemps2(response.getJSONObject(i).getString("Mitemps2"));
                                bet.setMoins(response.getJSONObject(i).getString("moins"));
                                bet.setPlus(response.getJSONObject(i).getString("plus"));

                                bet.setBut01(response.getJSONObject(i).getString("But01"));
                                bet.setBut23(response.getJSONObject(i).getString("But23"));
                                bet.setBut4Plus(response.getJSONObject(i).getString("But4Plus"));

                                ((Global) getApplication()).getListeFootball().add(bet);
                            }

                            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
                            startActivity(intent);
                            SplashActivity.this.finish();
                        }
                        catch(Exception ex)
                        {
                            Toast.makeText(SplashActivity.this, ex.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SplashActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }



    /*class MyJavaScriptFootballInterface
    {
        Context context;
        MyJavaScriptFootballInterface(Context context){
            this.context = context;
        }

        @SuppressWarnings("unused")
        @JavascriptInterface
        public void processHTML(String html){
            ((Global) getApplication()).getListeFootball().clear();
            Document doc = Jsoup.parse(html);
            Element response = doc.getElementById("catsport-1");
            Elements element3 = response.getElementsByTag("tr");

            for (Element e: element3) {

                Football bet = new Football();
                if(e.getElementsByAttributeValue("class", "date_games").size() > 0 ){
                    lastDate = e.getElementsByAttributeValue("class", "date_games").get(0).text();
                }

                if(e.getElementsByTag("td").size() > 1){
                    bet.setDateBet(lastDate);
                    i = 2;
                    Elements ssss3 = e.getElementsByTag("td");
                    for (Element e2: ssss3) {
                        if (i == 2)
                        {
                            bet.HeureDebut = e2.text();
                            i++;
                        }
                        else if (i == 3)
                        {
                            bet.NumeroBet = e2.text();
                            i++;
                        }
                        else if (i == 4)
                        {
                            bet.MinBets = e2.text();
                            i++;
                        }
                        else if (i == 5)
                        {
                            bet.HcpEq1 = e2.text();
                            i++;
                        }
                        else if (i == 6)
                        {
                            bet.Equipe1 = e2.text();
                            i++;
                        }
                        else if (i == 7)
                        {
                            bet.Equipe2 = e2.text();
                            i++;
                        }
                        else if (i == 8)
                        {
                            bet.HcpEq2 = e2.text();
                            i++;
                        }
                        else if (i == 9)
                        {
                            bet.Bet1 = e2.text();
                            i++;
                        }
                        else if (i == 10)
                        {
                            bet.Betx = e2.text();
                            i++;
                        }
                        else if (i == 11)
                        {
                            bet.Bet2 = e2.text();
                            i++;
                        }
                        else if (i == 12)
                        {
                            bet.DoubleChance1x = e2.text();
                            i++;
                        }
                        else if (i == 13)
                        {
                            bet.DoubleChance12 = e2.text();
                            i++;
                        }
                        else if (i == 14)
                        {
                            bet.DoubleChancex2 = e2.text();
                            i++;
                        }
                        else if (i == 15)
                        {
                            bet.Hcp1 = e2.text();
                            i++;
                        }
                        else if (i == 16)
                        {
                            bet.Hcpx = e2.text();
                            i++;
                        }
                        else if (i == 17)
                        {
                            bet.Hcp2 = e2.text();
                            i++;
                        }
                        else if (i == 18)
                        {
                            bet.Mitemps1 = e2.text();
                            i++;
                        }
                        else if (i == 19)
                        {
                            bet.Mitempsx = e2.text();
                            i++;
                        }
                        else if (i == 20)
                        {
                            bet.Mitemps2 = e2.text();
                            i++;
                        }
                        else if (i == 21)
                        {
                            bet.moins = e2.text();
                            i++;
                        }
                        else if (i == 22)
                        {
                            bet.plus = e2.text();
                            i++;
                        }
                        else if (i == 23)
                        {
                            bet.But01 = e2.text();
                            i++;
                        }
                        else if (i == 24)
                        {
                            bet.But23 = e2.text();
                            i++;
                        }
                        else if (i == 25)
                        {
                            bet.But4Plus = e2.text();
                            i++;
                        }
                        else if (i == 26)
                        {
                            bet.MitempsResultatFinal11 = e2.text();
                            i++;
                        }
                        else if (i == 27)
                        {
                            bet.MitempsResultatFinalx1 = e2.text();
                            i++;
                        }
                        else if (i == 28)
                        {
                            bet.MitempsResultatFinal21 = e2.text();
                            i++;
                        }
                        else if (i == 29)
                        {
                            bet.MitempsResultatFinal1x = e2.text();
                            i++;
                        }
                        else if (i == 30)
                        {
                            bet.MitempsResultatFinalxx = e2.text();
                            i++;
                        }
                        else if (i == 31)
                        {
                            bet.MitempsResultatFinal2x = e2.text();
                            i++;
                        }
                        else if (i == 32)
                        {
                            bet.MitempsResultatFinal12 = e2.text();
                            i++;
                        }
                        else if (i == 33)
                        {
                            bet.MitempsResultatFinalx2 = e2.text();
                            i++;
                        }
                        else if (i == 34)
                        {
                            bet.MitempsResultatFinal22 = e2.text();
                            i++;
                        }
                        else if (i == 35)
                        {
                            bet.ScoreExact = e2.text();
                            i++;
                        }
                    }
                    ((Global) getApplication()).getListeFootball().add(bet);
                }
            }

            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    }*/

    /*public class checkInternet extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {

            return null;
        }

    }*/
}
