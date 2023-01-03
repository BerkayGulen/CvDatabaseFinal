package localDbServices;

import dbServices.CvOwnerDao;
import dbServices.CvOwnerDaoImpl;
import java.util.ArrayList;
import models.CvOwner;

public class LocalDatabase implements DatabaseObserver {

    private static ArrayList<CvOwner> cvOwners;
    private static LocalDatabase instance;
    private static final CvOwnerDao dbHelper = new CvOwnerDaoImpl();

    private LocalDatabase() {
    }

    public static ArrayList<CvOwner> getLocalDatabase() {
        if (cvOwners == null) {
            cvOwners = dbHelper.getAll();

        }
        return cvOwners;
    }

    public static LocalDatabase getInstance() {
        if (instance == null) {
            instance = new LocalDatabase();
        }
        return instance;

    }

    @Override
    public void update(CvOwner person, int operation) {
        switch (operation) {
            case 1:
                cvOwners.add(person);
                break;
            case 2:
                cvOwners.remove(person);
                break;
            case 3:
                cvOwners.removeAll(cvOwners);
            case 4:
                for (int i = 0; i < cvOwners.size(); i++) {
                    if (person.getId() == cvOwners.get(i).getId()) {
                        cvOwners.set(i, person);
                    }
                }
                break;

        }
    }

}
