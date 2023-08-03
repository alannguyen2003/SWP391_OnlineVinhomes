package service;

import entity.CoordinatorEntity;
import java.util.ArrayList;
import repository.CoordinatorRepository;

public class CoordinatorService {

    CoordinatorRepository coorRepo = new CoordinatorRepository();

    public void addCoordinator(int CID) throws Exception {
        coorRepo.addCoordinator(CID);
    }

    public ArrayList<CoordinatorEntity> getAvailableCoordinator() throws Exception {
        return coorRepo.getAvailableCoordinator();
    }

    public ArrayList<CoordinatorEntity> getAllCoordinator() throws Exception {
        return coorRepo.getAllCoordinator();
    }

    public CoordinatorEntity getCoordinatorByID(int CID) throws Exception {
        return coorRepo.getCoordinatorByID(CID);
    }

    public void updateEnableCoordinattor(boolean enable, int id) throws Exception {
        coorRepo.updateEnableCoordinattor(enable, id);
    }

    public int autoAssignCoordinator() throws Exception {
        return coorRepo.autoAssignCoordinator();
    }
}
