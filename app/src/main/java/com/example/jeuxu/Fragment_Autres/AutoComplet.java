package com.example.jeuxu.Fragment_Autres;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;

import com.example.jeuxu.AdapterClass.PLaceAutoCompleteAdapter;
import com.example.jeuxu.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;

/**
 * A simple {@link Fragment} subclass.
 */
public class AutoComplet extends Fragment implements GoogleApiClient.OnConnectionFailedListener {


    private AutoCompleteTextView mSearhText;
    public AutoComplet() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view= inflater.inflate(R.layout.fragment_auto_complet, container, false);

        mSearhText=(AutoCompleteTextView)view.findViewById(R.id.txt_auto_name_map);

        final LatLngBounds latLngBounds=new LatLngBounds(
                new LatLng(-40,-168),new LatLng(71,136)
        );

        GoogleApiClient mGoogleApiClient=new GoogleApiClient
                .Builder(this.getContext())
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this.getActivity(),this)
                .build();

        PLaceAutoCompleteAdapter pLaceAutoCompleteAdapter=new PLaceAutoCompleteAdapter(this.getContext(),mGoogleApiClient,
                latLngBounds,null);
        mSearhText.setAdapter(pLaceAutoCompleteAdapter);
        return view;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
