public class Main {

    public static void main(String[] args) throws Exception {

        PdfReader reader = new PdfReader(
                "putusan_1012_pid.sus_2026_pn_sby_20260928093625.pdf"
        );

        System.out.println("=== HASIL DATA PUTUSAN ===");

        System.out.println("Nama           : " + reader.ambilNama());
        System.out.println("Umur           : " + reader.ambilUmur());
        System.out.println("Jenis Kelamin  : " + reader.ambilJenisKelamin());
        System.out.println("Tempat Lahir   : " + reader.ambilTempatLahir());
        System.out.println("Tanggal Lahir  : " + reader.ambilTanggalLahir());
        System.out.println("Kebangsaan     : " + reader.ambilKebangsaan());
        System.out.println("Pekerjaan      : " + reader.ambilPekerjaan());
        System.out.println("Pendidikan     : " + reader.ambilPendidikan());

        B3Putusan<String> putusan = new B3Putusan<>(
                reader.ambilNama(),
                Integer.parseInt(reader.ambilUmur()),
                reader.ambilJenisKelamin(),
                reader.ambilTempatLahir(),
                reader.ambilTanggalLahir(),
                reader.ambilKebangsaan(),
                reader.ambilPekerjaan(),
                reader.ambilPendidikan(),
                "Data Putusan"
        );

        putusan.tampilkan();

        putusan.tampilkan();
    }
}