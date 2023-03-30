package nusiss.paf.day28airbnb.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day28airbnb.Model.Airbnb;
import nusiss.paf.day28airbnb.Repository.AirbnbRepository;

@Service
public class AirbnbService {
    
    @Autowired
    private AirbnbRepository airbnbRepo;

    public List<Airbnb> findAirbnbByCountry(String seacrhText) {
        return airbnbRepo.findAirbnbByCountry(seacrhText);
    }
}
