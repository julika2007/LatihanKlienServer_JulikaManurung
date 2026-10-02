package app;

import java.util.ArrayList;
import java.util.List;
import model.Barang;

public class UjiBarang {
    private static int lulus = 0;

    private static void cek(String nama, boolean kondisi) {
        if (!kondisi) {
            throw new AssertionError("GAGAL: " + nama);
        }
        lulus++;
        System.out.println("LULUS " + lulus + ": " + nama);
    }

    private static void ditolak(String nama, Runnable aksi) {
        boolean gagal = false;
        try {
            aksi.run();
        } catch (IllegalArgumentException e) {
            gagal = true;
        }
        cek(nama, gagal);
    }

    public static void main(String[] args) {
        final Barang b = new Barang(" BRG-001 ", " Keyboard USB ", 10);
        cek("Constructor dan trim", b.getKode().equals("BRG-001")
                && b.getNama().equals("Keyboard USB")
                && b.getJumlahTersedia() == 10);
        ditolak("Kode null", new Runnable() {
            public void run() { new Barang(null, "Mouse", 1); }
        });
        ditolak("Kode kosong", new Runnable() {
            public void run() { new Barang("   ", "Mouse", 1); }
        });
        ditolak("Nama null", new Runnable() {
            public void run() { new Barang("B2", null, 1); }
        });
        ditolak("Nama kosong", new Runnable() {
            public void run() { new Barang("B2", "   ", 1); }
        });
        ditolak("Jumlah awal negatif", new Runnable() {
            public void run() { new Barang("B2", "Mouse", -1); }
        });
        cek("Jumlah awal nol", new Barang("B2", "Mouse", 0)
                .getJumlahTersedia() == 0);
        b.pinjam(3);
        cek("Pinjam valid", b.getJumlahTersedia() == 7);
        b.kembalikan(2);
        cek("Kembali valid", b.getJumlahTersedia() == 9);
        ditolak("Pinjam nol", new Runnable() {
            public void run() { b.pinjam(0); }
        });
        ditolak("Pinjam negatif", new Runnable() {
            public void run() { b.pinjam(-1); }
        });
        ditolak("Pinjam melebihi tersedia", new Runnable() {
            public void run() { b.pinjam(100); }
        });
        cek("Pinjam gagal menjaga keadaan", b.getJumlahTersedia() == 9);
        ditolak("Kembali nol", new Runnable() {
            public void run() { b.kembalikan(0); }
        });
        ditolak("Kembali negatif", new Runnable() {
            public void run() { b.kembalikan(-2); }
        });
        cek("Kembali gagal menjaga keadaan", b.getJumlahTersedia() == 9);
        final Barang besar = new Barang("MAX", "Barang", Integer.MAX_VALUE);
        ditolak("Overflow pengembalian", new Runnable() {
            public void run() { besar.kembalikan(1); }
        });
        cek("Overflow menjaga keadaan", besar.getJumlahTersedia()
                == Integer.MAX_VALUE);
        List<Barang> daftar = new ArrayList<Barang>();
        daftar.add(b);
        b.pinjam(9);
        cek("Pinjam seluruh tersedia", b.getJumlahTersedia() == 0);
        cek("List menyimpan referensi yang sama",
                daftar.get(0).getJumlahTersedia() == 0);
        System.out.println("TOTAL: " + lulus + " pengujian lulus.");
    }
}