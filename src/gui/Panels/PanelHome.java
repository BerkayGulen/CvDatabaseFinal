/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui.Panels;

import dbServices.CvOwnerDaoImpl;
import gui.Filter;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import localDbServices.LocalDatabase;
import models.CvOwner;
import models.LocationModel;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author berka
 */
public class PanelHome extends javax.swing.JFrame {

    private ArrayList<CvOwner> localCvOwners = LocalDatabase.getLocalDatabase();
    private CvOwnerDaoImpl dbHelper = new CvOwnerDaoImpl();
    private PanelUpload pnlUpload;
    private PanelEditv2 pnlEditv2;
    private PanelInfo pnlInfo;
    private PanelGenerateCv pnlGenerateCv;
    private Filter filter = new Filter();

    /**
     * Creates new form PanelHome
     */
    public PanelHome(LocationModel locationModel) {
        initComponents();
        setSize(1200, 850);
        pnlNavBar.setSize(150, 700);
        setLocation(locationModel.getLocation());
        setSize(locationModel.getDimension());

        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        showData(localCvOwners);

    }

    private PanelHome() {
        initComponents();
        setSize(1200, 850);
        pnlNavBar.setSize(150, 700);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        showData(localCvOwners);
    }

    public void showData(ArrayList<CvOwner> cvOwners) {
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            Object[] row = new Object[4];
            for (CvOwner data : cvOwners) {
                row[0] = data.getName();
                row[1] = data.getSurname();
                row[2] = data.getDepartment();
                String newPath = "";
                if (data.getCvFilePath() != null) {
                    newPath = data.getCvFilePath().replace("src\\cvStorage\\", "");

                } else {

                }
                row[3] = newPath;
                model.addRow(row);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void searchByName() {
        String name = txtName.getText();
        System.out.println(name);
        ArrayList<CvOwner> searchedCvOwners = new ArrayList<>();
        if (name != null) {
            searchedCvOwners = filter.searchByName(name);
            if (searchedCvOwners != null) {
                showData(searchedCvOwners);
            } else {
                showData(localCvOwners);
                JOptionPane.showMessageDialog(this, "There is no records to show");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Cells are empty");

        }
    }

    public void searchByDepartment() {
        String department = txtDepartment.getText();
        System.out.println(department);
        ArrayList<CvOwner> searchedCvOwners = new ArrayList<>();
        if (department != null) {
            searchedCvOwners = filter.searchByDepartment(department);
            if (searchedCvOwners != null) {
                showData(searchedCvOwners);
            } else {
                showData(localCvOwners);
                JOptionPane.showMessageDialog(this, "There is no records to show");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Cells are empty");

        }
    }

    public void searchBySurname() {
        String surname = txtSurname1.getText();
        System.out.println(surname);
        ArrayList<CvOwner> searchedCvOwners = new ArrayList<>();
        if (surname != null) {
            searchedCvOwners = filter.searchBySurname(surname);
            if (searchedCvOwners != null) {
                showData(searchedCvOwners);
            } else {
                showData(localCvOwners);
                JOptionPane.showMessageDialog(this, "There is no records to show");

            }
        } else {
            JOptionPane.showMessageDialog(this, "Cells are empty");

        }
    }

    public void deleteData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        try {
            if (jTable1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Choose Cell To Delete");
            } else {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure?", "Warning", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    CvOwner deletedData = localCvOwners.get(jTable1.getSelectedRow());
                    model.removeRow(jTable1.getSelectedRow());
                    dbHelper.delete(deletedData);
                    JOptionPane.showMessageDialog(this, "Person Successfully Deleted");

                }

            }
        } catch (Exception e) {
                System.out.println(e.getMessage());
        }

    }

    public void downloadCv() throws IOException {
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Choose Cell To Download");
        } else {

            CvOwner selectedRow = localCvOwners.get(jTable1.getSelectedRow());
            if (selectedRow.getCvFilePath() == null) {
                JOptionPane.showMessageDialog(this, "There is no file to download");

            } else {
                File savedFile = new File(selectedRow.getCvFilePath());
                String path = "";

                String choosertitle = "";
                File directory;

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
                    path += fileChooser.getSelectedFile().toString();
                    directory = new File(path);

                    FileUtils.copyToDirectory(savedFile, directory);
                    JOptionPane.showMessageDialog(this, "File Saved");

                    System.out.println("getCurrentDirectory(): "
                            + path);
                } else {
                    System.out.println("No Selection ");
                }
            }

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
        pnlSearch = new javax.swing.JPanel();
        txtDepartment = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSearchDepartment = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnSearchName1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtSurname1 = new javax.swing.JTextField();
        btnSearchSurname1 = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
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

        pnlContent.setBackground(new java.awt.Color(187, 187, 187));

        txtDepartment.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtDepartment.setPreferredSize(new java.awt.Dimension(80, 50));
        txtDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDepartmentActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Department:");
        jLabel2.setPreferredSize(new java.awt.Dimension(80, 50));

        btnSearchDepartment.setBackground(new java.awt.Color(50, 50, 50));
        btnSearchDepartment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_Search_30px.png"))); // NOI18N
        btnSearchDepartment.setMaximumSize(new java.awt.Dimension(50, 50));
        btnSearchDepartment.setMinimumSize(new java.awt.Dimension(50, 50));
        btnSearchDepartment.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSearchDepartment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDepartmentActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setText("Name:");
        jLabel3.setPreferredSize(new java.awt.Dimension(80, 50));

        txtName.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtName.setPreferredSize(new java.awt.Dimension(80, 50));
        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        btnSearchName1.setBackground(new java.awt.Color(50, 50, 50));
        btnSearchName1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_Search_30px.png"))); // NOI18N
        btnSearchName1.setMaximumSize(new java.awt.Dimension(50, 50));
        btnSearchName1.setMinimumSize(new java.awt.Dimension(50, 50));
        btnSearchName1.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSearchName1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchName1ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel4.setText("Surname:");
        jLabel4.setPreferredSize(new java.awt.Dimension(80, 50));

        txtSurname1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        txtSurname1.setPreferredSize(new java.awt.Dimension(80, 50));
        txtSurname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSurname1ActionPerformed(evt);
            }
        });

        btnSearchSurname1.setBackground(new java.awt.Color(50, 50, 50));
        btnSearchSurname1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/icons/icons8_Search_30px.png"))); // NOI18N
        btnSearchSurname1.setMaximumSize(new java.awt.Dimension(50, 50));
        btnSearchSurname1.setMinimumSize(new java.awt.Dimension(50, 50));
        btnSearchSurname1.setPreferredSize(new java.awt.Dimension(50, 50));
        btnSearchSurname1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSurname1ActionPerformed(evt);
            }
        });

        btnOpen.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        btnReset.setText("Reset Filter");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addGap(230, 230, 230)
                                .addComponent(btnSearchName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(txtSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addGap(260, 260, 260)
                                .addComponent(btnSearchSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addGap(290, 290, 290)
                                .addComponent(btnSearchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlSearchLayout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlSearchLayout.createSequentialGroup()
                        .addGap(610, 610, 610)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSearchLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchSurname1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDepartment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Surname", "Department", "Cv File"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pnlContentLayout = new javax.swing.GroupLayout(pnlContent);
        pnlContent.setLayout(pnlContentLayout);
        pnlContentLayout.setHorizontalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
        );
        pnlContentLayout.setVerticalGroup(
            pnlContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlContentLayout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        btnDownload1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDownload1ActionPerformed(evt);
            }
        });

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
        btnEdit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEdit1ActionPerformed(evt);
            }
        });

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
                .addContainerGap(8, Short.MAX_VALUE))
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
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlNavBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlNavBar, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
            .addComponent(pnlContent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnSearchDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDepartmentActionPerformed
        // TODO add your handling code here:
        searchByDepartment();
    }//GEN-LAST:event_btnSearchDepartmentActionPerformed

    private void btnSearchName1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchName1ActionPerformed
        // TODO add your handling code here:
        searchByName();
    }//GEN-LAST:event_btnSearchName1ActionPerformed

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
        searchByName();

    }//GEN-LAST:event_txtNameActionPerformed

    private void btnSearchSurname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSurname1ActionPerformed
        // TODO add your handling code here:
        searchBySurname();
    }//GEN-LAST:event_btnSearchSurname1ActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
        deleteData();


    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnDownload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDownload1ActionPerformed
        try {
            // TODO add your handling code here:
            downloadCv();
        } catch (IOException ex) {
            Logger.getLogger(PanelHome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnDownload1ActionPerformed

    private void btnUpload1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpload1ActionPerformed
        // TODO add your handling code here:

        this.dispose();

        pnlUpload = new PanelUpload(new LocationModel(getLocation(), getSize()));
        pnlUpload.setVisible(true);

    }//GEN-LAST:event_btnUpload1ActionPerformed

    private void btnEdit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEdit1ActionPerformed
        // TODO add your handling code here:
        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Choose Cell To Edit");
        } else {
            CvOwner deletedData = localCvOwners.get(jTable1.getSelectedRow());

            this.dispose();
            pnlEditv2 = new PanelEditv2(deletedData, new LocationModel(getLocation(), getSize()));
            pnlEditv2.setVisible(true);
            // getContentPane().removeAll();
            //setContentPane(new PanelEdit(deletedData));
            // getContentPane().revalidate(); //IMPORTANT
            //  getContentPane().repaint();

        }


    }//GEN-LAST:event_btnEdit1ActionPerformed

    private void btnInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInfoActionPerformed
        // TODO add your handling code here:
        this.dispose();

        pnlInfo = new PanelInfo(new LocationModel(getLocation(), getSize()));
        pnlInfo.setVisible(true);

    }//GEN-LAST:event_btnInfoActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        try {
            if (jTable1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Choose Cell To Open");
            } else {
                CvOwner cvOwner = localCvOwners.get(jTable1.getSelectedRow());
                Desktop.getDesktop().open(new File(cvOwner.getCvFilePath()));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "There is no file to open");

        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnGenerate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerate1ActionPerformed
        // TODO add your handling code here:
        this.dispose();

        pnlGenerateCv = new PanelGenerateCv(new LocationModel(getLocation(), getSize()));
        pnlGenerateCv.setVisible(true);
    }//GEN-LAST:event_btnGenerate1ActionPerformed

    private void txtDepartmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDepartmentActionPerformed
        // TODO add your handling code here:
        searchByDepartment();
    }//GEN-LAST:event_txtDepartmentActionPerformed

    private void txtSurname1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSurname1ActionPerformed
        // TODO add your handling code here:
        searchBySurname();
    }//GEN-LAST:event_txtSurname1ActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        showData(localCvOwners);
    }//GEN-LAST:event_btnResetActionPerformed

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
            java.util.logging.Logger.getLogger(PanelHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelHome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelHome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDownload1;
    private javax.swing.JButton btnEdit1;
    private javax.swing.JButton btnGenerate1;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnInfo;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSearchDepartment;
    private javax.swing.JButton btnSearchName1;
    private javax.swing.JButton btnSearchSurname1;
    private javax.swing.JButton btnUpload1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel pnlContent;
    private javax.swing.JPanel pnlNavBar;
    private javax.swing.JPanel pnlSearch;
    private javax.swing.JTextField txtDepartment;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSurname1;
    // End of variables declaration//GEN-END:variables
}
