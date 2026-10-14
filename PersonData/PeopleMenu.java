package icip_java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class PeopleMenu extends JFrame {
    private JPanel mainPanel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JTextField kategoriField;
    private JComboBox<String> statusComboBox;
    private JTable tablePerson;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton cancelButton;

    private DefaultTableModel tableModel;
    private int selectedIndex = -1;

    public PeopleMenu() {
        setContentPane(mainPanel);
        setTitle("PeopleData - Modul 5 Database DPBO");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupTable();
        loadDatabaseData();

        // Mengisi pilihan ComboBox Status
        statusComboBox.addItem("Aktif");
        statusComboBox.addItem("Cuti");
        statusComboBox.addItem("Non-Aktif");

        // Tombol Add / Update (Fungsi dinamis: kalau belum pilih baris jadi Add, kalau baris diklik jadi Update)
        addButton.addActionListener(e -> {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String tahunLahir = tahunLahirField.getText().trim();
            String kategori = kategoriField.getText().trim();
            String status = (String) statusComboBox.getSelectedItem();

            // 1. Validasi jika masih ada kolom input yang kosong
            if (id.isEmpty() || nama.isEmpty() || tahunLahir.isEmpty() || kategori.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Semua kolom input harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (selectedIndex < 0) {
                // PROSES INSERT (ADD) KE DATABASE
                try {
                    Connection conn = Database.getConnection();

                    // Cek apakah ID sudah ada di database
                    PreparedStatement checkStmt = conn.prepareStatement("SELECT * FROM person WHERE id = ?");
                    checkStmt.setString(1, id);
                    ResultSet rs = checkStmt.executeQuery();

                    if (rs.next()) {
                        // 2. Validasi jika ID sudah sama/sudah ada
                        JOptionPane.showMessageDialog(this, "ID " + id + " sudah terdaftar di database!", "Error", JOptionPane.ERROR_MESSAGE);
                        conn.close();
                        return;
                    }

                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO person (id, nama, tahun_lahir, kategori, status) VALUES (?, ?, ?, ?, ?)");
                    stmt.setString(1, id);
                    stmt.setString(2, nama);
                    stmt.setString(3, tahunLahir);
                    stmt.setString(4, kategori);
                    stmt.setString(5, status);
                    stmt.executeUpdate();
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
                    loadDatabaseData();
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menambah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // PROSES UPDATE KE DATABASE
                try {
                    Connection conn = Database.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("UPDATE person SET nama = ?, tahun_lahir = ?, kategori = ?, status = ? WHERE id = ?");
                    stmt.setString(1, nama);
                    stmt.setString(2, tahunLahir);
                    stmt.setString(3, kategori);
                    stmt.setString(4, status);
                    stmt.setString(5, id);
                    stmt.executeUpdate();
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Data berhasil diubah!");
                    loadDatabaseData();
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tombol Delete dengan Konfirmasi
        deleteButton.addActionListener(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Pilih data pada tabel yang ingin dihapus terlebih dahulu!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Apakah anda yakin akan menghapus data person dengan ID " + id + "?",
                    "Konfirmasi Hapus",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    Connection conn = Database.getConnection();
                    PreparedStatement stmt = conn.prepareStatement("DELETE FROM person WHERE id = ?");
                    stmt.setString(1, id);
                    stmt.executeUpdate();
                    conn.close();

                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                    loadDatabaseData();
                    clearForm();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Tombol Cancel
        cancelButton.addActionListener(e -> clearForm());

        // Mouse Listener pada tabel sesuai petunjuk modul
        tablePerson.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = tablePerson.getSelectedRow();
                if (selectedIndex >= 0) {
                    String curId = tablePerson.getValueAt(selectedIndex, 0).toString();
                    String curNama = tablePerson.getValueAt(selectedIndex, 1).toString();
                    String curTahun = tablePerson.getValueAt(selectedIndex, 2).toString();
                    String curKategori = tablePerson.getValueAt(selectedIndex, 3).toString();
                    String curStatus = tablePerson.getValueAt(selectedIndex, 4).toString();

                    idField.setText(curId);
                    idField.setEditable(false); // ID tidak boleh diubah saat mode update
                    namaField.setText(curNama);
                    tahunLahirField.setText(curTahun);
                    kategoriField.setText(curKategori);
                    statusComboBox.setSelectedItem(curStatus);

                    addButton.setText("Update");
                    deleteButton.setVisible(true);
                }
            }
        });
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Tahun Lahir", "Kategori", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Agar tabel tidak bisa diketik langsung secara manual
            }
        };
        tablePerson.setModel(tableModel);
    }

    // Load data langsung dari Database MySQL (menggantikan ArrayList)
    private void loadDatabaseData() {
        tableModel.setRowCount(0);
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM person");

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getString("id"),
                        rs.getString("nama"),
                        rs.getString("tahun_lahir"),
                        rs.getString("kategori"),
                        rs.getString("status")
                });
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat database: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearForm() {
        idField.setText("");
        idField.setEditable(true); // ID bisa diisi lagi saat mau menambah data baru
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriField.setText("");
        statusComboBox.setSelectedIndex(0);
        selectedIndex = -1;
        tablePerson.clearSelection();
        addButton.setText("Add");
        deleteButton.setVisible(false); // Sembunyikan tombol delete jika tidak ada yang dipilih
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PeopleMenu().setVisible(true));
    }
}