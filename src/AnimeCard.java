package src;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.io.File;

// AnimeCard is a panel that can be added to another Swing panel
public class AnimeCard extends JPanel {
    private Anime anime;
    private boolean selected = false;

    public AnimeCard(Anime anime) {
        this.anime = anime;
        // Set the layout of the card
        setLayout(new BorderLayout(0, 5));

        setPreferredSize(new Dimension(140, 200));
        setMinimumSize(new Dimension(140, 187));

        setBackground(Color.decode("#211B38"));

        setBorder(
            BorderFactory.createLineBorder(
                Color.decode("#C4B5FD")
            )
        );
        
        //image in the middle
        JLabel imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(140, 180));
        
        String path = anime.getPath();
        URL imagePath = null;
        ImageIcon original = null;
        if (path != null) {
            if (path.charAt(0) == '/') {
                //load image from images folder
                imagePath = getClass().getResource(path);
                if (imagePath != null) {
                    original = new ImageIcon(imagePath);
                }
            } else {
                //load from user selected path
                File file = new File(path);
                if (file.isFile()) {
                    original = new ImageIcon(path);
                }
            }
        }
        if (original != null && original.getIconWidth() > 0) {
            imageLabel = new ScaledImageLabel(original.getImage());
            //Image scaledImage = original.getImage()
            //        .getScaledInstance(140, 180, Image.SCALE_SMOOTH);
            //imageLabel.setIcon(new ImageIcon(scaledImage));
        } else {
            imageLabel.setText("No poster");
            imageLabel.setForeground(Color.WHITE);
        }
        //set the animeName at the center of the JLabel
        JLabel nameLabel = new JLabel(anime.getName(Lang.EN), SwingConstants.CENTER);
        System.out.println("Anime name: " + anime.getName(Lang.EN));
        nameLabel.setForeground(Color.decode("#C4B5FD"));
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 12));

        //display the animeName right below the card
        add(imageLabel, BorderLayout.CENTER);
        add(nameLabel, BorderLayout.SOUTH);
    }
    
    public void setSelected(boolean selected) {
        this.selected = selected;
        Color borderColor = selected ? Color.WHITE : Color.decode("#C4B5FD");
        setBorder(
            BorderFactory.createLineBorder(borderColor, selected ? 3 : 1)
        );
        repaint();
    }
    
    public boolean isSelected() {
        return selected;
    }
    
    public String getAnimeName() {
        return anime.getName(Lang.EN);
    }
    
    public Anime getAnime() {
        return anime;
    }
}

class ScaledImageLabel extends JLabel {
    private final Image image;
    int imageWidth = 140;
    int imageHeight = 180;

    public ScaledImageLabel(Image image) {
        this.image = image;
        setPreferredSize(new Dimension(imageWidth, imageHeight));
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

            int x = (getWidth() - imageWidth) / 2;
            int y = (getHeight() - imageHeight) / 2;

            g2.drawImage(
                image, x, y, imageWidth, imageHeight, this
            );
            g2.dispose();
        }
    }
}
