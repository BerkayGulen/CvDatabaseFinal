package dbServices;

import java.util.ArrayList;
import models.CvOwner;

public interface CvOwnerDao {
    void add(CvOwner person);
    void delete(CvOwner person);
    void deleteAll();
    void update(CvOwner person);
    ArrayList<CvOwner> getAll();
}
