# Tugas Praktikum 5 DPBO 2026 - PeopleData App (MySQL & Database Integration)

Nama: Muh. Adib Al-Fathi NIM: 2500418 Kelas: C1

**Janji**

Saya Muh. Adib Al-Fathi dengan NIM 2500418 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

## 1. Penjelasan Singkat & Alur Program
Di Modul 5 ini, aplikasi Java Swing GUI yang tadinya nyimpen data pakai `ArrayList` lokal, sekarang sudah di-upgrade supaya terhubung langsung ke **Database MySQL** menggunakan JDBC[cite: 1, 3, 5].

Alur dan fitur utama aplikasinya:
- **Koneksi (`Database.java`):** Mengatur koneksi driver JDBC ke database `db_people`[cite: 3].
- **Create (Add):** Nambah data baru ke database. Dilengkapi validasi supaya form gak boleh kosong dan ID gak boleh kembar (*duplicate ID*)[cite: 1, 5].
- **Read:** Menampilkan data dari tabel database secara otomatis ke `JTable` pas aplikasi pertama kali dibuka[cite: 1, 3, 5].
- **Update:** Mengubah data yang ada dengan cara klik baris di tabel (pakai *Mouse Listener* buat narik data ke form), edit, terus klik update[cite: 1, 5].
- **Delete:** Menghapus data dari database lengkap dengan pop-up konfirmasi *JOptionPane*[cite: 1, 5].

---

## 2. Dokumentasi Pengujian Fitur (Screenshot)
Berikut adalah bukti screenshot hasil uji coba CRUD dan validasi *error* di aplikasi:

- **Pengujian Error (Kolom Kosong):**
  <br><img src="Dokumentasi/Error Kolom Kosong.png" width="450">

- **Pengujian Error (ID Sama / Terdaftar):**
  <br><img src="Dokumentasi/Error ID sama.png" width="450">

- **Pengujian Tambah Data (Add):**
  <br><img src="Dokumentasi/Add Data.png" width="450">

- **Pengujian Ubah Data (Update Status):**
  <br><img src="Dokumentasi/Update Data(status).png" width="450">

- **Pengujian Hapus Data (Delete dengan Konfirmasi):**
  <br><img src="Dokumentasi/Delete Data.png" width="450">
