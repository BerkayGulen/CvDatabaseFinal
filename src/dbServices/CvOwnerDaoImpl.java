package dbServices;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import localDbServices.DatabaseObserver;
import localDbServices.LocalDatabase;
import models.CvOwner;
import org.apache.commons.io.FileUtils;

public class CvOwnerDaoImpl implements CvOwnerDao {

    private Connection conn = DatabaseConnection.getInstance();
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;
    private DatabaseObserver observer;

    public CvOwnerDaoImpl() {
        this.preparedStatement = null;
        this.statement = null;
        this.resultSet = null;
        this.observer = LocalDatabase.getInstance();
    }

    @Override
    public void add(CvOwner person) {
        try {
            preparedStatement = conn.prepareStatement("INSERT INTO cvOwner values (?,?,?,?,?);");
            preparedStatement.setString(2, person.getName());
            preparedStatement.setString(3, person.getSurname());
            preparedStatement.setString(4, person.getDepartment());
            preparedStatement.setString(5, person.getCvFilePath());
            preparedStatement.execute();

            preparedStatement.close();
            person.setId(getMaxId());

            notifyObserver(person, 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(CvOwner person) {
        try {
            preparedStatement = conn.prepareStatement("UPDATE cvOwner SET name = ?, surname = ?, department = ?, cvFilePath = ? WHERE id = ?");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getDepartment());
            preparedStatement.setString(4, person.getCvFilePath());
            preparedStatement.setInt(5, person.getId());

            preparedStatement.execute();

            preparedStatement.close();
            notifyObserver(person, 4);

            //notifyObserver(person, 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(CvOwner person) {
        try {
            notifyObserver(person, 2);

            preparedStatement = conn.prepareStatement("delete from cvOwner where id=?");
            preparedStatement.setInt(1, person.getId());
            preparedStatement.execute();

            preparedStatement.close();
            FileUtils.delete(new File(person.getCvFilePath()));

        } catch (SQLException e) {
            System.err.println(e);
        } catch (IOException ex) {
            Logger.getLogger(CvOwnerDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int getMaxId() {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT MAX(id) FROM cvOwner");
            int maxId = resultSet.getInt(1);
            statement.close();
            resultSet.close();
            return maxId;
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    @Override
    public void deleteAll() {
        try {
            String sql = "delete from cvOwner";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.execute();
            preparedStatement.close();
            notifyObserver(null, 3);

        } catch (SQLException e) {
            System.err.println(e);
        }

    }

    @Override
    public ArrayList<CvOwner> getAll() {
        ArrayList<CvOwner> cvOwners = new ArrayList<>();
        try {
            String query = "SELECT * from cvOwner";
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String department = resultSet.getString("department");
                String cvFilePath = resultSet.getString("cvFilePath");
                cvOwners.add(new CvOwner(id, name, surname, department, cvFilePath));
            }
            statement.close();
            resultSet.close();
            return cvOwners;

        } catch (SQLException e) {
            System.err.println(e);
        }

        return null;
    }

    public void notifyObserver(CvOwner person, int operation) {
        observer.update(person, operation);
    }

}
