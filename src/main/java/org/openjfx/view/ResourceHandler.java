package org.openjfx.view;

import javafx.scene.image.Image;

/*Author: Carl Manngard, Patrik Emanuelsson, Edward Karlsson, Johan Davidsson
  Responsibility:
  Used by:
  Uses:
  */

class ResourceHandler {

    /*Returns PNG Image called id.png from resource folder src/main/resources/...*/
    static Image getResource(String id){
        String imageUrl = ClassLoader.getSystemResource(id + ".png").toExternalForm();
        return new Image (imageUrl);
    }
}
