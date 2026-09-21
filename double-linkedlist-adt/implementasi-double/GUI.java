import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI extends JFrame {

    private final BrowserHistory history;

    private JTextField addressBar;
    private JButton backButton;
    private JButton forwardButton;
    private JButton goButton;
    private DefaultListModel<String> historyListModel;
    private JList<String> historyListView;
    private JLabel statusLabel;

    public GUI(BrowserHistory history) {
        super("Browser History Sederhana (Doubly Linked List)");
        this.history = history;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(8, 8));

        add(buildTopPanel(), BorderLayout.NORTH);
        add(buildCenterPanel(), BorderLayout.CENTER);
        add(buildStatusBar(), BorderLayout.SOUTH);

        refreshView();
    }

    private JPanel buildTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JPanel navButtons = new JPanel(new FlowLayout(FlowLayout.LEFT, 4, 0));
        backButton = new JButton("<");
        forwardButton = new JButton(">");
        backButton.setToolTipText("Kembali ke halaman sebelumnya");
        forwardButton.setToolTipText("Maju ke halaman berikutnya");
        navButtons.add(backButton);
        navButtons.add(forwardButton);

        addressBar = new JTextField();
        addressBar.setFont(new Font("SansSerif", Font.PLAIN, 14));

        goButton = new JButton("Go");

        JPanel addressPanel = new JPanel(new BorderLayout(6, 0));
        addressPanel.add(addressBar, BorderLayout.CENTER);
        addressPanel.add(goButton, BorderLayout.EAST);

        panel.add(navButtons, BorderLayout.WEST);
        panel.add(addressPanel, BorderLayout.CENTER);

        backButton.addActionListener(e -> doBack());
        forwardButton.addActionListener(e -> doForward());
        goButton.addActionListener(e -> doVisitFromAddressBar());
        addressBar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    doVisitFromAddressBar();
                }
            }
        });

        return panel;
    }

    private JPanel buildCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(6, 6));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));

        JLabel title = new JLabel("History:");
        title.setFont(new Font("SansSerif", Font.BOLD, 13));

        historyListModel = new DefaultListModel<>();
        historyListView = new JList<>(historyListModel);
        historyListView.setFont(new Font("Monospaced", Font.PLAIN, 13));
        historyListView.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(historyListView);

        panel.add(title, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel buildStatusBar() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(6, 10, 10, 10));
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
        panel.add(statusLabel, BorderLayout.WEST);
        return panel;
    }

    private void doVisitFromAddressBar() {
        String url = addressBar.getText().trim();
        if (url.isEmpty()) {
            JOptionPane.showMessageDialog(this, "URL tidak boleh kosong.",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
        history.visit(url);
        addressBar.setText("");
        refreshView();
    }

    private void doBack() {
        if (!history.canGoBack()) {
            statusLabel.setText("Sudah di halaman paling awal, tidak bisa back.");
            return;
        }
        history.back();
        refreshView();
    }

    private void doForward() {
        if (!history.canGoForward()) {
            statusLabel.setText("Sudah di halaman paling akhir, tidak bisa forward.");
            return;
        }
        history.forward();
        refreshView();
    }

    private void refreshView() {
        historyListModel.clear();
        Node temp = history.getHead();
        int index = 1;
        int currentIndex = -1;

        while (temp != null) {
            boolean isCurrent = (temp == history.getCurrentNode());
            String marker = isCurrent ? "  <- Current" : "";
            historyListModel.addElement(index + ". " + temp.getUrl() + marker);
            if (isCurrent) {
                currentIndex = index - 1;
            }
            temp = temp.getNext();
            index++;
        }

        if (currentIndex >= 0) {
            historyListView.setSelectedIndex(currentIndex);
            historyListView.ensureIndexIsVisible(currentIndex);
        }

        backButton.setEnabled(history.canGoBack());
        forwardButton.setEnabled(history.canGoForward());

        statusLabel.setText("Halaman sekarang: " + history.getCurrentUrl()
                + "   |   Total riwayat: " + history.size() + " halaman");

        setTitle("Browser History - " + history.getCurrentUrl());
    }
}
