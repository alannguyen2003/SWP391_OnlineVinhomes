package service;

import entity.CoordinatorEntity;
import entity.UserEntity;
import java.util.ArrayList;
import repository.CoordinatorRepository;

public class CoordinatorService {

    CoordinatorRepository coorRepo = new CoordinatorRepository();

    public ArrayList<CoordinatorEntity> getAvailableCoordinator() throws Exception {
        return coorRepo.getAvailableCoordinator();
    }
}
