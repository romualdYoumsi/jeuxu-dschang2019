package com.example.jeuxu.Detail_Sport.Fragment_Detail;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jeuxu.AdapterClass.JoueurOneAdapter;
import com.example.jeuxu.AdapterClass.JoueurTwoAdapter;
import com.example.jeuxu.Classe.Joueur;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Composition extends Fragment {
    RecyclerView joueur_one, joueur_two;

    public Composition() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_composition, container, false);

        joueur_one = (RecyclerView) view.findViewById(R.id.lst_composition_equipe_one);
        joueur_two = (RecyclerView) view.findViewById(R.id.lst_composition_equipe_two);
       display();

        return view;
    }

    public void display() {
        List<Joueur> joueurs = Common.currentRencontre.getEquipesList().get(0).joueurList;
        List<Joueur> joueurs1 = Common.currentRencontre.getEquipesList().get(1).joueurList;

        JoueurOneAdapter joueurOneAdapter = new JoueurOneAdapter(getActivity(), joueurs);
        JoueurTwoAdapter joueurTwoAdapter = new JoueurTwoAdapter(getActivity(), joueurs1);

       joueur_one.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        joueur_one.setAdapter(joueurOneAdapter);
        joueur_two.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        joueur_two.setAdapter(joueurTwoAdapter);

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        display();
    }

}
