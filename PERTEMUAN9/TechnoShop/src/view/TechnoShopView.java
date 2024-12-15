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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import model.Item;


public class TechnoShopView extends JFrame {
    // Komponen GUI
    private JTextField txtName = new JTextField(20);
    private JTextField txtCategory = new JTextField(20);
    private JTextField txtPrice = new JTextField(20);
    private JTextField txtStock = new JTextField(20);
    private JButton btnAdd = new JButton("Tambah Item");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnDelete = new JButton("Hapus Item");
    private JButton btnUpdate = new JButton("Update Item");
    private JTable itemTable;
    private DefaultTableModel tableModel;

    public TechnoShopView() {
        setTitle("Manajemen Data Item");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Model untuk tabel
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nama");
        tableModel.addColumn("Kategori");
        tableModel.addColumn("Harga");
        tableModel.addColumn("Stok");

        itemTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemTable);

        // Panel untuk form input
        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nama Item:"));
        panel.add(txtName);
        panel.add(new JLabel("Kategori:"));
        panel.add(txtCategory);
        panel.add(new JLabel("Harga:"));
        panel.add(txtPrice);
        panel.add(new JLabel("Stok:"));
        panel.add(txtStock);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnUpdate);

        add(panel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);
    }

    public String getItemNameInput() {
        return txtName.getText();
    }

    public String getCategoryInput() {
        return txtCategory.getText();
    }

    public String getPriceInput() {
        return txtPrice.getText();
    }

    public String getStockInput() {
        return txtStock.getText();
    }

    public int getSelectedItemId() {
        int selectedRow = itemTable.getSelectedRow();
        if (selectedRow != -1) {
            return (int) tableModel.getValueAt(selectedRow, 0);
        }
        return -1;
    }

    public void setItemList(List<Item> items) {
        tableModel.setRowCount(0);  // Clear existing rows
        for (Item item : items) {
            tableModel.addRow(new Object[] { item.getId(), item.getName(), item.getCategory(), item.getPrice(), item.getStock() });
        }
    }

    public void addAddItemListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addDeleteItemListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }

    public void addUpdateItemListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }
}
