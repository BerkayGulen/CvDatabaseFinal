/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui;

import java.util.ArrayList;
import localDbServices.LocalDatabase;
import models.CvOwner;


public class Filter {
    

    private ArrayList<CvOwner> localCvOwners = LocalDatabase.getLocalDatabase();

    public ArrayList<CvOwner> searchByName(String searchInput) {

        ArrayList<CvOwner> searchNameCV = new ArrayList<CvOwner>();

        for (CvOwner person : localCvOwners) {

            if (person.getName().toLowerCase().replaceAll("\\s", "").contains(searchInput.toLowerCase())) {
                searchNameCV.add(person);
            }
        }
        if (searchNameCV.isEmpty()) {
            return null;
        }

        return searchNameCV;

    }

    public ArrayList<CvOwner> searchBySurname(String searchInput) {

        ArrayList<CvOwner> searchNameCV = new ArrayList<CvOwner>();

        for (CvOwner person : localCvOwners) {

            if (person.getSurname().toLowerCase().replaceAll("\\s", "").contains(searchInput.toLowerCase())) {
                searchNameCV.add(person);
            }
        }
        if (searchNameCV.isEmpty()) {
            return null;
        }

        return searchNameCV;

    }

    public ArrayList<CvOwner> searchByDepartment(String searchInput) {

        ArrayList<CvOwner> searchNameCV = new ArrayList<CvOwner>();

        for (CvOwner person : localCvOwners) {

            if (person.getDepartment().toLowerCase().replaceAll("\\s", "").contains(searchInput.toLowerCase())) {
                searchNameCV.add(person);
            }
        }
        if (searchNameCV.isEmpty()) {
            return null;
        }

        return searchNameCV;

    }

}
