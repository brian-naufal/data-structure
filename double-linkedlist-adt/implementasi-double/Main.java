
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String homepage = JOptionPane.showInputDialog(
                    null,
                    "Masukkan homepage awal:",
                    "test");
            if (homepage == null || homepage.isBlank()) {
                homepage = "test";
            }

            BrowserHistory history = new BrowserHistory(homepage.trim());
            GUI gui = new GUI(history);
            gui.setVisible(true);
        });
    }
}
