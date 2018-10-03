# Sistem Pencatatan Data Bencana dan Posko
---

## 1. Aplikasi Web 
Pada aplikasi pencatatan data bencana dan posko berbasis web memiliki beberapa fungsi diantaranya adalah login. Login disini digunakan agar tidak semua orang dapat menggunakan aplikasi web tersebut, hanya pengguna khusus yang telah ditunjuk. Login dilakukan dengan cara memasukan username dan password yang sudah terdaftarkan agar pengguna dapat masuk ke dalam sistem dan dapat menggunakan fungsi lainnya pada aplikasi web tersebut. Setelah berhasil melakukan login, pengguna dapat menggunakan fungsi lain seperti melakukan pengelolaan data petugas. Fungsi tersebut memungkinkan pengguna dapat menambah data petugas baru, menampilkan data petugas yang sudah ada, mengubah data petugas yang sudah ada, menghapus data petugas yang sudah ada, melakukan reset password petugas, serta melakukan pencarian data petugas. Fungsi selanjutnya adalah melakukan pengelolaan pesan. Fungsi tersebut memungkinkan pengguna untuk dapat menampilkan data pesan masuk, menampilkan data pesan terkirim, menulis pesan baru, melakukan broadcast informasi bencana, serta melakukan broadcast informasi posko.

Github Repository: https://github.com/heryatmo/msb_laravel

### 1.1. Fungsionalistas

| No | Fitur | Deskripsi Fitur |
| ------------- | ------------- | ------------- |
| 1  | Mengelola Pengguna | Fungsi ini digunakan oleh aktor untuk mengelola data user. Aktor dapat melakukan tambah data user, ubah data user, hapus data user, cari data user, dan tampil data user.  |
| 2  | Mengelola Bencana  | Fungsi ini digunakan oleh aktor untuk mengelola data bencana. Aktor dapat melakukan tambah data bencana, ubah data bencana, hapus data bencana, cari data bencana, dan tampil data bencana.  |

---

## 2. Aplikasi Mobile 
Pada aplikasi pencatatan data bencana dan posko berbasis mobile memiliki beberapa fungsi diantaranya adalah login. Login disini digunakan agar tidak semua orang dapat menggunakan aplikasi mobile tersebut, hanya pengguna khusus yang telah ditunjuk. Login dilakukan dengan cara memasukan password yang sesuai agar pengguna dapat masuk ke dalam sistem dan dapat menggunakan fungsi lainnya pada aplikasi mobile tersebut. Setelah berhasil melakukan login, pengguna dapat menggunakan fungsi lain seperti melakukan ubah password. Ubah password disini digunakan untuk mengubah password yang digunakan saat login sesuai dengan yang pengguna inginkan. Fungsi selanjutnya adalah reset password, reset password digunakan untuk mengubah password seperti keadaan awal. Fungsi selanjutnya adalah melakukan set nomor SMS Gateway, dimana digunakan untuk mengatur nomor tujuan penerima pesan hasil pencatatan data. Fungsi selanjutnya adalah cek GPS, dimana digunakan untuk melakukan pengecekan apakah fungsi GPS pada perangkat mobile dapat digunakan pada saat itu. Fungsi lainnya adalah melakukan pencatatan data bencana serta posko, baik untuk keadaan awal maupun perkembangan. Fungsi ini digunakan pengguna untuk melakukan pencatatan data bencana maupun posko, yakni dengan menginputkan data-data yang dibutuhkan. Pada fungsi pencatatan data awal bencana maupun posko, sistem juga sekaligus menentukan koordinat lokasi dimana penggunna berada yang juga akan disertakan dalam pencatatan data tersebut. Hasil pencatatan data tersebut akan dikirimkan dalam bentuk pesan singkat (SMS) menuju nomor yang sudah ditentukan.

Github Repository: https://github.com/heryatmo/msb_mob

### 2.1. Fungsionalistas

| No | Fitur | Deskripsi Fitur |
| ------------- | ------------- | ------------- |
| 1  | Mendaftar Akun | Fungsi ini digunakan oleh aktor untuk mendaftarkan diri sebagai pengguna aplikasi.  |
| 2  | Memilih Peran | Fungsi ini digunakan oleh aktor untuk memilih peran sebagai volunteer atau shelter manager pada aplikasi.  |
| 3  | Melakukan Donasi | Fungsi ini digunakan oleh aktor untuk melakukan donasi berupa uang atau logistik pada aplikasi.  |
| 4  | Melihat Profil | Fungsi ini digunakan oleh aktor untuk melihat profil dari aktor.  |
| 5 | Mengelola Shelter/Posko  | Fungsi ini digunakan oleh aktor untuk mengelola data Shelter. Aktor dapat melakukan tambah data Shelter, ubah data Shelter, hapus data Shelter, cari data Shelter, dan tampil data Shelter. |
| 6  | Mengelola Volunteer  | Fungsi ini digunakan oleh aktor untuk mengelola data Volunteer. Aktor dapat melakukan tampil data calon Volunteer dan Approve calon Volunteer.  |
| 7  | Mengelola Pusat Informasi  | Fungsi ini digunakan oleh aktor untuk mengelola data informasi yang akan dipublikasikan. Aktor dapat melakukan tambah data informasi yang akan dipublikasikan, ubah data informasi yang dipublikasikan, hapus data informasi yang dipublikasikan, cari data informasi yang dipublikasikan, dan tampil data informasi yang  dipublikasikan.  |
| 8  | Menampilkan Laporan Distribusi Donasi  | Fungsi ini digunakan oleh aktor untuk menampilkan laporan terkait dengan data distribusi donasi.  |
| 9  | Mengelola Laporan Pengungsi  | Fungsi ini digunakan oleh aktor untuk menampilkan laporan terkait dengan data pengungsi.  |
| 10  | Mengelola Pengungsi  | Fungsi ini digunakan oleh aktor untuk mengelola data Pengungsi. Aktor dapat melakukan tambah data Pengungsi, ubah data Pengungsi, hapus data Pengungsi, cari data Pengungsi, dan tampil data Pengungsi. |
| 11  | Mendistribusikan Logistik | Fungsi ini digunakan oleh aktor untuk mendistribusikan logistik.  |
| 12  | Melihat Informasi | Fungsi ini digunakan oleh aktor untuk melihat informasi-informasi yang dipublikasikan oleh shelter manager.  |
