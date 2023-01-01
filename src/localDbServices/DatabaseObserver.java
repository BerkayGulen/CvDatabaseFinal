package localDbServices;

import models.CvOwner;

public interface DatabaseObserver {
    void update(CvOwner person, int operation);
}
