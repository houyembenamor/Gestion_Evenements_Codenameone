/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.l10n.Format;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import java.util.Date;





/**
 *
 * @author HOUYEM BENAMOR
 */
public class Evenements {
    private int id_event;
    private String nom_event;
    private String date_d;
    private String date_f;
    private String descrip_event;

    public Evenements(int id_event, String nom_event, String date_d, String date_f, String descrip_event) throws ParseException {
        this.id_event = id_event;
        this.nom_event = nom_event;
        this.date_d = date_d;
        this.date_f = date_f;
        this.descrip_event = descrip_event;
  

    }

    public Evenements(){

    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom_event() {
        return nom_event;
    }

    public void setNom_event(String nom_event) {
        this.nom_event = nom_event;
    }



    public String getDescrip_event() {
        return descrip_event;
    }

    public void setDescrip_event(String descrip_event) {
        this.descrip_event = descrip_event;
    }

    public Evenements(String nom_event, String descrip_event) {
        this.nom_event = nom_event;
        this.descrip_event = descrip_event;
    }

    public String toString() {
           Format formatter = new SimpleDateFormat("yyyy-mm-dd");

        String s = formatter.format(date_d);
        String s2 = formatter.format(date_f);
        return "Evenements{" + "id_event=" + id_event + ", nom_event=" + nom_event + ", date_d=" + s + ", date_f=" + s2 + ", descrip_event=" + descrip_event + '}';
    }

    public Evenements(String nom_event, String date_d, String date_f, String descrip_event) {
        this.nom_event = nom_event;
        this.date_d = date_d;
        this.date_f = date_f;
        this.descrip_event = descrip_event;
 

    }

    public String getDate_d() {
        return date_d;
    }

    public void setDate_d(String date_d) {
        this.date_d = date_d;
    }

    public String getDate_f() {
        return date_f;
    }

    public void setDate_f(String date_f) {
        this.date_f = date_f;
    }

 

  

    
    
    
}
