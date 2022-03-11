package br.com.ettec.produtividade.domain;

/**
 * Created by usuario on 27/03/2017.
 */

public class Coordenadas {

    private String latitude;
    private String longitude;

    public Coordenadas() {
    }

    public Coordenadas(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }


}
