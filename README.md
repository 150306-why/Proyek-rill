# alstrudat-C04-ifs24011

# Description
Sebuah lembaga kesehatan masyarakat sedang melacak penyebaran virus mutasi baru. Diketahui bahwa karakteristik interaksi manusia membentuk struktur linked list, di mana setiap individu dapat berinteraksi fisik dengan banyak orang.
Karena jumlah data mencapai jutaan, pencarian data pasien untuk memperbarui status medis mereka akan memakan waktu terlalu lambat. 

Setiap entitas dalam Graph memiliki atribut:
Patient_ID (String, Unik)
Name (String)
Status (String: AMAN, TERINFEKSI, SEMBUH. Default awal: AMAN)
path: linked list dalam bentuk jalur panah

# Format Perintah (Input)
Baris pertama adalah N (jumlah operasi). Baris berikutnya berisi salah satu dari perintah berikut:
## ROOT <Patient_ID> <Name>
Mendaftarkan Patient Zero. Node ini langsung berstatus TERINFEKSI. Sistem mencatat ID ini sebagai Akar (Root) dan juga otomatis menjadi Pasien Terakhir Terinfeksi (Last Infected).
Cetak error jika ROOT sudah pernah didaftarkan.
## INFECT <Patient_ID>
Mengubah status orang tersebut menjadi TERINFEKSI. Sistem wajib memperbarui penunjuk "Pasien Terakhir Terinfeksi" menjadi ID ini.
Cetak error jika ID tidak ditemukan di dalam sistem.
## HEAL <Patient_ID>
Mengubah status pasien menjadi SEMBUH. (Catatan: Jika pasien ini adalah pasien terakhir yang terinfeksi, ia tetap menjadi target SHORTEST_PATH karena rekam jejak pelacakan tidak hilang).
Cetak error jika ID tidak ditemukan.
## Shortest_path
Mencari jalur kontak terpendek (derajat pemisah) dari ROOT ke pasien Terakhir Terinfeksi (pasien yang paling terakhir dikenai perintah INFECT, atau Root jika belum ada perintah INFECT lain). Wajib menggunakan BFS. Cetak rute dipisahkan dengan tanda ->.
Cetak error jika jalur tidak ditemukan.
