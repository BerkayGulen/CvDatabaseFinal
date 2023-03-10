/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.Panels;

import dbServices.CvOwnerDaoImpl;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import localDbServices.LocalDatabase;
import models.CvOwner;
import models.LocationModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author berka
 */
public class PanelEditv2 extends javax.swing.JFrame {

    private ArrayList<CvOwner> localCvOwners = LocalDatabase.getLocalDatabase();
    private CvOwnerDaoImpl dbHelper = new CvOwnerDaoImpl();
    private PanelHome pnlHome;
    private File cv;
    private CvOwner cvOwner = new CvOwner();
    private CvOwner currentPerson;

    private PanelHome pnlHome1;
    private PanelUpload pnlUpload;
    private PanelEditv2 pnlEditv2;
    private PanelInfo pnlInfo;
    private PanelGenerateCv pnlGenerateCv;

    /**
     * Creates new form PanelEditv2
     */
    public PanelEditv2(CvOwner currentPerson, LocationModel locationModel) {
        this.currentPerson = currentPerson;
        initComponents();
        txtName1.setText(currentPerson.getName());
        txtSurname1.setText(currentPerson.getSurname());
        txtDepartment.setText(currentPerson.getDepartment());
        txtFileChoose.setText(currentPerson.getCvFilePath());

        setSize(1200, 850);
        pnlNavBar.setSize(150, 700);
        setLocation(locationModel.getLocation());
        setSize(locationModel.getDimension());
        //setLocation(location);
        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

    }

    private PanelEditv2() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validateInput() {
        if (!localCvOwners.isEmpty()) {
            String name = txtName1.getText();
            String surname = txtSurname1.getText();
            String department = txtDepartment.getText();

            CvOwner prevPerson = localCvOwners.get(localCvOwners.size() - 1);
            if (prevPerson.getName().equals(name) && prevPerson.getSurname().equals(surname) && prevPerson.getDepartment().equals(department)) {
                return false;
            }
        }

        return true;

    }

