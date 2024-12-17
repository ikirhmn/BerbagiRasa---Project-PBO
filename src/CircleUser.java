package src;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CircleUser {
    public static Image createCircularImage(Image source, int diameter) {
        // Mengubah sumber menjadi BufferedImage
        BufferedImage original = new BufferedImage(source.getWidth(null), source.getHeight(null),
                BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = original.createGraphics();
        g2.drawImage(source, 0, 0, null);
        g2.dispose();

        // Memastikan gambar adalah persegi (crop jika perlu)
        int size = Math.min(original.getWidth(), original.getHeight());
        BufferedImage squareImage = original.getSubimage(
                (original.getWidth() - size) / 2,
                (original.getHeight() - size) / 2,
                size,
                size);

        // Membuat gambar lingkaran
        BufferedImage circularImage = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = circularImage.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setClip(new java.awt.geom.Ellipse2D.Float(0, 0, diameter, diameter));
        g.drawImage(squareImage, 0, 0, diameter, diameter, null);
        g.dispose();

        return circularImage;
    }

}
