package nusiss.paf.day23.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day23.Model.Dependent;
import nusiss.paf.day23.Repository.DependentRepository;

@Service
public class DependentService {
    
    @Autowired
    DependentRepository depRepo;

    public List<Dependent> findAllDep() {
        return depRepo.findAllDep();
    }

    public Boolean createDep(Dependent dep) {
        return depRepo.createDep(dep);
    }

    public Boolean deleteDep(Integer id) {
        return depRepo.deleteDepById(id);
    }

    public Boolean updateDep(Dependent dep) {
        return depRepo.updateDepById(dep);
    }
}
