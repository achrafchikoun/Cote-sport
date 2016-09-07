package com.cotesport.maroc.programme.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cotesport.maroc.programme.R;
import com.cotesport.maroc.programme.Tabs.TabFragment1;
import com.cotesport.maroc.programme.Tabs.TabFragment2;
import com.cotesport.maroc.programme.Tabs.TabFragment3;
import com.cotesport.maroc.programme.Tabs.TabFragment4;
import com.cotesport.maroc.programme.Tabs.TabFragment5;
import com.cotesport.maroc.programme.Tabs.TabFragment6;
import com.cotesport.maroc.programme.Tabs.TabFragment8;

public class FootballFragment extends Fragment implements View.OnClickListener {

    TabsPagerAdapter tabsPagerAdapter;
    ViewPager viewPager;
    Button btn_tab1, btn_tab2, btn_tab3, btn_tab4, btn_tab5, btn_tab6, btn_tab7, btn_tab8, current_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FootballFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BasketballFragment.
     */
    // TODO: Rename and change types and number of parameters


    public static FootballFragment newInstance(String param1, String param2) {
        FootballFragment fragment = new FootballFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_football, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        tabsPagerAdapter = new TabsPagerAdapter(getFragmentManager());

        viewPager.setAdapter(tabsPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        setCurrentButtonStyle(view, R.id.btn_resultat_final);
                        break;
                    case 1:
                        setCurrentButtonStyle(view, R.id.btn_double_chance);
                        break;
                    case 2:
                        setCurrentButtonStyle(view, R.id.btn_hdc);
                        break;
                    case 3:
                        setCurrentButtonStyle(view, R.id.btn_mi_temps);
                        break;
                    case 4:
                        setCurrentButtonStyle(view, R.id.btn_moins_plus);
                        break;
                    case 5:
                        setCurrentButtonStyle(view, R.id.btn_total_but);
                        break;
                    case 6:
                        setCurrentButtonStyle(view, R.id.btn_mi_temp_resultat_final);
                        break;
                }

            }
        });

        btn_tab1 = (Button) view.findViewById(R.id.btn_resultat_final);
        btn_tab2 = (Button) view.findViewById(R.id.btn_double_chance);
        btn_tab3 = (Button) view.findViewById(R.id.btn_hdc);
        btn_tab4 = (Button) view.findViewById(R.id.btn_mi_temps);
        btn_tab5 = (Button) view.findViewById(R.id.btn_moins_plus);
        btn_tab6 = (Button) view.findViewById(R.id.btn_total_but);
        btn_tab8 = (Button) view.findViewById(R.id.btn_mi_temp_resultat_final);
        btn_tab1.setOnClickListener(this);
        btn_tab2.setOnClickListener(this);
        btn_tab3.setOnClickListener(this);
        btn_tab4.setOnClickListener(this);
        btn_tab5.setOnClickListener(this);
        btn_tab6.setOnClickListener(this);
        btn_tab8.setOnClickListener(this);
        setCurrentButtonStyle(view, R.id.btn_resultat_final);

        return view;
    }


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
                case 6:
                    return new TabFragment8();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 7;
        }
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btn_resultat_final:
                viewPager.setCurrentItem(0);
                setCurrentButtonStyle(view, R.id.btn_resultat_final);
                break;
            case R.id.btn_double_chance:
                viewPager.setCurrentItem(1);
                setCurrentButtonStyle(view, R.id.btn_double_chance);
                break;
            case R.id.btn_hdc:
                viewPager.setCurrentItem(2);
                setCurrentButtonStyle(view, R.id.btn_hdc);
                break;
            case R.id.btn_mi_temps:
                viewPager.setCurrentItem(3);
                setCurrentButtonStyle(view, R.id.btn_mi_temps);
                break;
            case R.id.btn_moins_plus:
                viewPager.setCurrentItem(4);
                setCurrentButtonStyle(view, R.id.btn_moins_plus);
                break;
            case R.id.btn_total_but:
                viewPager.setCurrentItem(5);
                setCurrentButtonStyle(view, R.id.btn_total_but);
                break;
            case R.id.btn_mi_temp_resultat_final:
                viewPager.setCurrentItem(6);
                setCurrentButtonStyle(view, R.id.btn_mi_temp_resultat_final);
                break;
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setCurrentButtonStyle(View view , int id){
        btn_tab1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab1.setTextColor(Color.BLACK);
        btn_tab2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab2.setTextColor(Color.BLACK);
        btn_tab3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab3.setTextColor(Color.BLACK);
        btn_tab4.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab4.setTextColor(Color.BLACK);
        btn_tab5.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab5.setTextColor(Color.BLACK);
        btn_tab6.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab6.setTextColor(Color.BLACK);
        btn_tab8.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
        btn_tab8.setTextColor(Color.BLACK);

        current_btn = (Button) view.findViewById(id);
        current_btn.setTextColor(getResources().getColor(R.color.colorPrimary));
        current_btn.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_button_cote_sport
                , 0, 0, 0);
    }
}
