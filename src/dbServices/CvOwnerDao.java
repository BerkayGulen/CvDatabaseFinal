package dbServices;

import java.util.ArrayList;
import models.CvOwner;

public interface CvOwnerDao {
    void add(CvOwner person);
    void delete(CvOwner person);
    void deleteAll();
    ArrayList<CvOwner> getAll();
}
