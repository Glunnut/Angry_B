package Livrable2.view;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;


public abstract class ImageTool {
    /**
     * Converti une image donnee en une image bufferise
     * 
     * @param img l'image qui doit etre converti
     * @return l'image bufferisee
     */
   public static BufferedImage toBufferedImage(Image img){
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        // Cree une image bufferisee en transparence 
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        // Dessine l'image 
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        // Retourne l'image bufferisee
        return bimage;
    }

    /**
     * Converti une image bufferise en une image donnee 
     * 
     * @param bimage l'image bufferisee qui doit etre converti
     * @return l'image converti
     */
    public static Image toImage(BufferedImage bimage){
        // On effectue un cast sur l'image
        Image img = (Image) bimage;
        return img;
    }
    /**
     * Creation d'une image vide transparente
     * 
     * @param width largeur de l'image
     * @param height hauteur de l'image
     * @return l'image cree
     */
    public static Image getEmptyImage(int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        return toImage(img);
    }

    /**
     * Effectue une rotation de l'image
     * 
     * @param img l'image sur laquelle on effectue la rotation
     * @param angle le degree de rotation
     * @return l'image apres rotation
     */
    public static Image rotate(Image img, double angle){
        double sin = Math.abs(Math.sin(Math.toRadians(angle))), cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int w = img.getWidth(null), h = img.getHeight(null);
        int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h
                * cos + w * sin);
        BufferedImage bimg = toBufferedImage(getEmptyImage(neww, newh));
        Graphics2D g = bimg.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(Math.toRadians(angle), w / 2, h / 2);
        g.drawRenderedImage(toBufferedImage(img), null);
        g.dispose();
        return toImage(bimg);
    }
    
   

}
