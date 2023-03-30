package nusiss.paf.day27lectureworkshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day27lectureworkshop.Model.Shows;
import nusiss.paf.day27lectureworkshop.Repository.ShowsRepository;

@Service
public class ShowsService {
    
    @Autowired
    private ShowsRepository showsRepo;

    public List<Shows> findAllTvShow() {
        return showsRepo.findAllTvShow();
    }

    public Shows findShowByName(String name) {
        return showsRepo.findShowByName(name);
    }

    public void updateShow(Shows updatedShow) {
        showsRepo.updateShow(updatedShow);
    }
}
