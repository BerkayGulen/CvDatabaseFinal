/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import com.raven.table.model.TableRowData;

/**
 *
 * @author berka
 */
public class ModelStaff extends TableRowData {

    private int id;
    private String name;
    private String surname;
    private String department;
    private String cvFilePath;

    /**
     *
     * @return
     */
    @Override
    public Object[] toTableRow() {
        return new Object[]{name, surname, department, cvFilePath};

    }

    public ModelStaff() {
    }

    public ModelStaff(int id, String name, String surname, String department, String cvFilePath) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.department = department;
        this.cvFilePath = cvFilePath;
    }

}
