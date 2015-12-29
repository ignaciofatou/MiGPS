package com.gps.ignacio.migps;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ignacio on 23/07/2015.
 */
	/* Class My Location Listener */
public class MyGPS implements LocationListener {

    Activity activity;
    DatosGPS datosGPS;
    LocationManager locManager;

    public MyGPS(Activity activity, DatosGPS datosGPS){
        //Almacenamos los Parametros del Constructor
        this.activity = activity;
        this.datosGPS = datosGPS;

        //Iniciamos el Location Manager
        startLocationManager();
    }

    @Override
    public void onLocationChanged(Location location) {
        //Asignamos los Valores de las Nuevas Posiciones
        if (datosGPS != null) {
            datosGPS.setTxtEstado("ON");
            datosGPS.setTxtLatitud(location.getLatitude());
            datosGPS.setTxtLongitud(location.getLongitude());
            datosGPS.setTxtAltidud(location.getAltitude());
            datosGPS.setTxtPrecision(location.getAccuracy());
            datosGPS.setTxtTime(location.getTime());
            datosGPS.setTxtDirecion(getDireccion());
            datosGPS.setTxtSatelites(getNumSaltellites());
        }
    }

    @Override
    public void onProviderDisabled(String provider) {
        //Al Apagar el GPS reiniciamos las Coordenadas
        datosGPS.setTxtEstado("SIN CONEXION");
        datosGPS.setTxtLatitud("-");
        datosGPS.setTxtLongitud("-");
        datosGPS.setTxtAltidud("-");
        datosGPS.setTxtPrecision("-");
        datosGPS.setTxtTime("-");
        datosGPS.setTxtDirecion("-");
        datosGPS.setTxtSatelites("-");
    }

    @Override
    public void onProviderEnabled(String provider) {
        datosGPS.setTxtEstado("ON");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        datosGPS.setTxtEstado(provider + ":" + String.valueOf(status));
    }

    private String getDireccion(){
        //Si viene con una Posicion
        if (datosGPS.getTxtLatitud() != 0.0 && datosGPS.getTxtLongitud() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(activity, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(datosGPS.getTxtLatitud(), datosGPS.getTxtLongitud(), 1);
                if (!list.isEmpty()) {
                    Address address = list.get(0);
                    return address.getAddressLine(0);
                }
            } catch (IOException e) {
                return datosGPS.getTxtDirecion();
            }
        }
        return "";
    }

    private int getNumSaltellites(){
        GpsStatus status = locManager.getGpsStatus(null);
        Iterable<GpsSatellite> satellites = status.getSatellites();
        int numSatelites=0;
        while (satellites.iterator().hasNext())
        {
            GpsSatellite gpsSatellite= satellites.iterator().next();
            if (gpsSatellite.usedInFix())
                numSatelites++;
        }
        return numSatelites;
    }

    //Desactiva el GPS
    public void apagaGPS()
    {
        String provider = Settings.Secure.getString(activity.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        //Si el GPS esta activo
        if (provider.contains("gps")){
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            activity.sendBroadcast(poke);
        }
        //Anulamos el Location Manager
        offLocationManager();

        //Finalizamos esta Clase
        try {
            this.finalize();
        } catch (Throwable throwable) {
            System.out.println("Fallo al Matar la clase");
        }
    }

    private void startLocationManager(){
        if (locManager != null)
            offLocationManager();

        locManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 0, this);
        locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0, this);
    }
    private void offLocationManager(){
        //Eliminamos el Proceso de
        if (locManager != null) {
            locManager.removeUpdates(this);
            locManager = null;
        }
    }
}/* End of Class MyLocationListener */
