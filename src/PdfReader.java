import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PdfReader {

    private String teks;

    public PdfReader(String namaFile) throws Exception {
        File file = new File("data/" + namaFile);

        PDDocument document = Loader.loadPDF(file);

        PDFTextStripper stripper = new PDFTextStripper();

        teks = stripper.getText(document);

        document.close();
    }

    public String ambilNama() {
        Pattern pattern = Pattern.compile(
                "Nama lengkap\\s*:\\s*([\\s\\S]*?);"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).replaceAll("\\s+", " ").trim();
        }

        return "Tidak ditemukan";
    }

    public String ambilUmur() {
        Pattern pattern = Pattern.compile(
                "Umur/ Tanggal lahir\\s*:\\s*(\\d+) Tahun"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "Tidak ditemukan";
    }

    public JenisKelamin ambilJenisKelamin() {
        Pattern pattern = Pattern.compile(
                "Jenis kelamin\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            String hasil = matcher.group(1).trim();

            if (hasil.equalsIgnoreCase("Laki-laki")) {
                return JenisKelamin.LAKI_LAKI;
            }

            if (hasil.equalsIgnoreCase("Perempuan")) {
                return JenisKelamin.PEREMPUAN;
            }
        }

        return null;
    }

    public String ambilTempatLahir() {
        Pattern pattern = Pattern.compile(
                "Tempat lahir\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    public String ambilTanggalLahir() {
        Pattern pattern = Pattern.compile(
                "Umur/ Tanggal lahir\\s*:\\s*\\d+ Tahun\\s*/\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    public String ambilKebangsaan() {
        Pattern pattern = Pattern.compile(
                "Kebangsaan\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    public String ambilPekerjaan() {
        Pattern pattern = Pattern.compile(
                "Pekerjaan\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }

    public String ambilPendidikan() {
        Pattern pattern = Pattern.compile(
                "Pendidikan\\s*:\\s*([^;]+)"
        );

        Matcher matcher = pattern.matcher(teks);

        if (matcher.find()) {
            return matcher.group(1).trim();
        }

        return "Tidak ditemukan";
    }
}