/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.spinner.Picker;


import java.util.Date;


/**
 *
 * @author Amal
 */
public class Aevenement extends Form {
    
    private final Label l1,l2,l3,l4,l5;
    private final TextField nomTf,descripTf,datef,dated;
     // private final Picker dated;
      //private final Picker datef;
    private final Container mainContainer;
    private final Button editBtn,removeBtn,backBtn;
    private Evenements currentEvent;
    
    public Aevenement(String nom_event,String date_d,String date_f,String descrip_event){
        
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label(""+nom_event);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        l2 = new Label("nom:");
        nomTf= new TextField(nom_event); 
         l3 = new Label("Date Debut:");
       //dated=new Picker();
        dated= new TextField(date_d); 
       // dated.setType(Display.PICKER_TYPE_DATE);
       l4 = new Label("Date Fin:");
      // datef=new Picker();
       datef= new TextField(date_f); 
      // dated.setType(Display.PICKER_TYPE_DATE);
        l5 = new Label("descrip:");
        descripTf= new TextField(descrip_event);
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);

        mainContainer.add(l1);
        mainContainer.add(new Label());
  
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
        mainContainer.add(nomTf);
       
        Statics.setLabelStyle(l3);
        mainContainer.add(l3);
         mainContainer.add(dated);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        mainContainer.add(datef);
         Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        mainContainer.add(descripTf);
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
        backBtn = Statics.createBackBtn(); 
       mainContainer.add(backBtn);
        currentEvent = new Evenements(nom_event, date_d, date_f, descrip_event);
        editBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
           new EvenementDAO().updateEvent(currentEvent);
            });
        removeBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
               new EvenementDAO().deleteEvenement(currentEvent);


            }
               
            
        });
        this.add(BorderLayout.NORTH, mainContainer);
    }
}
