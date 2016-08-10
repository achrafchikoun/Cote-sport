package com.cotesport.maroc.toto.Tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cotesport.maroc.toto.Others.Global;
import com.cotesport.maroc.toto.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

public class TabFragment6 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerView;
    List<String> listeDate = new ArrayList<>();
    List<Integer> listeDatePosition = new ArrayList<>();

    public TabFragment6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TabFragment6.
     */
    // TODO: Rename and change types and number of parameters
    public static TabFragment6 newInstance(String param1, String param2) {
        TabFragment6 fragment = new TabFragment6();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //super.onSaveInstanceState(outState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_fragment6, container, false);

        MobileAds.initialize(getActivity().getApplicationContext(), "ca-app-pub-6158022415806589/3630392558");
        AdView mAdView = (AdView) view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        recyclerView = (RecyclerView) view.findViewById(R.id.rvFragment6);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        RVAdapter rva = new RVAdapter();
        recyclerView.setAdapter(rva);
        return view;
    }

    public class RVAdapter extends RecyclerView.Adapter<RVAdapter.FootballViewHolder> {

        RVAdapter() {
        }

        @Override
        public FootballViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_items_fragment_6, parent, false);
            FootballViewHolder pvh = new FootballViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(FootballViewHolder holder, int position) {

            if (!listeDate.contains(((Global) getActivity().getApplication()).getListeFootball().get(position).getDateBet())) {

                listeDate.add(((Global) getActivity().getApplication()).getListeFootball().get(position).getDateBet());
                listeDatePosition.add(position);
                holder.txt_date.setVisibility(View.VISIBLE);

                holder.txt_date.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getDateBet());
                holder.txt_debut.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getHeureDebut());
                holder.txt_c.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getNumeroBet());
                holder.txt_min.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getMinBets());
                holder.txt_equipe1.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getEquipe1());
                holder.txt_equipe2.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getEquipe2());
                holder.txt_0_1.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut01());
                holder.txt_2_3.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut23());
                holder.txt_4_plus.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut4Plus());
            } else if (listeDate.contains(((Global) getActivity().getApplication()).getListeFootball().get(position).getDateBet())
                    && listeDatePosition.contains(position)) {

                holder.txt_date.setVisibility(View.VISIBLE);

                holder.txt_date.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getDateBet());
                holder.txt_debut.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getHeureDebut());
                holder.txt_c.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getNumeroBet());
                holder.txt_min.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getMinBets());
                holder.txt_equipe1.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getEquipe1());
                holder.txt_equipe2.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getEquipe2());
                holder.txt_0_1.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut01());
                holder.txt_2_3.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut23());
                holder.txt_4_plus.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut4Plus());

            } else {
                holder.txt_date.setVisibility(View.GONE);
                holder.txt_debut.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getHeureDebut());
                holder.txt_c.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getNumeroBet());
                holder.txt_min.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getMinBets());
                holder.txt_equipe1.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getEquipe1());
                holder.txt_equipe2.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getEquipe2());
                holder.txt_0_1.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut01());
                holder.txt_2_3.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut23());
                holder.txt_4_plus.setText(((Global) getActivity().getApplication()).getListeFootball().get(position).getBut4Plus());
            }

            //Change lines colors
            if (position % 2 == 0) {
                //holder.cardView.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_debut.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_c.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_min.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_equipe1.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_equipe2.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_0_1.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_2_3.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
                holder.txt_4_plus.setBackgroundResource(R.drawable.textview_bet_drawable_paire);
            } else {
                //holder.cardView.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_debut.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_c.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_min.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_equipe1.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_equipe2.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_0_1.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_2_3.setBackgroundResource(R.drawable.textview_bet_drawable);
                holder.txt_4_plus.setBackgroundResource(R.drawable.textview_bet_drawable);
            }

            //Change min match color
            if (((Global) getActivity().getApplication()).getListeFootball().get(position).getMinBets().equals("3"))
                holder.txt_min.setBackgroundResource(R.drawable.textview_min_bet_3);
            else if (((Global) getActivity().getApplication()).getListeFootball().get(position).getMinBets().equals("2"))
                holder.txt_min.setBackgroundResource(R.drawable.textview_min_bet_2);
            else if (((Global) getActivity().getApplication()).getListeFootball().get(position).getMinBets().equals("1"))
                holder.txt_min.setBackgroundResource(R.drawable.textview_min_bet_1);

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public int getItemCount() {
            return ((Global) getActivity().getApplication()).getListeFootball().size();
        }

        public class FootballViewHolder extends RecyclerView.ViewHolder {
            TextView txt_date, txt_debut, txt_c, txt_min, txt_equipe1, txt_equipe2, txt_0_1, txt_2_3, txt_4_plus;
            CardView cardView;

            FootballViewHolder(View view) {
                super(view);
                cardView = (CardView) view.findViewById(R.id.cv6);
                txt_date = (TextView) view.findViewById(R.id.txtDate);
                txt_debut = (TextView) view.findViewById(R.id.txtDebut);
                txt_c = (TextView) view.findViewById(R.id.txtC);
                txt_min = (TextView) view.findViewById(R.id.txtMin);
                txt_equipe1 = (TextView) view.findViewById(R.id.txtEquipe1);
                txt_equipe2 = (TextView) view.findViewById(R.id.txtEquipe2);
                txt_0_1 = (TextView) view.findViewById(R.id.txt01);
                txt_2_3 = (TextView) view.findViewById(R.id.txt23);
                txt_4_plus = (TextView) view.findViewById(R.id.txt4Plus);
            }
        }

    }

}
