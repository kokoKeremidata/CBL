package src;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

class ScaledImageLabel extends JLabel {
    private final Image image;

    public ScaledImageLabel(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(140,180));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC
            );
            g2.setRenderingHint(
                RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY
            );

            int originalWidth = image.getWidth(this);
            int originalHeight = image.getHeight(this);
            
            double scale = Math.min(
                (double) getWidth() / originalWidth,
                (double) getHeight() / originalHeight
            );
            int scaledWidth = (int) Math.round(originalWidth * scale);
            int scaledHeight = (int) Math.round(originalHeight * scale);
            
            int x = (getWidth() - scaledWidth) / 2;
            int y = (getHeight() - scaledHeight) / 2;
            
            g2.drawImage(
                image, x, y, scaledWidth, scaledHeight, this
            );
            g2.dispose();
        }
    }
}