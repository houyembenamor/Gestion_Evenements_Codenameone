/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;


import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;

import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;



import com.codename1.ui.layouts.BorderLayout;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


import java.util.Map;
import java.util.ArrayList;
import java.util.List;






/**
 *
 * @author Amal
 */
public class EvenementDAO {
        
  private ConnectionRequest connectionRequest;
    public static Form listOfEvents;
     public static Evenements e = new Evenements();
   
  
   
      public void addEvenement(Evenements e){
        connectionRequest=new ConnectionRequest(){

            
             @Override
            protected void readResponse(InputStream in) throws IOException {
                System.out.println(in);

            }};
        // DateFormat simpleDateFormat = new SimpleDateFormat("'MM/dd/yy' ")  ;
          SimpleDateFormat simpleDateFormat= new SimpleDateFormat("dd-MM-yy");
        connectionRequest.setUrl("http://localhost/dbevenements/insert.php?nom_event=" + e.getNom_event()+ "&date_d=" +simpleDateFormat.format( e.getDate_d())+"&date_f="+simpleDateFormat.format( e.getDate_f())+"&descrip_event="+e.getDescrip_event());
        NetworkManager.getInstance().addToQueue(connectionRequest);//faciliter les demandes de service Web 
    }
      
      public void deleteEvenement(Evenements b) {
        
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void readResponse(InputStream in) throws IOException {
                System.out.println(in);
 }
@Override
                    protected void postResponse() {
                       Dialog.show("Event deleted ", null, "Ok", null);

                    }

 };

        connectionRequest.setUrl("http://localhost/dbevenements/remove.php?nom_event=" + b.getNom_event());
       
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
  
  
  
  public void getEvenements() {
       
        connectionRequest = new ConnectionRequest() {
        List<Evenements> events = new ArrayList<>();
            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    Map<String, Object> data = json.parseJSON(reader);
                    List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                    events.clear();
                  
                    for (Map<String, Object> obj : content) {
                       DateFormat simpleDateFormat = new SimpleDateFormat("'yyyy-MM-dd' ")  ;
                        events.add(new Evenements((String) obj.get("nom_event"),
         (String) obj.get("date_d"),(String) obj.get("date_f"),(String) obj.get("descrip_event"))
                        );
                    }
                } catch (IOException err) {
                    Log.e(err);
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfEvents = new Form();
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for(Evenements l :events){
                    libsNoms.add(l.getNom_event());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Evenements currentBook = events.get(uiLibsList.getCurrentSelected());
                        new Aevenement(currentBook.getNom_event(),currentBook.getDate_d(),currentBook.getDate_f(),currentBook.getDescrip_event()).show();
                    }
                });
                listOfEvents.setLayout(new BorderLayout());
                listOfEvents.add(BorderLayout.NORTH,uiLibsList);
                //listOfBooks.add(BorderLayout.SOUTH,Statics.createBackBtn());
                listOfEvents.show();             
            }
         
        };
        connectionRequest.setUrl("http://localhost/dbevenements/getevenements.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
  
  
  
    public void updateEvent(Evenements b){
        connectionRequest = new ConnectionRequest() {
            
            @Override
            protected void postResponse() { 
             /*   Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Event updated");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                d.show();*/
                
              
                          Dialog.show("Event updated ", null, "Ok", null);
                         
            }
             @Override
            protected void readResponse(InputStream in) throws IOException {
                System.out.println(in);

            }
        };
           SimpleDateFormat simpleDateFormat= new SimpleDateFormat("'MM/dd/yy' ");
        connectionRequest.setUrl("http://localhost/dbevenements/update.php?id_event="+b.getId_event());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

  
  
    
     public ArrayList<Evenements> getList(String json) {
        ArrayList<Evenements> listevent = new ArrayList<>();
        System.out.println("JSON*************\n"+json);
        try {

            JSONParser j = new JSONParser();
            
            Map<String, Object> evenements = j.parseJSON(new CharArrayReader(json.toCharArray()));

            System.out.println();
            List<Map<String, Object>> list = (List<Map<String, Object>>) evenements.get("root");

            for (Map<String, Object> pbu : list) {
        Evenements e = new Evenements ();
 
           e.setId_event((int) Float.parseFloat(pbu.get("id").toString()));
      e.setNom_event(pbu.get("nom_event").toString());            
e.setDate_d(pbu.get("date_d").toString());
e.setDate_f(pbu.get("date_f").toString());
e.setDescrip_event(pbu.get("descrip_event").toString());

                System.out.println(e.toString());
                listevent.add(e);

            }

        } catch (IOException ex) {
        }
        return listevent;

    }
    
   

    
    
    /*  public boolean verifier(TextField nom_event, TextField date_d, TextField date_f, TextField descrip_event) {

        if (nom_event.getText().length() == 0) {
            Dialog.show("Erreur", "Veuillez verifie rvotre ADRESSE", "OK", "CANCEL");
            nom_event.requestFocus();
            return false;
        }
        if (date_d.getText().length() == 0) {
            Dialog.show("Erreur", "Veuillez verifie rvotre NUMERO", "OK", "CANCEL");
            date_d.requestFocus();
            return false;
        }

        if (date_f.getText().length() == 0) {
            Dialog.show("Erreur", "Veuillez verifier votre E-MAIL", "OK", "CANCEL");
            date_f.requestFocus();
            return false;
        }

            if (descrip_event.getText().length() == 0) {
            Dialog.show("Erreur", "Veuillez verifier votre E-MAIL", "OK", "CANCEL");
            descrip_event.requestFocus();
            return false;
        }
        return true;
    }*/
    
    
    
}
