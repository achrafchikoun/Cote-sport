package com.cotesport.maroc.toto.Others;

import android.app.Application;

import com.cotesport.maroc.toto.Models.Football;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Achraf on 17/07/2016.
 */
public class Global extends Application {
    public List<Football> listeFootball = new ArrayList<>();

    public List<Football> getListeFootball() {
        return listeFootball;
    }

    public void setListeFootball(List<Football> listeFootball) {
        this.listeFootball = listeFootball;
    }
}
