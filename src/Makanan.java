package src;
public class Makanan {
    private String nama;
    private String porsi;
    private String imagePath;

    public Makanan(String nama, String porsi, String imagePath) {
        this.nama = nama;
        this.porsi = porsi;
        this.imagePath = imagePath;
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
}
