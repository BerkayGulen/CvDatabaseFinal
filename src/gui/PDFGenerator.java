package gui;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.List;
import javax.swing.text.Document;

public class PDFGenerator {
    
    public PDFGenerator(){}

//    public static void main(String[] args) throws IOException {
//        List<String> education = new ArrayList<>();
//        education.add("Bachelor's degree in Computer Science from XYZ University");
//        education.add("Master's degree in Data Science from ABC University");
//
//        List<String> experiences = new ArrayList<>();
//        experiences.add("Software engineer at XYZ Company (2018-2020)");
//        experiences.add("Data scientist at ABC Company (2020-Present)");
//
//        List<String> skills = new ArrayList<>();
//        skills.add("Java");
//        skills.add("Python");
//        skills.add("SQL");
//
//        List<String> interests = new ArrayList<>();
//        interests.add("Machine learning");
//        interests.add("Natural language processing");
//        interests.add("Data visualization");
//
//        generatePDF("Ali", "ali@mail.com", "2142141", education, experiences, skills, interests);
//    }
    public PDDocument generatePDF(String name, String email, String phone, List<String> education, List<String> experiences, List<String> interests, List<String> skills) throws IOException {
        // Create a new PDF document
        PDDocument document = new PDDocument();

        // Create a new page
        PDPage page = new PDPage();
        document.addPage(page);

        // Get the font to use
        PDFont font = PDType1Font.TIMES_BOLD;
        PDFont font1 = PDType1Font.TIMES_ROMAN;

        // Create a new content stream for the page
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Set the font and font size
        contentStream.setFont(font, 12);
        float fontHeight = 12;
        int leading = 20;

        // Write some text to the page
        contentStream.beginText();

        float margin = 50;
        float yCoordinate = page.getCropBox().getUpperRightY() - margin;
        float startX = page.getCropBox().getLowerLeftX() + margin;
        float endX = page.getCropBox().getUpperRightX() - margin;
        contentStream.newLineAtOffset(startX, yCoordinate);

        contentStream.showText("Name:  ");
        contentStream.setFont(font1, 12);
        contentStream.showText(name);
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;

        contentStream.setFont(font, 12);
        contentStream.showText("Email:  ");
        contentStream.setFont(font1, 12);
        contentStream.showText(email);
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;

        contentStream.setFont(font, 12);
        contentStream.showText("Phone:  ");
        contentStream.setFont(font1, 12);
        contentStream.showText(phone);
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;

        contentStream.endText();
        contentStream.moveTo(startX, yCoordinate);
        contentStream.lineTo(endX, yCoordinate);
        contentStream.stroke();
        yCoordinate -= leading;

        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yCoordinate);

        contentStream.setFont(font, 12);
        contentStream.showText("Education:  ");
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;

        for (String edu : education) {
            contentStream.setFont(font1, 12);
            contentStream.showText(edu);
            contentStream.newLineAtOffset(0, -leading);
            yCoordinate -= leading;
        }

        contentStream.endText();
        contentStream.moveTo(startX, yCoordinate);
        contentStream.lineTo(endX, yCoordinate);
        contentStream.stroke();
        yCoordinate -= leading;

        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yCoordinate);
        contentStream.setFont(font, 12);

        contentStream.showText("Experiences:");
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;
        for (String exp : experiences) {
            contentStream.setFont(font1, 12);
            contentStream.showText(exp);
            contentStream.newLineAtOffset(0, -leading);
            yCoordinate -= leading;
        }

        contentStream.endText();
        contentStream.moveTo(startX, yCoordinate);
        contentStream.lineTo(endX, yCoordinate);
        contentStream.stroke();
        yCoordinate -= leading;

        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yCoordinate);

        contentStream.setFont(font, 12);
        contentStream.showText("Skills:");
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;
        for (String skill : skills) {
            contentStream.setFont(font1, 12);
            contentStream.showText(skill);
            contentStream.newLineAtOffset(0, -leading);
            yCoordinate -= leading;
        }

        contentStream.endText();
        contentStream.moveTo(startX, yCoordinate);
        contentStream.lineTo(endX, yCoordinate);
        contentStream.stroke();
        yCoordinate -= leading;

        contentStream.beginText();
        contentStream.newLineAtOffset(startX, yCoordinate);
        // Interests
        contentStream.setFont(font, 12);
        contentStream.showText("Interests:");
        contentStream.newLineAtOffset(0, -leading);
        yCoordinate -= leading;
        for (String interest : interests) {
            contentStream.setFont(font1, 12);
            contentStream.showText(interest);
            contentStream.newLineAtOffset(0, -leading);
            yCoordinate -= leading;
        }

        contentStream.endText();

        // Save and close the content stream
        contentStream.close();

        // Save the PDF document
        //document.save("triall2.pdf");
        System.out.println("PDF created");

        // Close the PDF document
        return  document;

        //document.close();
    }
}
