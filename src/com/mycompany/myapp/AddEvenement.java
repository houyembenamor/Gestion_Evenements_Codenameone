/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;


import com.codename1.components.FloatingActionButton;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.Component.CENTER;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import java.util.Date;



/**
 *
 * @author HOUYEM BENAMOR
 */
public class AddEvenement extends Form {

    
    private final Label l1,l2,l3,l4,l5;
    private final TextField nomtf,descriptf;
     private final Picker date_d;
      private final Picker date_f;
    private final Container mainContainer;
    private final Button addBtn,backBtn ;
   
    private Resources theme;
   public AddEvenement(){
      
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
   
        mainContainer.setLayout(new GridLayout(8,2));
        
        l1 = new Label("Add new event");
        l1.setAlignment(CENTER);
      
        l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
         // addEventBtn = new Button("");
      //  FloatingActionButton addEventBtn = FloatingActionButton.createFAB(FontImage.MATERIAL_ADD);
     // mainContainer.add(addEventBtn);
//     ImageViewer Boss = new ImageViewer(theme.getImage("edit-button_t.jpg"));
 nomtf = new TextField(""); 
        l2 = new Label("Nom:");
        date_d=new Picker();
        l3 = new Label("Date Debut:");
       
      
      date_d.toString();
       l4 = new Label("Date Fin:");
       date_f=new Picker();
       date_f.toString();
        l5 = new Label("Description:");
        descriptf =  new TextField("");
        System.out.println(date_d);

        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer.add(l1);
       // mainContainer.add(new Label());
     
        Statics.setLabelStyle(l2);
        mainContainer.add(l2);
       mainContainer.add(nomtf);
        Statics.setLabelStyle(l4);
        mainContainer.add(l4);
        mainContainer.add(date_d);
          Statics.setLabelStyle(l3);
        mainContainer.add(l3);
        mainContainer.add(date_f);
           Statics.setLabelStyle(l5);
        mainContainer.add(l5);
        mainContainer.add(descriptf);
     
        
      
      
        mainContainer.add(addBtn);
        
       backBtn = Statics.createBackBtn(); 
        
       mainContainer.add(backBtn);
      
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            Evenements typedBook = new Evenements(nomtf.getText(),date_d.getText(),date_f.getText(),descriptf.getText()
                    );
            new  EvenementDAO().addEvenement(typedBook);
            });
        
        
        /* addEventBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddEvenement addBookUi = new AddEvenement();
                addBookUi.show();
            }
        });*/
        
        
        
        
        this.add(BorderLayout.NORTH, mainContainer);
   }
   
   
   
   
   
            private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);


}


private Slider createStarRankSlider() {
    Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(10);
    Font fnt = Font.createTrueTypeFont(
"native:MainLight"
, "native:MainLight"). derive(Display.getInstance().convertToPixels( 5, true), Font.STYLE_PLAIN);
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(
100);
    s.setFgColor(
0);
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
return
 starRank;
}
private void showStarPickingForm() {
 // Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
 mainContainer.add(FlowLayout.encloseCenter(createStarRankSlider()));
// mainContainer.show();
}


}
