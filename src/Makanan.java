package src;

import java.time.LocalDateTime;

public class Makanan {
    private int id;
    private String tanggalAcc;
    private String nama;
    private String porsi;
    private String imagePath;

    public Makanan(String nama, String porsi, String imagePath) {
        this.nama = nama;
        this.porsi = porsi;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public String getPorsi() {
        return porsi;
    }

    public String getImagePath() {
        return imagePath;
    }
     public void accMakanan() {
        this.tanggalAcc = LocalDateTime.now().toString();  // Menyimpan waktu ACC
    }

    public String getTanggalAcc() {
        return tanggalAcc;
    }

    public void setTanggalAcc(String tanggalAcc) {
        this.tanggalAcc = tanggalAcc;
    }
}
