
import dbServices.CvOwnerDaoImpl;
import org.apache.commons.io.FileUtils;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import localDbServices.LocalDatabase;
import models.CvOwner;

public class Main {

    public static void main(String[] args) throws IOException, SQLException {

        Scanner input = new Scanner(System.in);
        File cv;
        CvOwner cvOwner = new CvOwner();
        CvOwnerDaoImpl dbHelper = new CvOwnerDaoImpl();
        ArrayList<CvOwner> localCvOwners = LocalDatabase.getLocalDatabase();
        System.out.println("********************************************");
        System.out.println("Before Add");
        System.out.println("********************************************");

        for (CvOwner person : localCvOwners) {
            person.display();
        }

        System.out.println("********************************************");
        System.out.println("ADD");
        System.out.println("********************************************");

        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(parentFrame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            cv = fileChooser.getSelectedFile();
            FileUtils.copyToDirectory(cv, new File("cvStorage"));
            cvOwner.setCvFilePath("cvStorage\\" + cv.getName());
//            System.out.println("Save as file: " + x.getPath());
        }

        System.out.print("Enter name: ");
        cvOwner.setName(input.nextLine());
        System.out.print("Enter Surname: ");
        cvOwner.setSurname(input.nextLine());
        System.out.print("Enter department: ");
        cvOwner.setDepartment(input.nextLine());
        dbHelper.add(cvOwner);

        System.out.println("********************************************");
        System.out.println("After Add");
        System.out.println("********************************************");

        for (CvOwner person : localCvOwners) {
            person.display();
        }

        //dbHelper.deleteAll();
        System.out.println("********************************************");
        System.out.println("After remove all");
        System.out.println("********************************************");

        for (CvOwner person : localCvOwners) {
            person.display();
        }

        System.out.println("********************************************");
        System.out.println("Add again");
        System.out.println("********************************************");
        //dbHelper.add(cvOwner);

        for (CvOwner person : localCvOwners) {
            person.display();
        }
        dbHelper.deleteAll();

//
//        cvOwner.display();
//        Desktop.getDesktop().open(new File(cvOwner.getCvFilePath()));
        parentFrame.dispose();

    }
}
