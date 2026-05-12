# alstrudat-C04-ifs24011

# Description
Sebuah lembaga kesehatan masyarakat sedang melacak penyebaran virus mutasi baru. Diketahui bahwa karakteristik interaksi manusia membentuk struktur Graph tak berarah, di mana setiap individu dapat berinteraksi fisik dengan banyak orang.
Karena jumlah data mencapai jutaan, pencarian data pasien untuk memperbarui status medis mereka akan memakan waktu terlalu lambat. Oleh karena itu, sistem harus menggunakan Hash Table. Hash Table akan menyimpan Patient_ID sebagai Key dan Pointer/Referensi ke Node Graph (Vertex) sebagai Value. Struktur Graph sendiri akan menggunakan Adjacency List untuk menghemat memori.

Setiap entitas dalam Graph memiliki atribut:
Patient_ID (String, Unik)
Name (String)
Status (String: AMAN, TERINFEKSI, SEMBUH. Default awal: AMAN)
Contacts (List/Vector/Linked List of Pointers ke Node tetangga)

# Format Perintah (Input)
Baris pertama adalah N (jumlah operasi). Baris berikutnya berisi salah satu dari perintah berikut:
## ROOT <Patient_ID> <Name>
Mendaftarkan Patient Zero. Node ini langsung berstatus TERINFEKSI. Sistem mencatat ID ini sebagai Akar (Root) dan juga otomatis menjadi Pasien Terakhir Terinfeksi (Last Infected).
Cetak error jika ROOT sudah pernah didaftarkan.
## TRACE <Existing_ID> <New_Patient_ID> <Name>
Mendaftarkan pasien baru dengan status AMAN dan langsung membuat garis kontak (Edge tak berarah) dengan Existing_ID.
Cetak error jika Existing_ID tidak ditemukan atau New_Patient_ID sudah ada.
## LINK <ID_1> <ID_2>
Menambahkan riwayat kontak tambahan (Edge) antara dua pasien yang sudah ada di sistem. Ini memungkinkan Graph membentuk siklus (Cycle).
Cetak error jika salah satu ID tidak ditemukan.
## INFECT <Patient_ID>
Mengubah status orang tersebut menjadi TERINFEKSI. Sistem wajib memperbarui penunjuk "Pasien Terakhir Terinfeksi" menjadi ID ini.
Cetak error jika ID tidak ditemukan di dalam sistem.
## HEAL <Patient_ID>
Mengubah status pasien menjadi SEMBUH. (Catatan: Jika pasien ini adalah pasien terakhir yang terinfeksi, ia tetap menjadi target SHORTEST_PATH karena rekam jejak pelacakan tidak hilang).
Cetak error jika ID tidak ditemukan.
## GET_Active_ <Patient_ID>
Menghitung total orang yang saat ini berstatus TERINFEKSI di dalam jaringan yang saling terhubung (Connected Component) dari 
## <Patient_ID>.
 Wajib menggunakan DFS / BFS dan Visited Array/Set agar tidak terjadi Infinite Loop.
Cetak error jika ID tidak ditemukan.
## Shortest_path
Mencari jalur kontak terpendek (derajat pemisah) dari ROOT ke pasien Terakhir Terinfeksi (pasien yang paling terakhir dikenai perintah INFECT, atau Root jika belum ada perintah INFECT lain). Wajib menggunakan BFS. Cetak rute dipisahkan dengan tanda ->.
Cetak error jika jalur tidak ditemukan.
