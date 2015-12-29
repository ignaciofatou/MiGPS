package com.gps.ignacio.migps;

import android.location.Location;
import android.widget.TextView;

/**
 * Created by Ignacio on 22/12/2015.
 */
public class DatosGPS {
    //Atributos Principales
    private TextView txtEstado;
    private TextView txtLatitud;
    private TextView txtLongitud;
    private TextView txtAltidud;
    private TextView txtPrecision;
    private TextView txtTime;
    private TextView txtDirecion;
    private TextView txtSatelites;

    public DatosGPS(TextView txtEstado  , TextView txtLatitud  , TextView txtLongitud,
                    TextView txtAltidud , TextView txtPrecision, TextView txtTime    ,
                    TextView txtDirecion, TextView txtSatelites){
        //Asignamos los Parametros
        this.txtEstado    = txtEstado;
        this.txtLatitud   = txtLatitud;
        this.txtLongitud  = txtLongitud;
        this.txtAltidud   = txtAltidud;
        this.txtPrecision = txtPrecision;
        this.txtTime      = txtTime;
        this.txtDirecion  = txtDirecion;
        this.txtSatelites = txtSatelites;
    }

    public TextView getTxtEstado() {
        return txtEstado;
    }
    public void setTxtEstado(String estado) {
        this.txtEstado.setText(estado);
    }


    public double getTxtLatitud() {
        try{
            return Double.valueOf((String)this.txtLatitud.getText());
        }catch (NumberFormatException ex){
            return 0.0;
        }
    }
    public void setTxtLatitud(double latitud) {
        this.txtLatitud.setText(String.valueOf(latitud));
    }
    public void setTxtLatitud(String latitud) {
        this.txtLatitud.setText(latitud);
    }


    public double getTxtLongitud() {
        try{
            return Double.valueOf((String)this.txtLongitud.getText());
        }catch (NumberFormatException ex){
            return 0.0;
        }
    }
    public void setTxtLongitud(double longitud) {
        this.txtLongitud.setText(String.valueOf(longitud));
    }
    public void setTxtLongitud(String longitud) {
        this.txtLongitud.setText(longitud);
    }


    public TextView getTxtAltidud() {
        return txtAltidud;
    }
    public void setTxtAltidud(double altidud) {
        this.txtAltidud.setText(String.valueOf(altidud));
    }
    public void setTxtAltidud(String altidud) {
        this.txtAltidud.setText(altidud);
    }


    public TextView getTxtPrecision() {
        return txtPrecision;
    }
    public void setTxtPrecision(float precision) {
        this.txtPrecision.setText(String.valueOf(precision));
    }
    public void setTxtPrecision(String precision) {
        this.txtPrecision.setText(precision);
    }


    public TextView getTxtTime() {
        return txtTime;
    }
    public void setTxtTime(long time) {
        this.txtTime.setText(String.valueOf(time));
    }
    public void setTxtTime(String time) {
        this.txtTime.setText(time);
    }


    public String getTxtDirecion() {
        return (String)txtDirecion.getText();
    }
    public void setTxtDirecion(String direccion) {
        this.txtDirecion.setText(direccion);
    }


    public TextView getTxtSatelites() {
        return txtSatelites;
    }
    public void setTxtSatelites(int satelites) {
        this.txtSatelites.setText(String.valueOf(satelites));
    }
    public void setTxtSatelites(String satelites) {
        this.txtSatelites.setText(satelites);
    }
}