    public void chooseFile() throws IOException {
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        cv = fileChooser.getSelectedFile();
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            if (currentPerson.getCvFilePath() == null) {
                currentPerson.setCvFilePath("src\\cvStorage\\" + cv.getName());
                cvOwner.setCvFilePath("src\\cvStorage\\" + cv.getName());
                txtFileChoose.setText("src\\cvStorage\\" + cv.getName());
                FileUtils.copyToDirectory(cv, new File("src\\cvStorage"));

            } else {
                FileUtils.delete(new File(currentPerson.getCvFilePath()));
                FileUtils.copyToDirectory(cv, new File("src\\cvStorage"));
                currentPerson.setCvFilePath("src\\cvStorage\\" + cv.getName());
                cvOwner.setCvFilePath("src\\cvStorage\\" + cv.getName());
                txtFileChoose.setText("src\\cvStorage\\" + cv.getName());
            }

        }
    }

    public boolean isEmpty() {
        String name = txtName1.getText().trim();
        String surname = txtSurname1.getText().trim();
        String department = txtDepartment.getText().trim();

        if (name.length() == 0 || surname.length() == 0) {
            return true;
        }
        return false;
    }

    public void save() throws IOException {
        if (isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cells are empty");

        } else {

            cvOwner.setId(currentPerson.getId());
            cvOwner.setName(txtName1.getText());
            cvOwner.setSurname(txtSurname1.getText());
            cvOwner.setDepartment(txtDepartment.getText());
            cvOwner.setCvFilePath(txtFileChoose.getText());
            dbHelper.update(cvOwner);

            JOptionPane.showMessageDialog(this, "Person Succesfully Edited");

        }

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
        txtDepartment = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFileChoose = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtSurname1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        btnFileChooser = new javax.swing.JButton();
        txtName1 = new javax.swing.JTextField();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pnlNavBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnHome = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDownload1 = new javax.swing.JButton();
        btnUpload1 = new javax.swing.JButton();
        btnEdit1 = new javax.swing.JButton();
        btnGenerate1 = new javax.swing.JButton();
        btnInfo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlContent.setVerifyInputWhenFocusTarget(false);

        txtDepartment.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtDepartment.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Department:");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 50));

        txtFileChoose.setEditable(false);
        txtFileChoose.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtFileChoose.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtFileChoose.setText("no file choosen");
        txtFileChoose.setPreferredSize(new java.awt.Dimension(170, 30));
        txtFileChoose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFileChooseActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Surname:");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 50));

        txtSurname1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtSurname1.setPreferredSize(new java.awt.Dimension(170, 50));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel5.setText("Name:");
        jLabel5.setPreferredSize(new java.awt.Dimension(80, 50));

        btnFileChooser.setBackground(new java.awt.Color(50, 50, 50));
        btnFileChooser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_upload_to_cloud_20px.png"))); // NOI18N
        btnFileChooser.setText("Choose CV File");
        btnFileChooser.setPreferredSize(new java.awt.Dimension(120, 30));
        btnFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFileChooserActionPerformed(evt);
            }
        });

        txtName1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtName1.setPreferredSize(new java.awt.Dimension(170, 50));
        txtName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtName1ActionPerformed(evt);
            }
        });

        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        jLabel3.setText("(Optional)");

        jLabel6.setText("(Optional)");

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(txtSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(txtFileChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlContentLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addGap(40, 40, 40)
                .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFileChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)))
                .addGap(60, 60, 60)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlNavBar.setBackground(new java.awt.Color(50, 50, 50));
        pnlNavBar.setPreferredSize(new java.awt.Dimension(200, 700));

        jLabel1.setBackground(new java.awt.Color(50, 50, 50));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/ieuLogo2.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(50, 50, 50));

        btnHome.setBackground(new java.awt.Color(50, 50, 50));
        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_home_50px.png"))); // NOI18N
        btnHome.setMaximumSize(new java.awt.Dimension(50, 50));
        btnHome.setMinimumSize(new java.awt.Dimension(50, 50));
        btnHome.setPreferredSize(new java.awt.Dimension(60, 60));
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
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
        btnGenerate1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerate1ActionPerformed(evt);
            }
        });

        btnInfo.setBackground(new java.awt.Color(50, 50, 50));
        btnInfo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_info_50px.png"))); // NOI18N
        btnInfo.setPreferredSize(new java.awt.Dimension(60, 60));
        btnInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInfoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnGenerate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpload1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDownload1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlNavBarLayout = new javax.swing.GroupLayout(pnlNavBar);
        pnlNavBar.setLayout(pnlNavBarLayout);
        pnlNavBarLayout.setHorizontalGroup(
            pnlNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavBarLayout.createSequentialGroup()
                .addGroup(pnlNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlNavBarLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1013, 1013, 1013))
        );
        pnlNavBarLayout.setVerticalGroup(
            pnlNavBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlNavBarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pnlNavBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(pnlNavBar, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtFileChooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFileChooseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFileChooseActionPerformed

    private void btnFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFileChooserActionPerformed
        try {
            // TODO add your handling code here:
            chooseFile();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanelUpload.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnFileChooserActionPerformed

    private void txtName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtName1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtName1ActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        try {
            // TODO add your handling code here:
            save();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(PanelEditv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:

        Point location = getLocation();

        this.dispose();
        pnlHome = new PanelHome(new LocationModel(getLocation(), getSize()));
        pnlHome.setVisible(true);
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload1ActionPerformed
        // TODO add your handling code here:
        Point location = getLocation();

        this.dispose();
        pnlUpload = new PanelUpload(new LocationModel(getLocation(), getSize()));
        pnlUpload.setVisible(true);
    }//GEN-LAST:event_btnUpload1ActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
        this.dispose();

        pnlInfo = new PanelInfo(new LocationModel(getLocation(), getSize()));
        pnlInfo.setVisible(true);
    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnGenerate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerate1ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        pnlGenerateCv = new PanelGenerateCv(new LocationModel(getLocation(), getSize()));
        pnlGenerateCv.setVisible(true);
    }//GEN-LAST:event_btnGenerate1ActionPerformed

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
            java.util.logging.Logger.getLogger(PanelEditv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelEditv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelEditv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelEditv2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelEditv2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDownload1;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnFileChooser;
    private javax.swing.JButton btnGenerate1;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnUpload1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlNavBar;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtFileChoose;
    private javax.swing.JTextField txtName1;
    private javax.swing.JTextField txtSurname1;
    // End of variables declaration//GEN-END:variables
}
