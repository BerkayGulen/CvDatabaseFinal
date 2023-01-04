/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.Panels;

import dbServices.CvOwnerDaoImpl;
import gui.PDFGenerator;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.text.Document;
import models.CvOwner;
import models.LocationModel;
import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author berka
 */
public class PanelGenerateCv extends javax.swing.JFrame {

    private PanelHome pnlHome;
    private PanelUpload pnlUpload;
    private PanelInfo pnlInfo;

    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String interests;
    private String education;
    private String experiences;
    private String skills;
    private String fileName;
    private String department;
    private CvOwnerDaoImpl dbHelper = new CvOwnerDaoImpl();

    private ArrayList<String> interestsList;
    private ArrayList<String> educationList;
    private ArrayList<String> experincesList;
    private ArrayList<String> skillList;

    /**
     * Creates new form PanelGenerateCv
     */
    public PanelGenerateCv(LocationModel locationModel) {
        initComponents();
        setSize(1200, 850);
        //pnlNavBar.setSize(150, 700);
        setLocation(locationModel.getLocation());
        setSize(locationModel.getDimension());
    }

    private PanelGenerateCv() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean isEmpty() {
        name = txtName.getText();
        surname = txtSurname.getText();
        email = txtEmail.getText();
        phoneNumber = txtPhoneNumber.getText();
        interests = txtInterests.getText();
        education = txtEducation.getText();
        experiences = txtExperiences.getText();
        skills = txtSkills.getText();
        fileName = txtFileName.getText();
        department = txtDepartment.getText();
        String[] arr = {name, surname, email, phoneNumber, interests, education, experiences, skills, fileName,department};
        for (String text : arr) {
            if (text == null) {
                return true;
            } else if (text.trim().length() == 0) {
                return true;
            }
        }
        return false;

    }

