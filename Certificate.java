package skill.forge;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import org.json.JSONObject;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Certificate {
    private String certificateID;
    private Student student;
    private Course course;
    private LocalDate issueDate;
    private String nameOfFile;
    private JsonDataBaseManager db;
    public Certificate(String certificateID, Student student, Course course, JsonDataBaseManager db){
        this.certificateID = certificateID;
        this.course = course;
        this.issueDate = LocalDate.now();
        this.student = student;
        this.nameOfFile = "Certificate-" + this.student.getUserID();
        this.db = db;
    }
    public void pdfCertificateCreator(){
        String nameOfFile = this.nameOfFile + ".pdf";
        try {
            PdfWriter pdfwriter = new PdfWriter(nameOfFile);
            PdfDocument pdfdocument = new PdfDocument(pdfwriter);
            pdfdocument.setDefaultPageSize(PageSize.A4.rotate());
            Document document = new Document(pdfdocument);
            document.setMargins(40,40,40,40);

            Paragraph title = new Paragraph(this.course.getCourseName() + " Certificate")
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD))
                    .setFontSize(36)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(30);
            document.add(title);

            Paragraph paragraph = new Paragraph("Certificate " + this.certificateID +" is awarded to "+ this.student.getUsername()+ " with ID "+ this.student.getUserID() + " in the date " + this.issueDate.toString())
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA))
                    .setFontSize(20)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(25);
            document.add(paragraph);

            document.close();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found Error");
        }catch (IOException e){
            System.out.println("Error in creating font in pdf");
        }
        JOptionPane.showMessageDialog(null, "PDF File Created Successfully");
    }
    public void studentAddCertificate(){

    }
    public void jsonCertificateCreator(){
        String nameOfFile = this.nameOfFile + ".json";
        try{
            JSONObject certificate = new JSONObject();
            certificate.put("userID", this.student.getUserID());
            certificate.put("username", this.student.getUsername());
            certificate.put("course", this.course.getCourseName());
            certificate.put("courseID", this.course.getCourseID());
            certificate.put("certificateID", this.certificateID);
            certificate.put("issueDate", this.issueDate);
            FileWriter file = new FileWriter(nameOfFile);
            file.write(certificate.toString());
            file.close();
        }catch (IOException e){
            System.out.println("File Not Found Error");
        }
        JOptionPane.showMessageDialog(null, "JSON File Created Successfully");
    }
    
    public String getCertificateID() {
        return certificateID;
    }
    public void setCertificateID(String certificateID) {
        this.certificateID = certificateID;
    }
    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public Course getCourse() {
        return course;
    }
    public void setCourse(Course course) {
        this.course = course;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }
    public String getNameOfFile() {
        return nameOfFile;
    }
    public void setNameOfFile(String nameOfFile) {
        this.nameOfFile = nameOfFile;
    }
}
