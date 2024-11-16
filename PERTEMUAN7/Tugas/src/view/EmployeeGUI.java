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
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        table = new JTable();
        updateTableData();
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        frame.add(tablePanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton btnAdd = new JButton("Tambah Karyawan");
        JButton btnDelete = new JButton("Hapus Karyawan");
        JButton btnRefresh = new JButton("Refresh");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnRefresh);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
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
        dialog.setSize(300, 250);
        dialog.setLayout(new GridLayout(4, 2));

        // Form fields
        JLabel lblName = new JLabel("Nama:");
        JTextField txtName = new JTextField();
        JLabel lblPosition = new JLabel("Posisi:");
        JTextField txtPosition = new JTextField();
        JLabel lblSalary = new JLabel("Gaji:");
        JTextField txtSalary = new JTextField();

        JButton btnSubmit = new JButton("Tambah");
        dialog.add(lblName);
        dialog.add(txtName);
        dialog.add(lblPosition);
        dialog.add(txtPosition);
        dialog.add(lblSalary);
        dialog.add(txtSalary);
        dialog.add(new JLabel()); // Placeholder
        dialog.add(btnSubmit);

        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText().trim();
                String position = txtPosition.getText().trim();
                BigDecimal salary;
                try {
                    salary = new BigDecimal(txtSalary.getText().trim());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Gaji harus berupa angka.");
                    return;
                }

                if (!name.isEmpty() && !position.isEmpty() && salary.compareTo(BigDecimal.ZERO) > 0) {
                    Employee newEmployee = new Employee(name, position, salary);
                    employeeDAO.addEmployee(newEmployee);
                    updateTableData();
                    dialog.dispose();
                    JOptionPane.showMessageDialog(frame, "Karyawan berhasil ditambahkan!");
                } else {
                    JOptionPane.showMessageDialog(dialog, "Isi semua field dengan benar.");
                }
            }
        });

        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void updateTableData() {
        List<Employee> employees = employeeDAO.getAllEmployees();
        String[] columnNames = {"ID", "Nama", "Posisi", "Gaji"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (Employee emp : employees) {
            Object[] row = {emp.getId(), emp.getName(), emp.getPosition(), emp.getSalary()};
            model.addRow(row);
        }
        table.setModel(model);
    }

    public static void main(String[] args) {
        new EmployeeGUI();
    }
}