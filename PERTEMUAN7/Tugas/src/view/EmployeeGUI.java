package PERTEMUAN7.Tugas.src.view;

import PERTEMUAN7.Tugas.src.dao.EmployeeDAO;
import PERTEMUAN7.Tugas.src.model.Employee;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

public class EmployeeGUI {
    private JFrame frame;
    private JTable table;
    private EmployeeDAO employeeDAO;

    public EmployeeGUI() {
        employeeDAO = new EmployeeDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Employee Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.setLayout(new BorderLayout());

        // Panel tabel
        JPanel tablePanel = new JPanel(new BorderLayout());
        table = new JTable();
        updateTableData();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.CENTER);

        // Panel tombol
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Tambah Karyawan");
        JButton btnDelete = new JButton("Hapus Karyawan");
        JButton btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Aksi tombol
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddEmployeeDialog();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int id = (int) table.getValueAt(selectedRow, 0);
                    employeeDAO.deleteEmployee(id);
                    updateTableData();
                    JOptionPane.showMessageDialog(frame, "Karyawan berhasil dihapus!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Pilih karyawan untuk dihapus.");
                }
            }
        });

        btnRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTableData();
            }
        });

        frame.setVisible(true);
    }

    private void showAddEmployeeDialog() {
        JDialog dialog = new JDialog(frame, "Tambah Karyawan Baru", true);
        dialog.setSize(400, 300);
        dialog.setLayout(new GridLayout(7, 2));

        // Form field
        JLabel lblName = new JLabel("Nama:");
        JTextField txtName = new JTextField();
        JLabel lblPosition = new JLabel("Posisi:");
        JTextField txtPosition = new JTextField();
        JLabel lblDepartment = new JLabel("Departemen:");
        JTextField txtDepartment = new JTextField();
        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        JLabel lblSalary = new JLabel("Gaji:");
        JTextField txtSalary = new JTextField();

        JButton btnSubmit = new JButton("Tambah");
        JButton btnCancel = new JButton("Batal");

        // Add components to dialog
        dialog.add(lblName);
        dialog.add(txtName);
        dialog.add(lblPosition);
        dialog.add(txtPosition);
        dialog.add(lblDepartment);
        dialog.add(txtDepartment);
        dialog.add(lblEmail);
        dialog.add(txtEmail);
        dialog.add(lblSalary);
        dialog.add(txtSalary);
        dialog.add(btnSubmit);
        dialog.add(btnCancel);

        // Aksi tombol submit
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String position = txtPosition.getText();
                String department = txtDepartment.getText();
                String email = txtEmail.getText();
                BigDecimal salary;
                try {
                    salary = new BigDecimal(txtSalary.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Gaji harus berupa angka.");
                    return;
                }

                if (name.isEmpty() || position.isEmpty() || department.isEmpty() || email.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Semua field harus diisi.");
                    return;
                }

                Employee newEmployee = new Employee(name, position, department, email, salary);
                employeeDAO.addEmployee(newEmployee);
                updateTableData();
                dialog.dispose();
                JOptionPane.showMessageDialog(frame, "Karyawan berhasil ditambahkan!");
            }
        });

        // Aksi tombol cancel
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        dialog.setVisible(true);
    }

    private void updateTableData() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        String[] columnNames = {"ID", "Nama", "Posisi", "Departemen", "Email", "Gaji"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Employee emp : employees) {
            Object[] row = {emp.getId(), emp.getName(), emp.getPosition(), emp.getDepartment(), emp.getEmail(), emp.getSalary()};
            model.addRow(row);
        }
        table.setModel(model);
    }

    public static void main(String[] args) {
        new EmployeeGUI();
    }
}
