# Penjelasan Lengkap Method `insertSorted(Node input)`

Dokumen ini menjelaskan secara terperinci alur kerja, logika, serta penanganan kasus (edge cases) pada method `insertSorted(Node input)` yang terdapat pada kelas [`SSL.java`](file:///c:/Users/LOQ/Documents/Programcode/GitRepos/data-structure/singly-linkedlist-adt/Modul4/SSL.java).

---

## 1. Tujuan Method

Method `insertSorted` bertujuan untuk menyisipkan node baru yang berisi data `Mahasiswa` ke dalam Singly Linked List (SLL) secara **terurut (_ascending_) berdasarkan nilai IPK**. Dengan method ini, daftar mahasiswa akan otomatis tersusun rapi dari IPK terkecil hingga IPK terbesar sejak awal penyisipan data.

---

## 2. Kode Lengkap Method

```java
void insertSorted(Node input) {
    if (input == null || input.data == null) return;

    // Pengecekan apakah data merupakan tipe Mahasiswa
    if (!(input.data instanceof Mahasiswa)) {
        addLast(input);
        return;
    }

    Mahasiswa inputMhs = (Mahasiswa) input.data;

    // Kasus 1: List masih kosong
    if (isEmpty()) {
        addFirst(input);
        return;
    }

    // Kasus 2: Data head bukan Mahasiswa
    if (!(head.data instanceof Mahasiswa)) {
        addFirst(input);
        return;
    }

    Mahasiswa headMhs = (Mahasiswa) head.data;
    // IPK input <= IPK head (sisip di paling awal)
    if (inputMhs.getIpk() <= headMhs.getIpk()) {
        addFirst(input);
        return;
    }

    // Kasus 3: IPK input >= IPK tail (sisip di paling akhir)
    if (tail.data instanceof Mahasiswa) {
        Mahasiswa tailMhs = (Mahasiswa) tail.data;
        if (inputMhs.getIpk() >= tailMhs.getIpk()) {
            addLast(input);
            return;
        }
    }

    // Kasus 4: Sisip di posisi yang sesuai di tengah list
    Node current = head;
    while (current.next != null) {
        if (current.next.data instanceof Mahasiswa) {
            Mahasiswa nextMhs = (Mahasiswa) current.next.data;
            if (inputMhs.getIpk() <= nextMhs.getIpk()) {
                input.next = current.next;
                current.next = input;
                size++;
                return;
            }
        }
        current = current.next;
    }

    // Penanganan default jika tidak memenuhi kondisi loop
    addLast(input);
}
```

---

## 3. Penjelasan Alur dan Logika (Step-by-Step)

### A. Validasi Awal & Tipe Data

1. `if (input == null || input.data == null) return;`
   - Memastikan node `input` yang dimasukkan tidak bernilai `null` dan memiliki isi `data`.
2. `if (!(input.data instanceof Mahasiswa))`
   - Mengecek apakah data yang akan dimasukkan merupakan objek `Mahasiswa`. Jika bukan, maka node tersebut akan langsung ditaruh di akhir list dengan `addLast(input)`.
3. `Mahasiswa inputMhs = (Mahasiswa) input.data;`
   - Melakukan _casting_ tipe data dari `Object` menjadi `Mahasiswa` agar kita bisa mengakses method `.getIpk()`.

---

### B. Kasus 1: Linked List Masih Kosong

```java
if (isEmpty()) {
    addFirst(input);
    return;
}
```

- **Kondisi**: Jika list belum berisi node sama sekali (`head == null` atau `size == 0`).
- **Tindakan**: Panggil `addFirst(input)`. Node baru tersebut langsung menjadi `head` sekaligus `tail`.

---

### C. Kasus 2: Penyisipan di Paling Awal (IPK Terkecil)

```java
Mahasiswa headMhs = (Mahasiswa) head.data;
if (inputMhs.getIpk() <= headMhs.getIpk()) {
    addFirst(input);
    return;
}
```

- **Kondisi**: IPK dari mahasiswa baru lebih kecil atau sama dengan IPK milik mahasiswa di `head`.
- **Tindakan**: Panggil `addFirst(input)`. Node baru akan diletakkan di depan `head` lama, lalu `head` diperbarui menunjuk ke node baru tersebut.

---

### D. Kasus 3: Penyisipan di Paling Akhir (IPK Terbesar)

```java
if (tail.data instanceof Mahasiswa) {
    Mahasiswa tailMhs = (Mahasiswa) tail.data;
    if (inputMhs.getIpk() >= tailMhs.getIpk()) {
        addLast(input);
        return;
    }
}
```

- **Kondisi**: IPK dari mahasiswa baru lebih besar atau sama dengan IPK milik mahasiswa di `tail`.
- **Tindakan**: Panggil `addLast(input)`. Node baru akan disambungkan setelah `tail` lama, lalu `tail` diperbarui menunjuk ke node baru tersebut.

---

### E. Kasus 4: Penyisipan di Tengah List

```java
Node current = head;
while (current.next != null) {
    if (current.next.data instanceof Mahasiswa) {
        Mahasiswa nextMhs = (Mahasiswa) current.next.data;
        if (inputMhs.getIpk() <= nextMhs.getIpk()) {
            input.next = current.next;
            current.next = input;
            size++;
            return;
        }
    }
    current = current.next;
}
```

- **Kondisi**: IPK berada di antara elemen terdepan dan elemen terbelakang.
- **Tindakan**:
  1. Kita menelusuri list dari `head` menggunakan variabel pointer `current`.
  2. Pada setiap langkah, kita intip IPK node setelahnya (`current.next`).
  3. Ketika ditemukan bahwa `inputMhs.getIpk() <= nextMhs.getIpk()`:
     - Sambungkan penunjuk `next` milik node `input` ke `current.next`.
     - Ubah penunjuk `next` milik `current` ke node `input`.
     - Tambahkan nilai `size++`.
     - Keluar dari fungsi (`return`).

---

## 4. Simulasi Tracing / Contoh Kasus

Misalkan kita menyisipkan data secara berurutan:

1. **Sisip Budi (IPK: 3.50)**
   - List kosong $\rightarrow$ Masuk Kasus 1 (di awal).
   - **List**: `[Budi: 3.50]`

2. **Sisip Ani (IPK: 3.85)**
   - $3.85 \ge 3.50$ (tail) $\rightarrow$ Masuk Kasus 3 (di akhir).
   - **List**: `[Budi: 3.50] -> [Ani: 3.85]`

3. **Sisip Cici (IPK: 3.20)**
   - $3.20 \le 3.50$ (head) $\rightarrow$ Masuk Kasus 2 (di awal).
   - **List**: `[Cici: 3.20] -> [Budi: 3.50] -> [Ani: 3.85]`

4. **Sisip Eka (IPK: 3.65)**
   - $3.65 > 3.20$ (bukan head)
   - $3.65 < 3.85$ (bukan tail)
   - Iterasi $\rightarrow$ Berhenti di `current = Budi (3.50)` karena node setelahnya `Ani (3.85) >= 3.65`.
   - Disisipkan di antara Budi dan Ani $\rightarrow$ Masuk Kasus 4 (di tengah).
   - **List**: `[Cici: 3.20] -> [Budi: 3.50] -> [Eka: 3.65] -> [Ani: 3.85]`

---

## 5. Kompleksitas Waktu (Time Complexity)

- **Best Case ($O(1)$)**: Terjadi jika data disisipkan di awal atau akhir list (Kasus 1, 2, dan 3).
- **Worst Case ($O(N)$)**: Terjadi jika data disisipkan di posisi dekat akhir list (Kasus 4) karena perlu menelusuri $N$ node.
