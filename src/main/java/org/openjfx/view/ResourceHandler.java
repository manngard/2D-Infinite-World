package org.openjfx.view;

import javafx.scene.image.Image;

public class ResourceHandler {

    //Fetches PNG Image called id.png
    public Image getResource(String id){
        ClassLoader classLoader = getClass().getClassLoader();
        String imageUrl = ClassLoader.getSystemResource(id + ".png").toExternalForm();
        Image image = new Image(imageUrl);
        return image;
    }

}
