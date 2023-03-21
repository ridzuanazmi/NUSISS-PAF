package nusiss.paf.day22revision.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day22revision.Model.Rsvp;
import nusiss.paf.day22revision.Repository.RsvpRepository;

@Service
public class RsvpService {
    
    @Autowired
    RsvpRepository rsvpRepo;

    public List<Rsvp> getAllRsvp() {
        return rsvpRepo.getAllRsvp();
    }

    public List<Rsvp>getRsvpByName(String fullName) {
        return rsvpRepo.getRsvpByName(fullName);
    }

    public Rsvp getRsvpById(Integer id) {
        return rsvpRepo.getRsvpById(id);
    }

    public Boolean createRsvp1(Rsvp rsvp) {
        return rsvpRepo.createRsvp1(rsvp);
    }

    public Boolean updateRsvp1(Rsvp rsvp) {
        return rsvpRepo.updateRsvp1(rsvp);
    }

    public Boolean deleteRsvpById(Integer id) {
        return rsvpRepo.deleteRsvpById(id);
    }
}
