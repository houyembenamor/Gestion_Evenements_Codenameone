/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.location.Location;
import com.codename1.location.LocationManager;
import com.codename1.maps.Coord;
import com.codename1.maps.MapComponent;
import com.codename1.maps.layers.PointLayer;
import com.codename1.maps.layers.PointsLayer;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 *
 * @author HOUYEM BENAMOR
 */
public class Map extends Form{
      //ImageViewer img;
 private Coord lastLocation;
 private    Form map = new Form("Map");
    public Map(Resources theme) {
             
// img = new ImageViewer(theme.getImage("unnamed.png"));
      // Form main = new Form("Maps Demo");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        Button b = new Button("Where am I?");
        addComponent(b);
        b.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
                try {
                    showMeOnMap();
                } catch (IOException ex) {
                  
                }
            }
        });
      

        show();
    }
     private void showMeOnMap() throws IOException {
        
        map.setLayout(new BorderLayout());
        map.setScrollable(false);
       final MapComponent mc = new MapComponent();
 
  
       mc.zoomToLayers();

     map.addComponent(BorderLayout.CENTER, mc);
  putMeOnMap(mc);
        map.getToolbar().addCommandToLeftBar(new Map.BackCommand());
        map.setBackCommand(new Map.BackCommand());
        map.show();

    }

     private void putMeOnMap(MapComponent mc) throws IOException {
         Location loc = LocationManager.getLocationManager().getCurrentLocation();
         lastLocation = new Coord(loc.getLatitude(), loc.getLongtitude());
       Image i = Image.createImage("/bleu.png");
         PointsLayer pl = new PointsLayer();
         pl.setPointIcon(i);
         PointLayer p = new PointLayer(lastLocation, "loc", i);
         p.setDisplayName(true);
         pl.addPoint(p);
         pl.addActionListener(new ActionListener() {
             
             public void actionPerformed(ActionEvent evt) {
                 PointLayer p = (PointLayer) evt.getSource();
                 System.out.println("pressed " + p);
                 
                 Dialog.show("Details", "You Are Here" + "\n" + p.getLatitude() + "|" + p.getLongitude(), "Ok",null);
             }
         });
         mc.addLayer(pl);

    }
class BackCommand extends Command {

        public BackCommand() {
            super("");
            FontImage img = FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, UIManager.getInstance().getComponentStyle("TitleCommand"));
            setIcon(img);
        }

        public void actionPerformed(ActionEvent evt) {
            showBack();
        }
    }
}

