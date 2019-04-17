package com.example.jeuxu.Detail_Sport.Fragment_Detail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jeuxu.Classe.Statistique_rencontre;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Statist extends Fragment {

    RecyclerView statist,ls_statistique2;


    public Statist() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_statist, container, false);
        statist = (RecyclerView) view.findViewById(R.id.ls_statistique);
        ls_statistique2=(RecyclerView)view.findViewById(R.id.ls_statistique2);
        display();
        return view;
    }

    public void display() {
        List<Statistique_rencontre> rencontreList=Common.currentRencontre.getEquipesList().get(0).getStatistiqueRencontres();
        List<Statistique_rencontre> rencontreList1=Common.currentRencontre.getEquipesList().get(1).getStatistiqueRencontres();

        StatAdapter statAdapter=new StatAdapter(getContext(),rencontreList);
        StatAdapter_two statAdapter_two=new StatAdapter_two(getContext(),rencontreList1);

        statist.setLayoutManager(new GridLayoutManager(getActivity(),1));
        statist.setAdapter(statAdapter);

        ls_statistique2.setLayoutManager(new GridLayoutManager(getActivity(),1));
        ls_statistique2.setAdapter(statAdapter_two);


    }


}
