import javax.swing.*;
public class Main extends JFrame {
    private JPanel panel;
    private JTextField searchBar;
    private JList list1;
    private JButton button1;
    private JComboBox statusCB;
    private JButton button2;
    private JButton button3;
    private JButton searchButton;
    private JScrollPane scrollPane;
    private JPanel displayPanel;

    public Main() {
        setContentPane(panel);
        setTitle("Watchlist");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}