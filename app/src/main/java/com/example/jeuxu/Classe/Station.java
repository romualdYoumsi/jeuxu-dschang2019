package com.example.jeuxu.Classe;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

public class Station implements ClusterItem {
    private String name;
    private String address;
    private Position position;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Position getObjetPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public LatLng getPosition() {
        if (position == null) {

            return null;
        }
        return new LatLng(position.getLat(),position.getLng());
    }

    @Override
    public String getTitle() {
        return getName();
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
