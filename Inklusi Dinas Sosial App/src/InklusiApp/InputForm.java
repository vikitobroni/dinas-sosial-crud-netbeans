/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InklusiApp;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;

/**
 *
 * @author Viki
 */
public class InputForm extends javax.swing.JInternalFrame {

    Connection conn = InklusiApp.ConnectionDb.getConnection();
    private String SQL;

    ResultSet rs;

    /**
     * Creates new form InputForm
     */
    public InputForm() {
        initComponents();
        initializeUI();
        addComboBoxItems();
        idAuto();
    }

    private void addComboBoxItems() {
        tambahJenisKelamin();
        tambahJenisDisabilitas();
        tambahKecamatan();
        tambahDesa();
    }

    private void initializeUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        this.setBorder(null);
    }

    public void tambahJenisKelamin() {
        jenisKelaminBox.addItem("Pilih...");
        jenisKelaminBox.addItem("Laki-Laki");
        jenisKelaminBox.addItem("Perempuan");
    }

    public void tambahJenisDisabilitas() {
        jenisDisabilitasBox.addItem("Pilih...");
        jenisDisabilitasBox.addItem("Tunawicara");
        jenisDisabilitasBox.addItem("Tunarungu");
        jenisDisabilitasBox.addItem("Tunanetra");
        jenisDisabilitasBox.addItem("Tunadaksa");
    }

    public void tambahKecamatan() {
        kecamatanBox.addItem("Pilih...");
        kecamatanBox.addItem("Adiwerna");
        kecamatanBox.addItem("Balapulang");
        kecamatanBox.addItem("Bojong");
        kecamatanBox.addItem("Bumijawa");
        kecamatanBox.addItem("Dukuhturi");
        kecamatanBox.addItem("Dukuhwaru");
        kecamatanBox.addItem("Jatinegara");
        kecamatanBox.addItem("Kedungbanteng");
        kecamatanBox.addItem("Kramat");
        kecamatanBox.addItem("Lebaksiu");
        kecamatanBox.addItem("Margasari");
        kecamatanBox.addItem("Pagerbarang");
        kecamatanBox.addItem("Pangkah");
        kecamatanBox.addItem("Slawi");
        kecamatanBox.addItem("Suradadi");
        kecamatanBox.addItem("Talang");
        kecamatanBox.addItem("Tarub");
        kecamatanBox.addItem("Warureja");

        // Add an ActionListener to kecamatanBox
        kecamatanBox.addActionListener((e) -> {
            updateDesaComboBox();
        });
    }

    public void tambahDesa() {
        desaBox.addItem("Pilih...");
        // Add an empty array for "Pilih..." option
        addDesaItems();
    }

    private void updateDesaComboBox() {
        // Get the selected kecamatan
        String selectedKecamatan = (String) kecamatanBox.getSelectedItem();

        // Clear the existing items in desaBox
        desaBox.removeAllItems();

        // Populate desaBox based on the selected kecamatan
        // Populate desaBox based on the selected kecamatan
        if ("Adiwerna".equals(selectedKecamatan)) {
            // Add the desa names for Adiwerna
            addDesaItems("Adiwerna", "Bersole", "Gumalar", "Harjosari Kidul", "Harjosari Lor",
                    "Kalimati", "Kaliwadas", "Kedungsukun", "Lemahduwur", "Lumingser", "Pagedangan",
                    "Pagiyanten", "Pecangakan", "Pedeslohor", "Penarukan", "Pesarean",
                    "Tembok Banjaran", "Tembok Kidul", "Tembok Lor", "Tembok Luwung", "Ujungrusi");
        } else if ("Balapulang".equals(selectedKecamatan)) {
            addDesaItems("Balapulang Kulon", "Balapulang Wetan", "Banjar Anyar", "Batuagung", "Bukateja", "Cenggini",
                    "Cibunar", "Cilongok", "Danaraja", "Danawarih", "Harjawinangun", "Kalibakung", "Kaliwungu",
                    "Karangjambu", "Pagerwangi", "Pamiritan", "Sangkanjaya", "Sesepan", "Tembongwah", "Weringin Jenggot");
        } else if ("Bojong".equals(selectedKecamatan)) {
            addDesaItems("Batunyana", "Bojong", "Buniwah", "Cikura", "Danasari", "Dukuhtengah", "Gunungjati",
                    "Kajenengan", "Kalijambu", "Karangmulyo", "Kedawung", "Lengkong", "Pucang Luwuk",
                    "Rembul", "Sangkanayu", "Suniarsih", "Tuwel");
        } else if ("Bumijawa".equals(selectedKecamatan)) {
            // Add the desa names for Bumi Jawa
            addDesaItems("Batumirah", "Begawat", "Bumijawa", "Carul", "Cawitali", "Cempaka",
                    "Cintamanik", "Dukuh Benda", "Guci", "Gunung Agung", "Jejeg", "Muncanglarang",
                    "Pagerkasih", "Sigedong", "Sokasari", "Sokatengah", "Sumbaga", "Traju");
        } else if ("Dukuhturi".equals(selectedKecamatan)) {
            // Add the desa names for Dukuhturi
            addDesaItems("Bandasari", "Debong Wetan", "Dukuhturi", "Grogol", "Kademangaran",
                    "Karanganyar", "Kepandean", "Ketanggungan", "Kupu", "Lawatan", "Pagongan",
                    "Pekauman Kulon", "Pengabean", "Pengarasan", "Pepedan", "Sidakaton",
                    "Sidapurna", "Sutapranan");
        } else if ("Dukuhwaru".equals(selectedKecamatan)) {
            // Add the desa names for Dukuhwaru
            addDesaItems("Blubuk", "Bulakpacing", "Dukuhwaru", "Gumayun", "Kabunan", "Kalisoka",
                    "Pedagangan", "Selapura", "Sindang", "Slarang Lor");
        } else if ("Jatinegara".equals(selectedKecamatan)) {
            // Add the desa names for Jatinegara
            addDesaItems("Argatawang", "Capar", "Cerih", "Dukuhbangsa", "Gantungan", "Jatinegara",
                    "Kedungwungu", "Lebakwangi", "Lembahsari", "Luwijawa", "Mokaha", "Padasari",
                    "Penyalahan", "Setail", "Sumbarang", "Tamansari", "Wotgalih");
        } else if ("Kedungbanteng".equals(selectedKecamatan)) {
            addDesaItems("Dukuhjati Wetan", "Karanganyar", "Karangmalang", "Kebandingan", "Kedungbanteng",
                    "Margamulya", "Penujah", "Semedo", "Sumingkir", "Tonggara");
        } else if ("Kramat".equals(selectedKecamatan)) {
            addDesaItems("Babakan", "Bangungalih", "Bongkok", "Dinuk", "Jatilawang", "Kemantran", "Kemuning",
                    "Kepunduhan", "Kertaharja", "Kertayasa", "Ketileng", "Kramat", "Maribaya", "Mejasem Barat",
                    "Mejasem Timur", "Munjungagung", "Padaharja", "Plumbungan", "Tanjungharja");
        } else if ("Lebaksiu".equals(selectedKecamatan)) {
            addDesaItems("Balaradin", "Dukuhdamu", "Dukuhlo", "Jatimulyo", "Kajen", "Kambangan", "Kesuben",
                    "Lebak Goah", "Lebaksiu Kidul", "Lebaksiu Lor", "Pendawa", "Slarang Kidul", "Tegalandong",
                    "Timbangreja", "Yamansari");
        } else if ("Margasari".equals(selectedKecamatan)) {
            addDesaItems("Danaraja", "Dukuh Tengah", "Jatilaba", "Jembayat", "Kaligayam", "Kalisalak",
                    "Karangdawa", "Marga Ayu", "Margasari", "Pakulaut", "Prupuk Selatan", "Prupuk Utara",
                    "Wanasari");
        } else if ("Pagerbarang".equals(selectedKecamatan)) {
            addDesaItems("Jatiwangi", "Karanganyar", "Kedungsugih", "Kertaharja", "Mulyoharjo", "Pagerbarang",
                    "Pesarean", "Rajegwesi", "Randusari", "Semboja", "Sido Mulyo", "Srengseng", "Surokidul");
        } else if ("Pangkah".equals(selectedKecamatan)) {
            addDesaItems("Balamoa", "Bedug", "Bogares Kidul", "Bogares Lor", "Curug", "Depok", "Dermasandi",
                    "Dermasuci", "Dukuhjati Kidul", "Dukuhsembung", "Grobog Kulon", "Grobog Wetan",
                    "Jenggawur", "Kalikangkung", "Kendalserut", "Paketiban", "Pangkah", "Pecabean",
                    "Pener", "Penusupan", "Purbayasa", "Rancawiru", "Talok");
        } else if ("Slawi".equals(selectedKecamatan)) {
            addDesaItems("Dukuhsalam", "Dukuhwringin", "Kalisapu", "Slawi Kulon", "Trayeman");
        } else if ("Suradadi".equals(selectedKecamatan)) {
            addDesaItems("Bojongsana", "Gembongdadi", "Harjasari", "Jatibogor", "Jatimulya", "Karangmulya",
                    "Karangwuluh", "Kertasari", "Purwahamba", "Sidaharja", "Suradadi");
        } else if ("Talang".equals(selectedKecamatan)) {
            addDesaItems("Bengle", "Cangkring", "Dawuhan", "Dukuhmalang", "Gembong Kulon", "Getaskerep",
                    "Kajen", "Kaladawa", "Kaligayam", "Kebasen", "Langgen", "Pacul", "Pasangan", "Pegirikan",
                    "Pekiringan", "Pesayangan", "Talang", "Tegalwangi", "Wangandawa");
        } else if ("Tarub".equals(selectedKecamatan)) {
            addDesaItems("Brekat", "Bulakwaru", "Bumiharja", "Jatirawa", "Kabukan", "Kalijambe", "Karangjati",
                    "Karangmangu", "Kedokansayang", "Kedungbungkus", "Kemanggungan", "Kesadikan", "Kesamiran",
                    "Lebeteng", "Mangunsaren", "Margapadang", "Mindaka", "Purbasana", "Setu", "Tarub");
        } else if ("Warureja".equals(selectedKecamatan)) {
            addDesaItems("Banjaragung", "Banjarturi", "Demangharja", "Kedungjati", "Kedungkelor", "Kendayakan",
                    "Kreman", "Rangimulya", "Sidamulya", "Sigentong", "Sukareja", "Warureja");
        }
// Add conditions for other kecamatan

    }

    private void addDesaItems(String... desaArray) {
        // Add items to desaBox
        desaBox.addItem("Pilih...");
        for (String desa : desaArray) {
            desaBox.addItem(desa);
        }

        // Select the "Pilih..." item
        desaBox.setSelectedItem("Pilih...");
    }

    // Fungsi tombol bersih
    public void bersih() {
        idAuto(); // Generate a new ID
        nikTxt.setText("");
        noKkTxt.setText("");
        namaLengkapTxt.setText("");
        tempatLahirTxt.setText("");

        if (tanggalLahir != null) {
            tanggalLahir.setCalendar(null);  // Use setDate to clear the selected date
        }

        // Set the selected index to 0 for combo boxes
        jenisKelaminBox.setSelectedIndex(0);
        jenisDisabilitasBox.setSelectedIndex(0);
        kecamatanBox.setSelectedIndex(0);
        desaBox.removeAllItems();
        desaBox.addItem("Pilih...");

        // Add an ActionListener to kecamatanBox
        kecamatanBox.addActionListener((e) -> {
            updateDesaComboBox();
        });
    }

    public boolean tambah() {
        if (namaLengkapTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama Lengkap belum diisi");
            return false;
        } else if (nikTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "NIK belum diisi");
            return false;
        } else if (noKkTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No. KK belum diisi");
            return false;
        } else if (tempatLahirTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tempat Lahir belum diisi");
            return false;
        } else if (tanggalLahir.getCalendar() == null) {
            JOptionPane.showMessageDialog(null, "Tanggal Lahir belum diisi");
            return false;
        } else if ("Pilih...".equals(jenisKelaminBox.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Jenis Kelamin belum diisi");
            return false;
        } else if ("Pilih...".equals(jenisDisabilitasBox.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Jenis Disabilitas belum diisi");
            return false;
        } else if ("Pilih...".equals(kecamatanBox.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Kecamatan belum diisi");
            return false;
        } else if ("Pilih...".equals(desaBox.getSelectedItem().toString())) {
            JOptionPane.showMessageDialog(null, "Desa belum diisi");
            return false;
        }

        return true;
    }

    public void idAuto() {
        try {
            // Select the maximum id from the data table
            String SQL = "SELECT MAX(id) AS maxId FROM data";
            try (PreparedStatement preparedStatement = conn.prepareStatement(SQL);
                    ResultSet resultSet = preparedStatement.executeQuery()) {

                int nextId = 1;
                if (resultSet.next()) {
                    int maxId = resultSet.getInt("maxId");
                    nextId = maxId + 1;
                }

                // Format the ID with leading zeros
                String formattedId = String.format("%03d", nextId);

                // Set the formatted ID to the idTxt field
                idTxt.setText(formattedId);

                // Make the idTxt non-editable
                idTxt.setEditable(false);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tambah_data() {
        if (tambah()) {

            String tmp_idTxt = idTxt.getText();
            String tmp_nikTxt = nikTxt.getText();
            String tmp_noKkTxt = noKkTxt.getText();
            String tmp_namaLengkapTxt = namaLengkapTxt.getText();
            String tmp_tempatLahirTxt = tempatLahirTxt.getText();
            Calendar tmp_tanggalKalendar = tanggalLahir.getCalendar();
            String tmp_jenisKelaminBox = jenisKelaminBox.getSelectedItem().toString();
            String tmp_jenisDisabilitasBox = jenisDisabilitasBox.getSelectedItem().toString();
            String tmp_kecamatanBox = kecamatanBox.getSelectedItem().toString();
            String tmp_desaBox = desaBox.getSelectedItem().toString();

            String SQL = "INSERT INTO data (id, nik, no_kk, nama_lengkap, tempat_lahir, tanggal_lahir, jenis_kelamin, jenis_disabilitas, desa, kecamatan) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
                preparedStatement.setString(1, tmp_idTxt);
                preparedStatement.setString(2, tmp_nikTxt);
                preparedStatement.setString(3, tmp_noKkTxt);
                preparedStatement.setString(4, tmp_namaLengkapTxt);
                preparedStatement.setString(5, tmp_tempatLahirTxt);
                preparedStatement.setDate(6, new java.sql.Date(tmp_tanggalKalendar.getTimeInMillis()));
                preparedStatement.setString(7, tmp_jenisKelaminBox);
                preparedStatement.setString(8, tmp_jenisDisabilitasBox);
                preparedStatement.setString(9, tmp_kecamatanBox);
                preparedStatement.setString(10, tmp_desaBox);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Data Berhasil ditambah", "Pesan", JOptionPane.INFORMATION_MESSAGE);

                    // Clear the form and generate a new ID
                    bersih();
                    idAuto();
                } else {
                    JOptionPane.showMessageDialog(null, "Data Gagal ditambah", "Pesan", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Data Gagal ditambah", "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void setFields(String id, String nik, String noKk, String namaLengkap, String tempatLahir,
            String tanggalLahir, String jenisKelamin, String jenisDisabilitas, String kecamatan, String desa) {
        // Set the values to the form fields
        idTxt.setText(id);
        nikTxt.setText(nik);
        noKkTxt.setText(noKk);
        namaLengkapTxt.setText(namaLengkap);
        tempatLahirTxt.setText(tempatLahir);
        jenisKelaminBox.setSelectedItem(jenisKelamin);
        jenisDisabilitasBox.setSelectedItem(jenisDisabilitas);

        // Set the selected item for kecamatanBox
        kecamatanBox.setSelectedItem(kecamatan);

        // Update the desaBox based on the selected kecamatan
        updateDesaComboBox();

        // Set the selected item for desaBox
        desaBox.setSelectedItem(desa);
    }

    public void ubah() {
        int tmp_idTxt = Integer.parseInt(idTxt.getText());
        String tmp_nikTxt = nikTxt.getText();
        String tmp_noKkTxt = noKkTxt.getText();
        String tmp_namaLengkapTxt = namaLengkapTxt.getText();
        String tmp_tempatLahirTxt = tempatLahirTxt.getText();
        Date tmp_tanggalLahir = new java.sql.Date(tanggalLahir.getCalendar().getTimeInMillis());
        String tmp_jenisKelaminBox = jenisKelaminBox.getSelectedItem().toString();
        String tmp_jenisDisabilitasBox = jenisDisabilitasBox.getSelectedItem().toString();

        // Get the selected items from kecamatanBox and desaBox
        String tmp_kecamatanBox = kecamatanBox.getSelectedItem().toString();
        String tmp_desaBox = desaBox.getSelectedItem().toString();

        // Use a prepared statement to avoid SQL injection
        String SQL = "UPDATE data SET "
                + "nik = ?, "
                + "no_kk = ?, "
                + "nama_lengkap = ?, "
                + "jenis_kelamin = ?, "
                + "tempat_lahir = ?, "
                + "tanggal_lahir = ?, "
                + "jenis_disabilitas = ?, "
                + "kecamatan = ?, "
                + "desa = ? "
                + "WHERE id = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(SQL)) {
            preparedStatement.setString(1, tmp_nikTxt);
            preparedStatement.setString(2, tmp_noKkTxt);
            preparedStatement.setString(3, tmp_namaLengkapTxt);
            preparedStatement.setString(4, tmp_jenisKelaminBox);
            preparedStatement.setString(5, tmp_tempatLahirTxt);
            preparedStatement.setDate(6, tmp_tanggalLahir);
            preparedStatement.setString(7, tmp_jenisDisabilitasBox);
            preparedStatement.setString(8, tmp_kecamatanBox);
            preparedStatement.setString(9, tmp_desaBox);
            preparedStatement.setInt(10, tmp_idTxt);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Data Berhasil diubah", "Pesan", JOptionPane.INFORMATION_MESSAGE);
                bersih();
            } else {
                JOptionPane.showMessageDialog(null, "Data Gagal diubah", "Pesan", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Data Gagal diubah", "Pesan", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        nikTxt = new javax.swing.JTextField();
        namaLengkapTxt = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jenisKelaminBox = new javax.swing.JComboBox<>();
        idTxt = new javax.swing.JTextField();
        desaBox = new javax.swing.JComboBox<>();
        noKkTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tempatLahirTxt = new javax.swing.JTextField();
        inputBtn5 = new javax.swing.JButton();
        bersihBtn = new javax.swing.JButton();
        editBtn = new javax.swing.JButton();
        jenisDisabilitasBox = new javax.swing.JComboBox<>();
        kecamatanBox = new javax.swing.JComboBox<>();
        tanggalLahir = new com.toedter.calendar.JDateChooser();
        jPanel4 = new javax.swing.JPanel();
        inputBtn2 = new javax.swing.JButton();
        viewBtn2 = new javax.swing.JButton();
        logOutBtn2 = new javax.swing.JButton();
        exitBtn2 = new javax.swing.JButton();
        exitBtn1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setTitle("MASUKAN DATA");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("NIK");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Nama Lengkap");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jLabel7.setBackground(new java.awt.Color(51, 102, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 255));
        jLabel7.setText("MASUKAN DATA");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Jenis Kelamin");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Jenis Disabilitas");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 460, -1, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Desa");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 560, -1, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Kecamatan");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, -1, -1));

        nikTxt.setPreferredSize(new java.awt.Dimension(150, 35));
        nikTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nikTxtActionPerformed(evt);
            }
        });
        jPanel1.add(nikTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 200, 420, -1));

        namaLengkapTxt.setPreferredSize(new java.awt.Dimension(150, 35));
        namaLengkapTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaLengkapTxtActionPerformed(evt);
            }
        });
        jPanel1.add(namaLengkapTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 150, 420, -1));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setText("ID");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, -1, -1));

        jenisKelaminBox.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel1.add(jenisKelaminBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 420, -1));

        idTxt.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        idTxt.setPreferredSize(new java.awt.Dimension(150, 35));
        idTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idTxtActionPerformed(evt);
            }
        });
        jPanel1.add(idTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 100, 420, -1));

        desaBox.setPreferredSize(new java.awt.Dimension(150, 35));
        desaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desaBoxActionPerformed(evt);
            }
        });
        jPanel1.add(desaBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 550, 420, -1));

        noKkTxt.setPreferredSize(new java.awt.Dimension(150, 35));
        noKkTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noKkTxtActionPerformed(evt);
            }
        });
        jPanel1.add(noKkTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 250, 420, -1));

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("No Kartu keluarga");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Tempat Lahir");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, -1, -1));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("Tanggal Lahir");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        tempatLahirTxt.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel1.add(tempatLahirTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 300, 420, -1));

        inputBtn5.setBackground(new java.awt.Color(25, 118, 211));
        inputBtn5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inputBtn5.setForeground(new java.awt.Color(255, 255, 255));
        inputBtn5.setText("TAMBAH");
        inputBtn5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inputBtn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBtn5ActionPerformed(evt);
            }
        });
        jPanel1.add(inputBtn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 100, 161, 40));

        bersihBtn.setBackground(new java.awt.Color(25, 118, 211));
        bersihBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bersihBtn.setForeground(new java.awt.Color(255, 255, 255));
        bersihBtn.setText("BERSIH");
        bersihBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bersihBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bersihBtnActionPerformed(evt);
            }
        });
        jPanel1.add(bersihBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 160, 161, 40));

        editBtn.setBackground(new java.awt.Color(25, 118, 211));
        editBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        editBtn.setForeground(new java.awt.Color(255, 255, 255));
        editBtn.setText("SIMPAN UBAH");
        editBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        editBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editBtnActionPerformed(evt);
            }
        });
        jPanel1.add(editBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 220, 161, 40));

        jenisDisabilitasBox.setPreferredSize(new java.awt.Dimension(150, 35));
        jenisDisabilitasBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jenisDisabilitasBoxActionPerformed(evt);
            }
        });
        jPanel1.add(jenisDisabilitasBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 450, 420, -1));

        kecamatanBox.setPreferredSize(new java.awt.Dimension(150, 35));
        kecamatanBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kecamatanBoxActionPerformed(evt);
            }
        });
        jPanel1.add(kecamatanBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 500, 420, -1));

        tanggalLahir.setPreferredSize(new java.awt.Dimension(150, 35));
        jPanel1.add(tanggalLahir, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, 420, -1));

        jPanel4.setBackground(new java.awt.Color(25, 118, 211));

        inputBtn2.setBackground(new java.awt.Color(255, 255, 255));
        inputBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inputBtn2.setForeground(new java.awt.Color(25, 118, 211));
        inputBtn2.setText("MASUKAN DATA");
        inputBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inputBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputBtn2ActionPerformed(evt);
            }
        });

        viewBtn2.setBackground(new java.awt.Color(255, 255, 255));
        viewBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        viewBtn2.setForeground(new java.awt.Color(25, 118, 211));
        viewBtn2.setText("TAMPILKAN DATA");
        viewBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        viewBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewBtn2ActionPerformed(evt);
            }
        });

        logOutBtn2.setBackground(new java.awt.Color(255, 255, 255));
        logOutBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        logOutBtn2.setForeground(new java.awt.Color(25, 118, 211));
        logOutBtn2.setText("LOGOUT");
        logOutBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logOutBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutBtn2ActionPerformed(evt);
            }
        });

        exitBtn2.setBackground(new java.awt.Color(255, 255, 255));
        exitBtn2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        exitBtn2.setForeground(new java.awt.Color(25, 118, 211));
        exitBtn2.setText("KELUAR");
        exitBtn2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exitBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtn2ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(logOutBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewBtn2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(inputBtn2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBtn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exitBtn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(24, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(28, 28, 28)
                .addComponent(exitBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(inputBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logOutBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(exitBtn2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void nikTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nikTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nikTxtActionPerformed

    private void namaLengkapTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaLengkapTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaLengkapTxtActionPerformed

    private void desaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desaBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_desaBoxActionPerformed

    private void inputBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBtn2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputBtn2ActionPerformed

    private void viewBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewBtn2ActionPerformed
        ViewForm viewForm = new ViewForm();
        viewForm.setVisible(true);
        this.getDesktopPane().add(viewForm);
        this.dispose();
    }//GEN-LAST:event_viewBtn2ActionPerformed

    private void logOutBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutBtn2ActionPerformed
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        this.getDesktopPane().add(loginForm);
        this.dispose();
    }//GEN-LAST:event_logOutBtn2ActionPerformed

    private void exitBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn2ActionPerformed
        int pilih = JOptionPane.showConfirmDialog(null, "Keluar Aplikasi?",
                "Konfirmasi", JOptionPane.OK_OPTION);
        if (pilih == JOptionPane.OK_OPTION) {
            System.exit(0);
        } else {
            JOptionPane.getRootFrame();
        }
    }//GEN-LAST:event_exitBtn2ActionPerformed

    private void noKkTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noKkTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noKkTxtActionPerformed

    private void editBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editBtnActionPerformed
        ubah();
    }//GEN-LAST:event_editBtnActionPerformed

    private void bersihBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bersihBtnActionPerformed
        bersih();
    }//GEN-LAST:event_bersihBtnActionPerformed

    private void inputBtn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputBtn5ActionPerformed
        if (tambah()) {
            tambah_data();
        }
    }//GEN-LAST:event_inputBtn5ActionPerformed

    private void exitBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtn1ActionPerformed
        OptionMenu optionMenu = new OptionMenu();
        optionMenu.setVisible(true);
        this.getDesktopPane().add(optionMenu);
        this.dispose();
    }//GEN-LAST:event_exitBtn1ActionPerformed

    private void idTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idTxtActionPerformed

    private void jenisDisabilitasBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jenisDisabilitasBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jenisDisabilitasBoxActionPerformed

    private void kecamatanBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kecamatanBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kecamatanBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bersihBtn;
    private javax.swing.JComboBox<String> desaBox;
    private javax.swing.JButton editBtn;
    private javax.swing.JButton exitBtn1;
    private javax.swing.JButton exitBtn2;
    private javax.swing.JTextField idTxt;
    private javax.swing.JButton inputBtn2;
    private javax.swing.JButton inputBtn5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox<String> jenisDisabilitasBox;
    private javax.swing.JComboBox<String> jenisKelaminBox;
    private javax.swing.JComboBox<String> kecamatanBox;
    private javax.swing.JButton logOutBtn2;
    private javax.swing.JTextField namaLengkapTxt;
    private javax.swing.JTextField nikTxt;
    private javax.swing.JTextField noKkTxt;
    private com.toedter.calendar.JDateChooser tanggalLahir;
    private javax.swing.JTextField tempatLahirTxt;
    private javax.swing.JButton viewBtn2;
    // End of variables declaration//GEN-END:variables

    void setFieldssetFields(String id, String nik, String noKk, String namaLengkap, String tempatLahir, String tanggalLahir, String jenisKelamin,
            String jenisDisabilitas, String desa, String kecamatan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
