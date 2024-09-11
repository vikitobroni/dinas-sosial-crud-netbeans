/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InklusiApp;

//import yang dibuthkan
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.TableModel;

/**
 *
 * @author Viki
 */
public class ViewForm extends javax.swing.JInternalFrame {

    //memanggil class database
    Connection conn = InklusiApp.ConnectionDb.getConnection();
    private String SQL;
    private String NIK;

    ResultSet rs;
    DefaultTableModel model;

    /**
     * Creates new form ViewForm
     */
    public ViewForm() {
        initComponents();
        tampilData();
        hitungJumlahBaris();

        // Setting JFrame size to match the screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // Menghilangkan border internal
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        this.setBorder(null);

    }

    //agar tabel tidak bisa diedit
    public class NonEditableTableModel extends DefaultTableModel {

        public NonEditableTableModel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Mengembalikan false untuk mencegah pengeditan sel
        }
    }

    public void eksporToExcel() {
        FileWriter fileWriter;
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new File("[B]export_output/excel[/B]"));
        int retrival = chooser.showSaveDialog(null);
        if (retrival == JFileChooser.APPROVE_OPTION) {
            try {
                TableModel tModel = tableData.getModel();
                fileWriter = new FileWriter(new File(chooser.getSelectedFile() + ".xls"));
                // write header
                for (int i = 0; i < tModel.getColumnCount(); i++) {
                    fileWriter.write(tModel.getColumnName(i).toUpperCase() + "\t");
                }
                fileWriter.write("\n");
                // write record
                for (int i = 0; i < tModel.getRowCount(); i++) {
                    for (int j = 0; j < tModel.getColumnCount(); j++) {
                        fileWriter.write(tModel.getValueAt(i, j).toString() + "\t");
                    }
                    fileWriter.write("\n");
                }
                fileWriter.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //menampilkan data tabel 
    public void tampilData() {
        String[] judul = {"ID", "NIK", "No Kartu Keluarga", "Nama Lengkap", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Jenis Disabilitas", "Desa", "Kecamatan"};;
        model = new NonEditableTableModel(judul, 0);
        tableData.setModel(model);
        SQL = "SELECT * FROM data";
        try {
            rs = conn.createStatement().executeQuery(SQL);
            int i = 0;

            while (rs.next()) {
                String idTxt = rs.getString("id");
                String nikTxt = rs.getString("nik");
                String noKkTxt = rs.getString("no_kk");
                String namaLengkapTxt = rs.getString("nama_lengkap");
                String tempatLahirTxt = rs.getString("tempat_lahir");
                String tanggalKalendar = rs.getString("tanggal_lahir");
                String jenisKelaminBox = rs.getString("jenis_kelamin");
                String jenisDisabilitasBox = rs.getString("jenis_disabilitas");
                String desaTxt = rs.getString("desa");
                String kecamatanTxt = rs.getString("kecamatan");

                String[] data = {idTxt, nikTxt, noKkTxt, namaLengkapTxt, tempatLahirTxt, tanggalKalendar, jenisKelaminBox, jenisDisabilitasBox, desaTxt, kecamatanTxt};
                model.addRow(data);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Fungsi untuk menghitung jumlah baris
    private int hitungJumlahBaris() {
        int rowCount = tableData.getRowCount();
        // Menampilkan jumlah baris pada label atau komponen lainnya
        labelJumlahBaris.setText("Jumlah Data : " + rowCount);
        return rowCount;
    }

//    fungsi pencarian
    private void cariData(String key) {
        try {
            String[] judul = {"ID", "NIK", "No Kartu Keluarga", "Nama Lengkap", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Jenis Disabilitas", "Desa", "Kecamatan"};
            model = new NonEditableTableModel(judul, 0);
            tableData.setModel(model);

            SQL = "SELECT * FROM data WHERE id LIKE '%" + key + "%' OR nik LIKE '%" + key + "%' OR no_kk LIKE '%" + key + "%' OR nama_lengkap LIKE '%" + key + "%' OR tempat_lahir LIKE '%" + key + "%' OR tanggal_lahir LIKE '%" + key + "%' OR jenis_kelamin LIKE '%" + key + "%' OR jenis_disabilitas LIKE '%" + key + "%' OR desa LIKE '%" + key + "%' OR kecamatan LIKE '%" + key + "%'";
            rs = conn.createStatement().executeQuery(SQL);

            while (rs.next()) {
                String idTxt = rs.getString("id");
                String nikTxt = rs.getString("nik");
                String noKkTxt = rs.getString("no_kk");
                String namaLengkapTxt = rs.getString("nama_lengkap");
                String tempatLahirTxt = rs.getString("tempat_lahir");
                String tanggalKalendar = rs.getString("tanggal_lahir");
                String jenisKelaminBox = rs.getString("jenis_kelamin");
                String jenisDisabilitasBox = rs.getString("jenis_disabilitas");
                String desaTxt = rs.getString("desa");
                String kecamatanTxt = rs.getString("kecamatan");

                String[] data = {idTxt, nikTxt, noKkTxt, namaLengkapTxt, tempatLahirTxt, tanggalKalendar, jenisKelaminBox, jenisDisabilitasBox, desaTxt, kecamatanTxt};
                model.addRow(data);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //menghapus isi tabel
    public void hapus() {
        int selectedRow = tableData.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris data yang akan dihapus", "Pesan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idTxtToDelete = tableData.getValueAt(selectedRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            // Query SQL untuk menghapus data berdasarkan ID
            String SQL = "DELETE FROM data WHERE id='" + idTxtToDelete + "'";

            try {
                conn.createStatement().executeUpdate(SQL);
                JOptionPane.showMessageDialog(null, "Data Berhasil dihapus", "Pesan", JOptionPane.INFORMATION_MESSAGE);

                // Remove the deleted row from the table model
                DefaultTableModel model = (DefaultTableModel) tableData.getModel();
                model.removeRow(selectedRow);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Data Gagal dihapus", "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    private InputForm inputForm;  // Add this as a class member

    //edit isi tabel
    private void edit() {
        int selectedRow = tableData.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Pilih baris data yang akan diedit", "Pesan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Get the data from the selected row
        String id = tableData.getValueAt(selectedRow, 0).toString();
        String nik = tableData.getValueAt(selectedRow, 1).toString();
        String noKk = tableData.getValueAt(selectedRow, 2).toString();
        String namaLengkap = tableData.getValueAt(selectedRow, 3).toString();
        String tempatLahir = tableData.getValueAt(selectedRow, 4).toString();
        String tanggalLahir = tableData.getValueAt(selectedRow, 5).toString();
        String jenisKelamin = tableData.getValueAt(selectedRow, 6).toString();
        String jenisDisabilitas = tableData.getValueAt(selectedRow, 7).toString();
        String desa = tableData.getValueAt(selectedRow, 8).toString();
        String kecamatan = tableData.getValueAt(selectedRow, 9).toString();

        // Check if the InputForm is already created
        if (inputForm == null || inputForm.isClosed()) {
            // If not, create a new instance
            inputForm = new InputForm();
        }

        // Set the fields of the InputForm with the selected data
        inputForm.setFields(id, nik, noKk, namaLengkap, tempatLahir, tanggalLahir, jenisKelamin,
                jenisDisabilitas, desa, kecamatan);

        // Show the InputForm
        inputForm.setVisible(true);
        this.getDesktopPane().add(inputForm);
        this.dispose();
    }

    // ... (rest of your code)
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        editBtn = new javax.swing.JButton();
        hapusBtn = new javax.swing.JButton();
        exportBtn = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        cariTxt = new javax.swing.JTextField();
        labelJumlahBaris = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        inputBtn = new javax.swing.JButton();
        viewBtn = new javax.swing.JButton();
        logOutBtn = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        exitBtn1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NIK", "No Kartu Keluarga", "Nama Lengkap", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Jenis Disabilitas", "Desa", "Kecamatan"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableData);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 990, 490));

        jLabel7.setBackground(new java.awt.Color(51, 102, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("DATA DISABILITAS");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, -1));

        editBtn.setBackground(new java.awt.Color(255, 255, 255));
        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(25, 118, 211));
        editBtn.setText("UBAH");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 600, 155, 40));

        hapusBtn.setBackground(new java.awt.Color(255, 255, 255));
        hapusBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hapusBtn.setForeground(new java.awt.Color(25, 118, 211));
        hapusBtn.setText("HAPUS");
        hapusBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        hapusBtn.setPreferredSize(new java.awt.Dimension(85, 29));
        hapusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusBtnActionPerformed(evt);
            }
        });
        jPanel1.add(hapusBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 600, 155, 40));

        exportBtn.setBackground(new java.awt.Color(255, 255, 255));
        exportBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exportBtn.setForeground(new java.awt.Color(25, 118, 211));
        exportBtn.setText("EXPORT");
        exportBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exportBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 600, 155, 40));

        jLabel2.setText("Cari :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, -1, 20));

        cariTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariTxtKeyReleased(evt);
            }
        });
        jPanel1.add(cariTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 50, 240, -1));

        labelJumlahBaris.setText("Jumlah Data =");
        jPanel1.add(labelJumlahBaris, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, -1, -1));

        jPanel2.setBackground(new java.awt.Color(25, 118, 211));

        inputBtn.setBackground(new java.awt.Color(255, 255, 255));
        inputBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inputBtn.setForeground(new java.awt.Color(25, 118, 211));
        inputBtn.setText("MASUKAN DATA");
        inputBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inputBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBtnActionPerformed(evt);
            }
        });

        viewBtn.setBackground(new java.awt.Color(255, 255, 255));
        viewBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        viewBtn.setForeground(new java.awt.Color(25, 118, 211));
        viewBtn.setText("TAMPILKAN DATA");
        viewBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtnActionPerformed(evt);
            }
        });

        logOutBtn.setBackground(new java.awt.Color(255, 255, 255));
        logOutBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logOutBtn.setForeground(new java.awt.Color(25, 118, 211));
        logOutBtn.setText("LOGOUT");
        logOutBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtnActionPerformed(evt);
            }
        });

        exitBtn.setBackground(new java.awt.Color(255, 255, 255));
        exitBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitBtn.setForeground(new java.awt.Color(25, 118, 211));
        exitBtn.setText("KELUAR");
        exitBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });

        exitBtn1.setBackground(new java.awt.Color(255, 255, 255));
        exitBtn1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitBtn1.setForeground(new java.awt.Color(25, 118, 211));
        exitBtn1.setText("BERANDA");
        exitBtn1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn1ActionPerformed(evt);
            }
        });

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/dinsos_wm.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBtn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(exitBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(313, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBtnActionPerformed
        InputForm inputForm = new InputForm();
        inputForm.setVisible(true);
        this.getDesktopPane().add(inputForm);
        this.dispose();
    }//GEN-LAST:event_inputBtnActionPerformed

    private void viewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtnActionPerformed
        ViewForm viewForm = new ViewForm();
        viewForm.setVisible(true);
        this.getDesktopPane().add(viewForm);
        this.dispose();
    }//GEN-LAST:event_viewBtnActionPerformed

    private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtnActionPerformed
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        this.getDesktopPane().add(loginForm);
        this.dispose();
    }//GEN-LAST:event_logOutBtnActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        int pilih = JOptionPane.showConfirmDialog(null, "Keluar Aplikasi?",
                "Konfirmasi", JOptionPane.OK_OPTION);
        if (pilih == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            JOptionPane.getRootFrame();
        }
    }//GEN-LAST:event_exitBtnActionPerformed

    private void exitBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn1ActionPerformed
        OptionMenu optionMenu = new OptionMenu();
        optionMenu.setVisible(true);
        this.getDesktopPane().add(optionMenu);
        this.dispose();
    }//GEN-LAST:event_exitBtn1ActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        edit();
    }//GEN-LAST:event_editBtnActionPerformed

    private void hapusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusBtnActionPerformed
        hapus();
    }//GEN-LAST:event_hapusBtnActionPerformed

    private void exportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportBtnActionPerformed
        eksporToExcel();
    }//GEN-LAST:event_exportBtnActionPerformed

    private void cariTxtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariTxtKeyReleased
        String key = cariTxt.getText();
        System.out.println(key);

        if (key != "") {
            cariData(key);
        } else {
            tampilData();
        }
    }//GEN-LAST:event_cariTxtKeyReleased

    private void tableDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataMouseClicked

    }//GEN-LAST:event_tableDataMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cariTxt;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JButton exitBtn1;
    private javax.swing.JButton exportBtn;
    private javax.swing.JButton hapusBtn;
    private javax.swing.JButton inputBtn;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelJumlahBaris;
    private javax.swing.JButton logOutBtn;
    private javax.swing.JTable tableData;
    private javax.swing.JButton viewBtn;
    // End of variables declaration//GEN-END:variables
public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewForm().setVisible(true);
        });
    }
}
