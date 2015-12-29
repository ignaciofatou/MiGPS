package com.gps.ignacio.migps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvEstado;
    TextView tvLatitud;
    TextView tvLongitud;
    TextView tvAltitud;
    TextView tvPrecision;
    TextView tvTime;
    TextView tvDireccion;
    TextView tvSatelites;
    MyGPS myGPS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Recuperamos los Elementos de la Ventana
        setContentView(R.layout.activity_main);
        tvEstado    = (TextView) findViewById(R.id.estado);
        tvLatitud   = (TextView) findViewById(R.id.latitud);
        tvLongitud  = (TextView) findViewById(R.id.longitud);
        tvAltitud   = (TextView) findViewById(R.id.altitud);
        tvPrecision = (TextView) findViewById(R.id.precision);
        tvTime      = (TextView) findViewById(R.id.time);
        tvDireccion = (TextView) findViewById(R.id.direccion);
        tvSatelites = (TextView) findViewById(R.id.satelites);

        //Agregamos GPS a nuestra aplicacion, en messageTextView se actualizara la posicion
        DatosGPS datosGPS = new DatosGPS(tvEstado, tvLatitud, tvLongitud, tvAltitud, tvPrecision, tvTime, tvDireccion, tvSatelites);
        myGPS = new MyGPS(this, datosGPS);
    }

    @Override
    public void onBackPressed(){
        myGPS.apagaGPS();
        myGPS = null;
        super.onBackPressed();
        this.finish();
    }
}
