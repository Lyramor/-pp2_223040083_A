/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author m marsa n j
 */
import javax.swing.*;
import java.awt.*;

public class ProductsView extends JFrame {
    private JTable table;
    private JTextField tfName, tfBrand, tfPrice, tfStock;
    private JButton btnAdd, btnUpdate, btnDelete, btnRefresh;

    public ProductsView() {
        setTitle("Products Data Management");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table
        table = new JTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Name:"));
        tfName = new JTextField();
        inputPanel.add(tfName);
        inputPanel.add(new JLabel("Brand:"));
        tfBrand = new JTextField();
        inputPanel.add(tfBrand);
        inputPanel.add(new JLabel("Price:"));
        tfPrice = new JTextField();
        inputPanel.add(tfPrice);
        inputPanel.add(new JLabel("Stock:"));
        tfStock = new JTextField();
        inputPanel.add(tfStock);
        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        btnAdd = new JButton("Add");
        btnUpdate = new JButton("Update");
        btnDelete = new JButton("Delete");
        btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public JTable getTable() { return table; }
    public JTextField getTfName() { return tfName; }
    public JTextField getTfBrand() { return tfBrand; }
    public JTextField getTfPrice() { return tfPrice; }
    public JTextField getTfStock() { return tfStock; }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnUpdate() { return btnUpdate; }
    public JButton getBtnDelete() { return btnDelete; }
    public JButton getBtnRefresh() { return btnRefresh; }
}