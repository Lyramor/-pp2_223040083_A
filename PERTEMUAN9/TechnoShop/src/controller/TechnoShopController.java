/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author m marsa n j
 */

import model.*;
import view.TechnoShopView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TechnoShopController {
    private TechnoShopView view;
    private ItemMapper mapper;

    public TechnoShopController(TechnoShopView view, ItemMapper mapper) {
        this.view = view;
        this.mapper = mapper;

        this.view.addAddItemListener(new AddItemListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addDeleteItemListener(new DeleteItemListener());
        this.view.addUpdateItemListener(new UpdateItemListener());
    }

    class AddItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getItemNameInput();
            String category = view.getCategoryInput();
            String priceInput = view.getPriceInput();
            String stockInput = view.getStockInput();

            if (!name.isEmpty() && !category.isEmpty() && !priceInput.isEmpty() && !stockInput.isEmpty()) {
                try {
                    double price = Double.parseDouble(priceInput);
                    int stock = Integer.parseInt(stockInput);

                    Item item = new Item();
                    item.setName(name);
                    item.setCategory(category);
                    item.setPrice(price);
                    item.setStock(stock);
                    mapper.insertItem(item);
                    JOptionPane.showMessageDialog(view, "Item berhasil ditambahkan!");
                    refreshItemList();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(view, "Harga dan stok harus berupa angka valid.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Harap isi semua bidang input.");
            }
        }
    }

    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            refreshItemList();
        }
    }

    class DeleteItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedItemId();
            if (selectedId != -1) {
                mapper.deleteItem(selectedId);
                JOptionPane.showMessageDialog(view, "Item berhasil dihapus!");
                refreshItemList();
            } else {
                JOptionPane.showMessageDialog(view, "Pilih item yang akan dihapus.");
            }
        }
    }

    class UpdateItemListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedId = view.getSelectedItemId();
            if (selectedId != -1) {
                String name = view.getItemNameInput();
                String category = view.getCategoryInput();
                String priceInput = view.getPriceInput();
                String stockInput = view.getStockInput();

                if (!name.isEmpty() && !category.isEmpty() && !priceInput.isEmpty() && !stockInput.isEmpty()) {
                    try {
                        double price = Double.parseDouble(priceInput);
                        int stock = Integer.parseInt(stockInput);

                        Item item = new Item();
                        item.setId(selectedId);
                        item.setName(name);
                        item.setCategory(category);
                        item.setPrice(price);
                        item.setStock(stock);
                        mapper.updateItem(item);
                        JOptionPane.showMessageDialog(view, "Item berhasil diperbarui!");
                        refreshItemList();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(view, "Harga dan stok harus berupa angka valid.");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Harap isi semua bidang input.");
                }
            } else {
                JOptionPane.showMessageDialog(view, "Pilih item yang akan diperbarui.");
            }
        }
    }

    private void refreshItemList() {
        List<Item> itemList = mapper.getAllItems();
        view.setItemList(itemList);
    }
}