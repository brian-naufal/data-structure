# Dokumentasi Operasi Single Linked List (SSL.java)

Dokumen ini berisi penjelasan lengkap mengenai operasi-operasi tambahan (penghapusan, penyisipan, pencarian, dan pengaksesan) yang baru saja ditambahkan pada kelas `SSL`.

## 5. Penghapusan (Deletion)

### `void removeFirst()`

Menghapus node pertama (`head`) dari Linked List.

- **Logika**:
  - Pertama-tama metode ini akan mengecek apakah list tidak kosong menggunakan `!isEmpty()`. Jika kosong, maka tidak ada operasi yang dilakukan.
  - Jika `head == tail` (artinya hanya tersisa 1 node di dalam list), maka `head` dan `tail` akan diset menjadi `null`.
  - Jika terdapat lebih dari 1 node, penunjuk `head` akan digeser ke node di sebelahnya (`head = head.next`).
  - Setelah penghapusan berhasil, kurangi nilai `size` sebanyak 1.

### `void removeLast()`

Menghapus node terakhir (`tail`) dari Linked List.

- **Logika**:
  - Pastikan list tidak kosong dengan `!isEmpty()`.
  - Jika hanya ada 1 node (`head == tail`), `head` dan `tail` diubah menjadi `null`.
  - Jika node lebih dari 1, kita tidak bisa langsung menghapus `tail` karena kita perlu mengubah penunjuk `next` dari node _sebelum_ `tail` menjadi `null`.
  - Karenanya, kita melakukan iterasi (penelusuran) mulai dari `head` menggunakan variabel `current` sampai kita menemukan node tepat sebelum `tail` (yaitu ketika `current.next == tail`).
  - Setelah ditemukan, kita set `current.next = null`, lalu memindahkan posisi `tail` agar menunjuk ke `current`.
  - Terakhir, kurangi `size` sebanyak 1.

---

## 6. Penyisipan (Insertion)

### `void insertAfter(Node prevNode, Node input)`

Menyisipkan node baru (`input`) persis setelah node referensi yang diberikan (`prevNode`).

- **Logika**:
  - Pastikan node referensi `prevNode` tidak bernilai `null`.
  - Node baru `input` diarahkan agar menunjuk ke node yang saat ini berada setelah `prevNode` (`input.next = prevNode.next`).
  - Setelah itu, node referensi `prevNode` diubah agar menunjuk ke node baru `input` (`prevNode.next = input`).
  - Jika ternyata `prevNode` adalah node terakhir (`tail`), maka kita harus memperbarui posisi `tail` agar menunjuk ke node `input` yang baru dimasukkan.
  - Tambahkan nilai `size` sebanyak 1.

### `void insertAt(int index, Node input)`

Menyisipkan node baru (`input`) pada indeks/posisi tertentu di dalam list.

- **Logika**:
  - Jika `index == 0`, ini sama artinya dengan menyisipkan di awal, sehingga fungsi memanggil `addFirst(input)`.
  - Jika `index == size`, ini sama artinya dengan menyisipkan di akhir, sehingga fungsi memanggil `addLast(input)`.
  - Jika indeks berada di tengah list (`> 0` dan `< size`), kita harus mencari node tepat sebelum indeks tujuan (berada di posisi `index - 1`) melalui iterasi (perulangan `for`).
  - Setelah node sebelumnya ditemukan (`current`), lakukan hal yang sama seperti di `insertAfter`: sambungkan `input.next` ke `current.next`, dan `current.next` ke `input`.
  - Jika indeks diberikan berada di luar jangkauan (misal negatif atau > size), maka cetak "Index out of bounds".

---

## 7. Pencarian (Searching)

### `Node search(Object data)`

Mencari sebuah nilai/data tertentu di dalam Linked List, dan mengembalikan objek `Node`-nya jika berhasil ditemukan.

- **Logika**:
  - Mulai penelusuran dari node awal (`head`) dengan menggunakan variabel iterasi `current`.
  - Lakukan perulangan (`while (current != null)`). Pada setiap langkahnya, periksa apakah data di dalam node tersebut sama dengan parameter `data` yang dicari menggunakan operator `.equals(data)`.
  - Jika ditemukan kecocokan, fungsi akan langsung berhenti dan mengembalikan node `current` tersebut.
  - Jika tidak cocok, lanjut periksa node berikutnya (`current = current.next`).
  - Jika seluruh list sudah ditelusuri dan data tidak juga ditemukan, fungsi akan mengembalikan nilai `null`.

---

## 8. Pengaksesan (Accessing)

### `Node get(int index)`

Mengambil/mengembalikan `Node` yang menempati indeks atau posisi tertentu di dalam list, tanpa menghapusnya.

- **Logika**:
  - Pertama, lakukan pengecekan rentang (validasi). Jika `index < 0` atau melampaui jumlah isi list (`index >= size`), langsung kembalikan nilai `null`.
  - Jika indeks valid, mulai iterasi dari `head` sebanyak `index` kali dengan perulangan `for`.
  - Pada setiap putaran, `current` digeser ke node selanjutnya (`current = current.next`).
  - Saat perulangan usai, variabel `current` dipastikan telah berada tepat pada posisi indeks yang diminta, lalu return (kembalikan) node tersebut.
