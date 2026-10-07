public class B3Putusan<T> {

    private String nama;
    private int umur;
    private JenisKelamin jenisKelamin;
    private String tempatLahir;
    private String tanggalLahir;
    private String kebangsaan;
    private String pekerjaan;
    private String pendidikan;
    private T data;

    public B3Putusan(
            String nama,
            int umur,
            JenisKelamin jenisKelamin,
            String tempatLahir,
            String tanggalLahir,
            String kebangsaan,
            String pekerjaan,
            String pendidikan,
            T data
    ) {
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.kebangsaan = kebangsaan;
        this.pekerjaan = pekerjaan;
        this.pendidikan = pendidikan;
        this.data = data;
    }

    public void tampilkan() {
        System.out.println("=== DATA B3 PUTUSAN ===");
        System.out.println("Nama           : " + nama);
        System.out.println("Umur           : " + umur);
        System.out.println("Jenis Kelamin  : " + jenisKelamin);
        System.out.println("Tempat Lahir   : " + tempatLahir);
        System.out.println("Tanggal Lahir  : " + tanggalLahir);
        System.out.println("Kebangsaan     : " + kebangsaan);
        System.out.println("Pekerjaan      : " + pekerjaan);
        System.out.println("Pendidikan     : " + pendidikan);
        System.out.println("Data           : " + data);
    }
}