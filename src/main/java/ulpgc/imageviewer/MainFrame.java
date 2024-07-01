package ulpgc.imageviewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class MainFrame extends JFrame {
    private ImageDisplay imageDisplay;
    private final Map<String, Command> commands;

    public MainFrame() {
        this.commands = new HashMap<>();
        setTitle("Image Viewer");
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        add(createImageDisplay(), BorderLayout.CENTER);
        add(createToolbar(), BorderLayout.SOUTH);
        addKeyBindings();
    }

    private Component createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createButton("<"));
        panel.add(createButton(">"));
        return panel;
    }

    private Component createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(e -> commands.get(label).execute());
        return button;
    }

    private Component createImageDisplay() {
        SwingImageDisplay display = new SwingImageDisplay();
        this.imageDisplay = display;
        return display;
    }

    private void addKeyBindings() {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "prevImage");
        getRootPane().getActionMap().put("prevImage", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get("<").execute();
            }
        });

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "nextImage");
        getRootPane().getActionMap().put("nextImage", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(">").execute();
            }
        });
    }

    public void add(String name, Command command) {
        commands.put(name, command);
    }

    public ImageDisplay imageDisplay() {
        return imageDisplay;
    }
}
