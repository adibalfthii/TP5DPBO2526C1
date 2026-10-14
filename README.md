# TP5DPBO2526C1 - PeopleData App (MySQL & Database Integration)

Praktikum DPBO - Tugas Praktikum Modul 5 (Menghubungkan Java Swing GUI dengan Database MySQL & JDBC)[cite: 1, 3, 5].

---

## 1. Desain Program & Penjelasan Alur (CRUD Database)
Aplikasi **PeopleData** ini merupakan pengembangan dari tugas praktikum sebelumnya. Jika sebelumnya aplikasi menggunakan penyimpanan sementara berbasis `ArrayList`, pada modul ini seluruh penyimpanan data telah dimigrasikan secara penuh ke **Database MySQL** menggunakan koneksi **JDBC**[cite: 1, 3, 5].

### Komponen Utama:
* **`Database.java`**: Kelas utilitas yang bertugas mengatur koneksi utama ke database MySQL (`db_people`) menggunakan `DriverManager` dan driver `com.mysql.cj.jdbc.Driver`[cite: 3].
* **`Person.java`**: Kelas *Model* (POJO) yang merepresentasikan objek data person[cite: 1].
* **`PeopleMenu.java` & `PeopleMenu.form`**: Kelas antarmuka (*Graphical User Interface* / GUI) berbasis Java Swing yang menangani interaksi pengguna dan komponen tabel (`JTable`)[cite: 1, 5].

### Alur Kerja & Fitur Utama:
1. **Read (Muat Data)**: Saat aplikasi dijalankan, metode `loadDatabaseData()` secara otomatis mengambil seluruh record dari tabel `person` di database dan menampilkannya ke dalam `JTable`[cite: 1, 3, 5].
2. **Create (Tambah Data)**: Pengguna mengisi form input (ID, Nama, Tahun Lahir, Kategori, Status) lalu menekan tombol *Add*. Sistem dilengkapi dengan **validasi kolom kosong** serta **pencegahan duplikasi ID** (mengecek apakah ID sudah terdaftar di database sebelum melakukan *insert*)[cite: 1, 5].
3. **Update (Ubah Data)**: Dengan memanfaatkan *Mouse Listener* pada tabel, pengguna dapat mengklik salah satu baris data untuk menarik isinya kembali ke form input secara otomatis, lalu mengubah datanya dan menekan tombol *Update*[cite: 1, 5].
4. **Delete (Hapus Data)**: Pengguna dapat menghapus data dari database dengan memilih baris pada tabel, menekan tombol *Delete*, dan menyetujui kotak dialog konfirmasi (*JOptionPane*)[cite: 1, 5].

---

## 2. Dokumentasi Pengujian Program (Screenshot)
Berikut adalah bukti dokumentasi pengujian fitur CRUD serta penanganan validasi *error* pada aplikasi:

### A. Pengujian Validasi Error (Kolom Kosong)
Sistem menampilkan pesan dialog peringatan jika pengguna mencoba menambah atau memproses data saat ada kolom input yang masih kosong[cite: 5, 24].
<br><img src="Dokumentasi/Error Kolom Kosong.png" width="450">

### B. Pengujian Validasi Error (ID Sudah Terdaftar)
Sistem menolak penambahan data baru dan menampilkan dialog peringatan jika ID yang dimasukkan sudah ada di dalam database[cite: 5, 24].
<br><img src="Dokumentasi/Error ID sama.png" width="450">

### C. Pengujian Tambah Data (Create / Add)
Berhasil menambahkan data baru ke dalam database dan langsung memperbarui tampilan tabel secara *real-time*[cite: 1, 5, 24].
<br><img src="Dokumentasi/Add Data.png" width="450">

### D. Pengujian Ubah Data (Update)
Berhasil memperbarui informasi data yang sudah dipilih pada tabel (seperti mengubah status)[cite: 1, 5, 24].
<br><img src="Dokumentasi/Update Data(status).png" width="450">

### E. Pengujian Hapus Data (Delete dengan Konfirmasi)
Sistem menampilkan dialog konfirmasi keamanan sebelum benar-benar mengeksekusi perintah penghapusan data dari database[cite: 1, 5, 24].
<br><img src="Dokumentasi/Delete Data.png" width="450">
