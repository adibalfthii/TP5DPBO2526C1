package icip_java;

public class Person {
    private String id;
    private String nama;
    private String tahunLahir;
    private String kategori;
    private String status;

    public Person(String id, String nama, String tahunLahir, String kategori, String status) {
        this.id = id;
        this.nama = nama;
        this.tahunLahir = tahunLahir;
        this.kategori = kategori;
        this.status = status;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getTahunLahir() { return tahunLahir; }
    public void setTahunLahir(String tahunLahir) { this.tahunLahir = tahunLahir; }

    public String getKategori() { return kategori; }
    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}