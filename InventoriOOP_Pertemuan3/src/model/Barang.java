
package model;

public class Barang {
    private String kode;
    private String nama;
    private int jumlahTersedia;
    
    public Barang(String kode,String nama, int jumlahTersedia){
        if (kode == null || kode.trim().isEmpty()){
            throw new IllegalArgumentException("kode barang wajib diisi.");
        }
        if (nama == null || nama.trim().isEmpty()){
        throw new IllegalArgumentException("nama barang wajib diisi.");
    }
        if (jumlahTersedia < 0){
            throw new IllegalArgumentException("Jumlah awal tidak boleh negatif.");
        }
        this.kode = kode.trim();
        this.nama = nama.trim();
        this.jumlahTersedia = jumlahTersedia;
    }
    public String getKode() {
        return kode;
    }
    public String getNama(){
        return nama;
    }
    public int getJumlahTersedia(){
        return jumlahTersedia;
    }
    public void pinjam(int jumlah){
        if (jumlah <= 0){
            throw new IllegalArgumentException("Jumlah pinjam harus positif.");
        }
        if (jumlah > jumlahTersedia){
            throw new IllegalArgumentException("Barang tersedia tidak mencukupi.");
        }
        jumlahTersedia -= jumlah;
    }
    public void kembalikan(int jumlah){
        if (jumlah <= 0){
            throw new IllegalArgumentException("Jumlah kembali harus Positif.");
        }
        if (jumlah > Integer.MAX_VALUE - jumlahTersedia){
            throw new IllegalArgumentException("Jumlah melebihi kapasitas int.");
        }
        jumlahTersedia += jumlah;
    }
}