    public void generateCv() {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cells are empty");

        } else {
            PDFGenerator generator = new PDFGenerator();
            name = txtName.getText();
            surname = txtSurname.getText();
            email = txtEmail.getText();
            phoneNumber = txtPhoneNumber.getText();
            interests = txtInterests.getText();
            education = txtEducation.getText();
            experiences = txtExperiences.getText();
            skills = txtSkills.getText();
            department = txtDepartment.getText();
            

            educationList = stringTolist(education);
            experincesList = stringTolist(experiences);
            interestsList = stringTolist(interests);
            skillList = stringTolist(skills);

            String nameSurname = "";
            nameSurname += name + " " + surname;

            try {
                PDDocument document = generator.generatePDF(nameSurname, email, phoneNumber, educationList, experincesList, interestsList, skillList);
                String path = "";

                String choosertitle = "";

                JFrame parentFrame = new JFrame();
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new java.io.File("."));
                fileChooser.setDialogTitle(choosertitle);
                fileChooser.setDialogTitle("Specify a file to save");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(parentFrame);

                fileChooser.setAcceptAllFileFilterUsed(false);
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    //path += fileChooser.getCurrentDirectory().toString();
                    fileName = txtFileName.getText();
                    if (fileName.contains(".pdf")) {
                        path += fileChooser.getSelectedFile().toString() + "\\" + fileName;

                    } else {
                        path += fileChooser.getSelectedFile().toString() + "\\" + fileName + ".pdf";
                        fileName += ".pdf";

                    }
                    System.out.println(path);
                    System.out.println(path);
                    document.save(new File(path));
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    int dialogResult = JOptionPane.showConfirmDialog(null, "File Saved. Do you want to add this person to database", "Warning", dialogButton);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                      
                        dbHelper.add(new CvOwner(name, surname, department, "src\\cvStorage\\" + fileName));
                        document.save(new File("src\\cvStorage\\"+fileName));

                        JOptionPane.showMessageDialog(this, "Person Succesfully Added");

                    }
                    document.close();

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public ArrayList<String> stringTolist(String text) {
        String elements[] = text.split(",");
        List<String> fixedLenghtList = Arrays.asList(elements);
        ArrayList<String> stringList = new ArrayList<String>(fixedLenghtList);

        return stringList;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlContent = new javax.swing.JPanel();
        txtSurname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtPhoneNumber = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnGenerate = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtEducation = new javax.swing.JTextField();
        txtExperiences = new javax.swing.JTextField();
        txtSkills = new javax.swing.JTextField();
        txtInterests = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFileName = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        txtDepartment = new javax.swing.JTextField();
        pnlNavBar2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnHome2 = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDownload1 = new javax.swing.JButton();
        btnUpload1 = new javax.swing.JButton();
        btnEdit1 = new javax.swing.JButton();
        btnGenerate1 = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContent.setVerifyInputWhenFocusTarget(false);

        txtSurname.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtSurname.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("E-mail:");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 50));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Surname:");
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 50));

        txtEmail.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtEmail.setPreferredSize(new java.awt.Dimension(170, 50));
        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setText("Name:");
        jLabel6.setPreferredSize(new java.awt.Dimension(80, 50));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel7.setText("Interests:");
        jLabel7.setPreferredSize(new java.awt.Dimension(80, 50));

        txtPhoneNumber.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtPhoneNumber.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel8.setText("Phone number:");
        jLabel8.setPreferredSize(new java.awt.Dimension(80, 50));

        txtName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtName.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel9.setText("Education: ");
        jLabel9.setPreferredSize(new java.awt.Dimension(80, 50));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel10.setText("Experiences:");
        jLabel10.setPreferredSize(new java.awt.Dimension(80, 50));

        btnGenerate.setBackground(new java.awt.Color(50, 50, 50));
        btnGenerate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        btnGenerate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_pdf_70px.png"))); // NOI18N
        btnGenerate.setText("Generate");
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel11.setText("Skills:");
        jLabel11.setPreferredSize(new java.awt.Dimension(80, 50));

        txtEducation.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtEducation.setPreferredSize(new java.awt.Dimension(170, 50));

        txtExperiences.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtExperiences.setPreferredSize(new java.awt.Dimension(170, 50));

        txtSkills.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtSkills.setPreferredSize(new java.awt.Dimension(170, 50));

        txtInterests.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtInterests.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Enter pdf file name:");
        jLabel12.setPreferredSize(new java.awt.Dimension(80, 50));

        txtFileName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtFileName.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel13.setText("(please write ',' between interests)");

        jLabel14.setText("(please write ',' between Educations)");

        jLabel15.setText("(please write ',' between Skills)");

        jLabel16.setText("(please write ',' between Experiences)");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText(".pdf");

        jLabel18.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel18.setText("Department:");
        jLabel18.setPreferredSize(new java.awt.Dimension(80, 50));

        txtDepartment.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtDepartment.setPreferredSize(new java.awt.Dimension(170, 50));
        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(txtInterests, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel13))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(txtEducation, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(txtExperiences, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(txtSkills, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel15))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlContentLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(btnGenerate))
                            .addComponent(jLabel17))))
                .addGap(101, 101, 101))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSurname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtInterests, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel13)))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtEducation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel14)))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtExperiences, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel16)))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(txtSkills, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel15)))
                .addGap(18, 18, 18)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel17)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlNavBar2.setBackground(new java.awt.Color(50, 50, 50));
        pnlNavBar2.setPreferredSize(new java.awt.Dimension(200, 700));

        jLabel3.setBackground(new java.awt.Color(50, 50, 50));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/ieuLogo2.png"))); // NOI18N

        jPanel4.setBackground(new java.awt.Color(50, 50, 50));

        btnHome2.setBackground(new java.awt.Color(50, 50, 50));
        btnHome2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_home_50px.png"))); // NOI18N
        btnHome2.setMaximumSize(new java.awt.Dimension(50, 50));
        btnHome2.setMinimumSize(new java.awt.Dimension(50, 50));
        btnHome2.setPreferredSize(new java.awt.Dimension(60, 60));
        btnHome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHome2ActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(50, 50, 50));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_delete_50px.png"))); // NOI18N
        btnDelete.setPreferredSize(new java.awt.Dimension(60, 60));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnDownload1.setBackground(new java.awt.Color(50, 50, 50));
        btnDownload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_Download_50px_2.png"))); // NOI18N
        btnDownload1.setPreferredSize(new java.awt.Dimension(60, 60));

        btnUpload1.setBackground(new java.awt.Color(50, 50, 50));
        btnUpload1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_upload_50px.png"))); // NOI18N
        btnUpload1.setPreferredSize(new java.awt.Dimension(60, 60));
        btnUpload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpload1ActionPerformed(evt);
            }
        });

        btnEdit1.setBackground(new java.awt.Color(50, 50, 50));
        btnEdit1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_edit_50px.png"))); // NOI18N
        btnEdit1.setPreferredSize(new java.awt.Dimension(60, 60));

        btnGenerate1.setBackground(new java.awt.Color(50, 50, 50));
        btnGenerate1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_Document_50px_1.png"))); // NOI18N
        btnGenerate1.setPreferredSize(new java.awt.Dimension(60, 60));

        btnInfo.setBackground(new java.awt.Color(50, 50, 50));
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_info_50px.png"))); // NOI18N
        btnInfo.setPreferredSize(new java.awt.Dimension(60, 60));
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDownload1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnHome2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDownload1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNavBar2Layout = new javax.swing.GroupLayout(pnlNavBar2);
        pnlNavBar2.setLayout(pnlNavBar2Layout);
        pnlNavBar2Layout.setHorizontalGroup(
            pnlNavBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavBar2Layout.createSequentialGroup()
                .addGroup(pnlNavBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNavBar2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1013, 1013, 1013))
        );
        pnlNavBar2Layout.setVerticalGroup(
            pnlNavBar2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavBar2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNavBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlNavBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE))
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHome2ActionPerformed
        // TODO add your handling code here:

        this.dispose();

        pnlHome = new PanelHome(new LocationModel(getLocation(), getSize()));
        pnlHome.setVisible(true);
    }//GEN-LAST:event_btnHome2ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload1ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        pnlUpload = new PanelUpload(new LocationModel(getLocation(), getSize()));
        pnlUpload.setVisible(true);
    }//GEN-LAST:event_btnUpload1ActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        this.dispose();

        pnlInfo = new PanelInfo(new LocationModel(getLocation(), getSize()));
        pnlInfo.setVisible(true);
    }//GEN-LAST:event_btnInfoActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed
        // TODO add your handling code here:
        generateCv();
    }//GEN-LAST:event_btnGenerateActionPerformed

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDepartmentActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelGenerateCv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelGenerateCv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelGenerateCv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelGenerateCv.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelGenerateCv().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDownload1;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JButton btnGenerate1;
    private javax.swing.JButton btnHome2;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnUpload1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlNavBar2;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtEducation;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtExperiences;
    private javax.swing.JTextField txtFileName;
    private javax.swing.JTextField txtInterests;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhoneNumber;
    private javax.swing.JTextField txtSkills;
    private javax.swing.JTextField txtSurname;
    // End of variables declaration//GEN-END:variables

}
