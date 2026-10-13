package src;

import javax.swing.*;
import java.awt.*;

// AnimeCard is a panel that can be added to another Swing panel
public class AnimeCard extends JPanel {

    public AnimeCard(String animeName) {
        // Set the layout of the card
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(140, 200));
        setMinimumSize(new Dimension(140, 187));

        setBackground(Color.decode("#211B38"));

        setBorder(
            BorderFactory.createLineBorder(
                Color.decode("#C4B5FD")
            )
        );


        //set the animeName at the center of the JLabel
        JLabel nameLabel = new JLabel(
            animeName,
            SwingConstants.CENTER
        );

        System.out.println("Anime name: " + animeName);
        nameLabel.setForeground(Color.decode("#C4B5FD"));

        nameLabel.setFont(
            new Font("SansSerif", Font.BOLD, 12)
        );

        //display the animeName right below the card
        add(nameLabel, BorderLayout.CENTER);
    }
}