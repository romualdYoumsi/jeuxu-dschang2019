package com.example.jeuxu.Inscription.Fragment_inscription;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.jeuxu.Classe.Client;
import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.scottyab.showhidepasswordedittext.ShowHidePasswordEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inscription_one extends Fragment {


    MaterialEditText edt_first_name, edt_last_name, edt_email;
    ShowHidePasswordEditText edt_passwd,  edt_conf_passwd;
    Spinner Spsexe;
    String name, last_name, email, passwd, cfPasswd, sexe;
    Button btnSuivant;

    public Inscription_one() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inscription_one, container, false);

        edt_first_name = (MaterialEditText) view.findViewById(R.id.edt_first_name);
        edt_last_name = (MaterialEditText) view.findViewById(R.id.edt_last_name);
        edt_email = (MaterialEditText) view.findViewById(R.id.edt_email);
        edt_passwd = (ShowHidePasswordEditText) view.findViewById(R.id.edt_passwd);
        edt_conf_passwd = (ShowHidePasswordEditText) view.findViewById(R.id.edt_conf_passwd);
        btnSuivant = (Button) view.findViewById(R.id.btnSuivant);
        Spsexe = (Spinner) view.findViewById(R.id.spsexe);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.list_spinner_sexe));
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spsexe.setAdapter(myAdapter);

        btnSuivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification();

            }
        });


        return view;
    }


    public void verification() {
        name = edt_first_name.getText().toString().trim();
        last_name = edt_last_name.getText().toString().trim();
        email = edt_email.getText().toString().trim();
        passwd = edt_passwd.getText().toString().trim();
        cfPasswd = edt_conf_passwd.getText().toString().trim();
        sexe = Spsexe.getSelectedItem().toString();
        if (!name.isEmpty() && !last_name.isEmpty() && !email.isEmpty() && !passwd.isEmpty() && !cfPasswd.isEmpty()) {
            if (passwd.equals(cfPasswd)) {
                saveDate();
                FragmentManager frman = getFragmentManager();
                FragmentTransaction ftran = frman.beginTransaction();
                Inscription_two inscription_two = new Inscription_two();
                ftran.replace(R.id.register_frame, inscription_two);
                ftran.commit();
            } else {
                Toast.makeText(getContext(), "Verifier mot de passe", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "Remplir tout les champs", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveDate() {
        Client client = new Client();
        client.setEmail(email);
        client.setName(name);
        client.setPassword(passwd);
        client.setSexe(sexe);
        client.setSurname(last_name);
        Common.currentClient = null;
        Common.currentClient = client;
    }

}
