import javax.swing.*;
public class Main extends JFrame {
    private JScrollPane scroll;
    private JRadioButton aaaRadioButton;
    private JPanel panel;

    public Main() {
        setContentPane(panel);
        setTitle("Watchlist");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main() {
        new Main();
    }
}