package com.example.jeuxu.Inscription.Fragment_inscription;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jeuxu.Common.Common;
import com.example.jeuxu.R;
import com.example.jeuxu.Utils.ParseContent;
import com.rengwuxian.materialedittext.MaterialEditText;

/**
 * A simple {@link Fragment} subclass.
 */
public class Inscription_two extends Fragment {

    MaterialEditText edt_localiter, edt_provenance, edt_date;
    String provenance, localiter, date_naissance;
    Button btnTerminer;
    private ParseContent parseContent;
    private final int jsoncode = 1;


    public Inscription_two() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_inscription_two, container, false);
        edt_localiter = (MaterialEditText) view.findViewById(R.id.edt_localiter);
        edt_provenance = (MaterialEditText) view.findViewById(R.id.edt_provenance);
        edt_date = (MaterialEditText) view.findViewById(R.id.edt_date);
        btnTerminer=(Button)view.findViewById(R.id.btnTerminer);
       // edt_date.addTextChangedListener(new PatternedTextWatcher("####-##-##"));
       parseContent =new ParseContent(getActivity());

        btnTerminer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verification();
            }
        });

        return view;
    }

    public void verification() {
        provenance = edt_provenance.getText().toString().trim();
        localiter = edt_localiter.getText().toString().trim();
        date_naissance = edt_date.getText().toString().trim();

        if (!provenance.isEmpty() || !localiter.isEmpty()) {
            saveData();

        } else {
            Toast.makeText(getContext(), "Remplir tout les champs", Toast.LENGTH_SHORT).show();
        }

    }
    public void saveData(){
        Common.currentClient.setDate_naissance(date_naissance);
        Common.currentClient.setNom_lieux(localiter);
        Common.currentClient.setID_univercity(provenance);
        FragmentManager frman=getFragmentManager();
        FragmentTransaction ftran=frman.beginTransaction();
        Inscription_three inscription_three=new Inscription_three();

        ftran.replace(R.id.register_frame,inscription_three);
        ftran.commit();
    }

//    private void parsejson() {
//        if (!AndyUtils.isNetworkAvailable(getContext())) {
//            Toast.makeText(getContext(), "Internet is required!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        AndyUtils.showSimpleProgressDialog(getContext());
//
//
//        new AsyncTask<Void, Void, String>() {
//
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                String response = "";
//                HashMap<String, String> map = new HashMap<>();
//                map.put("name", Common.currentClient.getName());
//                map.put("surname", Common.currentClient.getSurname());
//                map.put("sexe", Common.currentClient.getSexe());
//                map.put("date_naissance", date_naissance);
//                map.put("email", Common.currentClient.getEmail());
//                map.put("password", Common.currentClient.getPassword());
//                map.put("nom_lieux", localiter);
//                map.put("univercite", provenance);
//                map.put("lat", "0.0");
//                map.put("lng", "0.0");
//
//                try {
//                    HttpRequest request = new HttpRequest(AndyConstant.ServiceType.REGISTER);
//                    response = request.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
//                } catch (IOException e) {
//                    response = e.getMessage();
//                }
//                return response;
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                Log.d("REGISTER_TWO", s);
//                onTaskCompleted(s,jsoncode);
//
//            }
//        }.execute();
//    }
//
//    private void onTaskCompleted(String response, int serviceCode) {
//        Log.d("responsejson", response.toString());
//        switch (serviceCode) {
//            case jsoncode:
//                AndyUtils.removeSimpleProgressDialog();
//                if (parseContent.isSuccess(response)) {
//                    client = parseContent.getInfoClient(response);
////                    final Intent intent = new Intent(getContext(), MainActivity.class);
//                    FragmentManager frman=getFragmentManager();
//                    FragmentTransaction ftran=frman.beginTransaction();
//                    Inscription_three inscription_three=new Inscription_three();
//
//
//                    Gson gson = new Gson();
//                    String myJson = gson.toJson(client);
//                    Bundle bundle=new Bundle();
//                    bundle.putString("user",myJson);
//                    inscription_three.setArguments(bundle);
//                    ftran.replace(R.id.register_frame,inscription_three);
//                    ftran.commit();
////                    intent.putExtra("user", myJson);
////
////                    onPostExecute(intent);
////                    startActivity(intent);
//                } else {
//                    Toast.makeText(getContext(), parseContent.getErrorCode(response), Toast.LENGTH_SHORT).show();
//
//                }
//
//        }
//    }




}
