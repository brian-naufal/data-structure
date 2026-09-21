# Browser History (Doubly Linked List) - Java GUI

Struktur project:

```
src/browserhistory/
├── Node.java              # Model: satu node dalam doubly linked list
├── BrowserHistory.java    # Logika inti: visit, back, forward
├── BrowserHistoryGUI.java # Tampilan (Swing), memakai BrowserHistory
└── Main.java              # Entry point aplikasi
```

## Cara menjalankan

Dari folder `src`:

```bash
cd src
javac browserhistory/*.java
java browserhistory.Main
```

## Kenapa dipisah begini?

- **Node** = representasi data murni (model), tidak punya logika apa pun.
- **BrowserHistory** = logika doubly linked list (visit/back/forward),
  sama sekali tidak tahu soal Swing/GUI. Ini membuatnya bisa dipakai
  ulang untuk interface lain (CLI, web, dll) tanpa perlu diubah.
- **BrowserHistoryGUI** = hanya urusan tampilan, memanggil method
  dari `BrowserHistory` dan me-refresh tampilan setelah setiap aksi.
- **Main** = titik masuk aplikasi, menyiapkan homepage lalu membuka GUI.

Pemisahan ini mengikuti prinsip **Separation of Concerns** dan mirip
pola **MVC (Model-View-Controller)** sederhana: Node+BrowserHistory
sebagai Model, BrowserHistoryGUI sebagai View, dan Main sebagai
pemicu/controller awal.
