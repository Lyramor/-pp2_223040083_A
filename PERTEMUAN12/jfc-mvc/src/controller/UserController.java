/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

/**
 *
 * @author m marsa n j
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import model.*;
import org.apache.ibatis.session.SqlSession;
import view.UserPdf;
import view.UserView;

public class UserController {
    private UserView view;
    private UserMapper mapper;
    private UserPdf pdf;

    public UserController(UserView view, UserMapper mapper, UserPdf pdf) {
        this.view = view;
        this.mapper = mapper;
        this.pdf = pdf;

        this.view.addAddUserListener(new AddUserListener());
        this.view.addRefreshListener(new RefreshListener());
        this.view.addExportListener(new ExportListener());
    }

    class AddUserListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = view.getNameInput();
        String email = view.getEmailInput();
        if (!name.isEmpty() && !email.isEmpty()) {
            SqlSession session = MyBatisUtil.getSqlSession(); // Dapatkan session
            try {
                UserMapper mapper = session.getMapper(UserMapper.class);
                User user = new User();
                user.setName(name);
                user.setEmail(email);

                mapper.insertUser(user); // Insert data
                session.commit(); // Commit transaksi
                JOptionPane.showMessageDialog(view, "User added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, "Failed to add user: " + ex.getMessage());
            } finally {
                session.close(); // Tutup session
            }
        } else {
            JOptionPane.showMessageDialog(view, "Please fill in all fields.");
        }
    }
}


    class RefreshListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            String[] userArray = users.stream()
                .map(u -> u.getName() + " (" + u.getEmail() + ")")
                .toArray(String[]::new);

            view.setUserList(userArray);
        }
    }

    class ExportListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<User> users = mapper.getAllUsers();
            pdf.exportPdf(users);
            JOptionPane.showMessageDialog(view, "User data exported to PDF.");
        }
    }
}