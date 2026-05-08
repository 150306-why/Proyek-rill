
1. # ============================================================
ALSTRUDAT CIRCLE BATTLE — HARD PROBLEM
Judul  : Expedition to the Ancient Network
Topik  : Graph + State-Space Dijkstra + Dynamic Programming
Level  : SULIT
============================================================

------------------------------------------------------------
DESKRIPSI MASALAH
------------------------------------------------------------

Kamu adalah seorang penjelajah yang sedang menavigasi
sebuah jaringan kota kuno yang terdiri dari N kota
yang terhubung oleh M jalan satu arah.

Setiap jalan memiliki biaya perjalanan (weight).

Terdapat mekanisme khusus bernama "Time Warp Portal"
yang berada di setiap kota. Portal ini hanya boleh
digunakan PALING BANYAK SATU KALI selama perjalananmu.

Ketika kamu mengaktifkan portal di kota U,
maka jalan BERIKUTNYA yang kamu ambil dari U
akan memiliki biaya 0 (nol) daripada bobot normalnya.

Selain itu, beberapa kota ditandai sebagai "Terkutuk".

Jika kamu berada di kota terkutuk dan kamu KELUAR dari kota itu,
kamu harus membayar penalti tambahan sebesar nilai kutukan kota tersebut
di atas biaya jalan — TETAPI hanya jika kamu BELUM menggunakan portal
sebelum momen tersebut.

Aturan Penting:
1. Portal hanya dapat digunakan PALING BANYAK SATU KALI.
2. Menggunakan portal di kota U membuat jalan keluar berikutnya
   dari U memiliki biaya 0 (terlepas dari bobot aslinya).
3. Penalti kutukan diterapkan saat KELUAR dari kota terkutuk.
   Penalti ini ditambahkan ke total biaya perjalanan.
4. Jika kamu menggunakan portal di kota terkutuk U:
   - Penalti kutukan tetap berlaku.
   - Biaya jalan menjadi 0 karena efek portal.
   - Jadi biaya efektif = 0 + curse[U].
5. Jika portal sudah digunakan sebelum mencapai kota terkutuk,
   maka tidak ada penalti saat keluar dari kota tersebut.
6. Jika tidak ada jalur dari S ke T, output adalah -1.

Tujuan: Temukan biaya total MINIMUM untuk perjalanan dari S ke T.

------------------------------------------------------------
FORMAT INPUT
------------------------------------------------------------

Baris 1 : N M K
          N = jumlah kota (dimulai dari indeks 1)
          M = jumlah jalan berarah
          K = jumlah kota terkutuk

Baris 2 : c1 p1 c2 p2 ... cK pK
          (kota c1 memiliki penalti kutukan p1, dan seterusnya)

          BARIS INI TIDAK ADA jika K = 0.

Berikutnya M baris:
u v w
Jalan berarah dari kota u ke kota v dengan biaya w.

Baris terakhir:
S T
Kota awal S dan kota tujuan T.

------------------------------------------------------------
FORMAT OUTPUT
------------------------------------------------------------

Minimum cost: X

Di mana X adalah sebuah bilangan bulat
yang menunjukkan biaya perjalanan minimum.

Jika tidak ada jalur:
Minimum cost: -1

Output harus persis dengan format ini
untuk penilaian otomatis.

------------------------------------------------------------
BATASAN
------------------------------------------------------------

1  <= N       <= 100.000
1  <= M       <= 300.000
0  <= K       <= min(N, 1000)
1  <= w       <= 1.000.000   (bobot jalan)
1  <= pi      <= 500.000     (penalti kutukan)
1  <= S, T   <= N,  S ≠ T

Graf bersifat berarah.
Boleh ada beberapa edge antara pasangan kota yang sama.
Tidak ada self-loop.

------------------------------------------------------------
CONTOH INPUT
------------------------------------------------------------

6 8 2
3 15 5 10
1 2 10
1 3 5
3 2 4
3 4 8
2 4 3
4 5 2
5 6 7
2 6 30
1 6

------------------------------------------------------------
CONTOH OUTPUT
------------------------------------------------------------

Minimum cost: 10

------------------------------------------------------------
PENJELASAN CONTOH
------------------------------------------------------------

Kota: 1 sampai 6

Kota terkutuk:
- kota 3 (penalti = 15)
- kota 5 (penalti = 10)

Edge berarah:
1->2 (10)
1->3 (5)
3->2 (4)
3->4 (8)
2->4 (3)
4->5 (2)
5->6 (7)
2->6 (30)

Tujuan: dari kota 1 ke kota 6 dengan biaya minimum.

JALUR A - Tanpa portal:
1->3->4->5->6

1->3 = 5
keluar dari kota 3 (terkutuk) +15
3->4 = 8 + 15 = 23

4->5 = 2
keluar dari kota 5 (terkutuk) +10
5->6 = 7 + 10 = 17

Total = 5 + 23 + 2 + 17 = 47

JALUR B - Portal di kota 1:
1->2->4->5->6

1->2 = 0 (portal)
2->4 = 3
4->5 = 2
5->6 = 7

Total = 0 + 3 + 2 + 7 = 12

JALUR C - Portal di kota 2 (OPTIMAL)

1->2 = 10
aktifkan portal di kota 2
2->6 = 0

Total = 10

JALUR D - Portal di kota 3

1->3 = 5
portal di kota 3
3->2 = 0 + 15
2->4 = 3
4->5 = 2
5->6 = 7

Total = 32

Jalur terbaik adalah JALUR C
Total biaya = 10

Jawaban:
Minimum cost: 10

------------------------------------------------------------
PETUNJUK ALGORITMA
------------------------------------------------------------

Modelkan state sebagai:

(city, portalUsed)

di mana portalUsed ∈ {0,1}

Total state = 2 × N

Gunakan algoritma Dijkstra pada graf state ini.

Jika state (u, p=0):

untuk setiap edge (u->v, w)

cursePenalty = curse[u] jika u adalah kota terkutuk

Opsi 1 (tanpa portal)
pindah ke (v,0)
cost += w + cursePenalty

Opsi 2 (pakai portal)
pindah ke (v,1)
cost += 0 + cursePenalty

Jika state (u, p=1):

untuk setiap edge (u->v, w)

Opsi satu-satunya:
pindah ke (v,1)
cost += w

(tidak ada penalti kutukan)

Jawaban akhir:

min(dist[T][0], dist[T][1])

Kompleksitas Waktu:
O((N + M) log N)

Kompleksitas Memori:
O(N + M)

============================================================Proyek real final

