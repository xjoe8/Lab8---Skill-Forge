package youssef.lab8;
public class Lab8 {
    public static void main(String[] args) {
        Student s = new Student("US-9006","xjoe8");
        Course c = new Course("CS-123", "Java");
        Certificate certificate = new Certificate("CF-001", s, c);
        certificate.pdfCertificateCreator();
        certificate.jsonCertificateCreator();
    }
}
