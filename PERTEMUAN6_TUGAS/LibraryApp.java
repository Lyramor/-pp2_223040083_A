package PERTEMUAN6_TUGAS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryApp extends JFrame {

    private JTextField bookTitleField;
    private JTextArea descriptionArea;
    private JRadioButton availableButton, checkedOutButton;
    private JCheckBox fictionCheckBox, nonFictionCheckBox, referenceCheckBox;
    private JComboBox<String> genreComboBox;
    private JList<String> conditionList;
    private JSlider pagesSlider;
    private JSpinner quantitySpinner;
    private JTable libraryTable;
    private DefaultTableModel tableModel;

    public LibraryApp() {
        setTitle("Library Management System");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem newEntryItem = new JMenuItem("New Entry");
        JMenuItem resetItem = new JMenuItem("Reset");
        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(newEntryItem);
        menu.add(resetItem);
        menu.add(exitItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Entry Panel (Form Input)
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new BoxLayout(entryPanel, BoxLayout.Y_AXIS));

        // Form Components
        bookTitleField = new JTextField(20);
        descriptionArea = new JTextArea(3, 20);
        availableButton = new JRadioButton("Available");
        checkedOutButton = new JRadioButton("Checked Out");
        ButtonGroup conditionGroup = new ButtonGroup();
        conditionGroup.add(availableButton);
        conditionGroup.add(checkedOutButton);

        fictionCheckBox = new JCheckBox("Fiction");
        nonFictionCheckBox = new JCheckBox("Non-Fiction");
        referenceCheckBox = new JCheckBox("Reference");

        String[] genres = {"Mystery", "Fantasy", "Science Fiction", "Biography", "History"};
        genreComboBox = new JComboBox<>(genres);

        String[] conditions = {"New", "Good", "Worn"};
        conditionList = new JList<>(conditions);

        pagesSlider = new JSlider(100, 1000, 300);
        pagesSlider.setMajorTickSpacing(100);
        pagesSlider.setPaintTicks(true);
        pagesSlider.setPaintLabels(true);
 
        quantitySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));

        // Table to display library entries
        String[] columns = {"Book Title", "Genre", "Availability", "Categories", "Pages", "Quantity"};
        tableModel = new DefaultTableModel(columns, 0);
        libraryTable = new JTable(tableModel);

        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEntry();
            }
        });

        // Adding components to the entry panel
        entryPanel.add(new JLabel("Book Title:"));
        entryPanel.add(bookTitleField);
        entryPanel.add(new JLabel("Description:"));
        entryPanel.add(new JScrollPane(descriptionArea));
        entryPanel.add(new JLabel("Availability:"));
        entryPanel.add(availableButton);
        entryPanel.add(checkedOutButton);
        entryPanel.add(new JLabel("Categories:"));
        entryPanel.add(fictionCheckBox);
        entryPanel.add(nonFictionCheckBox);
        entryPanel.add(referenceCheckBox);
        entryPanel.add(new JLabel("Genre:"));
        entryPanel.add(genreComboBox);
        entryPanel.add(new JLabel("Condition:"));
        entryPanel.add(new JScrollPane(conditionList));
        entryPanel.add(new JLabel("Pages:"));
        entryPanel.add(pagesSlider);
        entryPanel.add(new JLabel("Quantity:"));
        entryPanel.add(quantitySpinner);
        entryPanel.add(addButton);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(new JScrollPane(libraryTable), BorderLayout.CENTER);

        mainPanel.add(entryPanel, BorderLayout.WEST);
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        add(mainPanel);

        // Event Handlers
        newEntryItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetEntry();
                JOptionPane.showMessageDialog(null, "New Entry Form Ready");
            }
        });
        
        resetItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetForm();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void addEntry() {
        String bookTitle = bookTitleField.getText();
        String genre = genreComboBox.getSelectedItem().toString();
        String availability = availableButton.isSelected() ? "Available" : "Checked Out";

        StringBuilder categories = new StringBuilder();
        if (fictionCheckBox.isSelected()) categories.append("Fiction ");
        if (nonFictionCheckBox.isSelected()) categories.append("Non-Fiction ");
        if (referenceCheckBox.isSelected()) categories.append("Reference ");

        String pages = String.valueOf(pagesSlider.getValue());
        String quantity = quantitySpinner.getValue().toString();

        tableModel.addRow(new Object[]{bookTitle, genre, availability, categories.toString(), pages, quantity});
    }
    
    private void resetEntry() {
        bookTitleField.setText("");
        descriptionArea.setText("");
        availableButton.setSelected(false);
        checkedOutButton.setSelected(false);
        fictionCheckBox.setSelected(false);
        nonFictionCheckBox.setSelected(false);
        referenceCheckBox.setSelected(false);
        genreComboBox.setSelectedIndex(0);
        conditionList.clearSelection();
        pagesSlider.setValue(300);
        quantitySpinner.setValue(1); 
    }
    
    private void resetForm() {
        bookTitleField.setText("");
        descriptionArea.setText("");
        availableButton.setSelected(false);
        checkedOutButton.setSelected(false);
        fictionCheckBox.setSelected(false);
        nonFictionCheckBox.setSelected(false);
        referenceCheckBox.setSelected(false);
        genreComboBox.setSelectedIndex(0);
        conditionList.clearSelection();
        pagesSlider.setValue(300);
        quantitySpinner.setValue(1); 

        // Clear the table model
        tableModel.setRowCount(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LibraryApp app = new LibraryApp();
            app.setVisible(true);
        });
    }
}

