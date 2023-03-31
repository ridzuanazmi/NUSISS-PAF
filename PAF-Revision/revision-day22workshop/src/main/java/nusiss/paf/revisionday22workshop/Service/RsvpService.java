package nusiss.paf.revisionday22workshop.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.revisionday22workshop.Model.Rsvp;
import nusiss.paf.revisionday22workshop.Repository.RsvpRepository;

@Service
public class RsvpService {
    
    @Autowired
    private RsvpRepository rsvpRepo;

    public List<Rsvp> getAllRsvp() {
        return rsvpRepo.getAllRsvp();
    }

    public Optional<Rsvp> getRsvpByName(String name) {
        try {
            return rsvpRepo.getRsvpByName(name);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Optional<Rsvp> getRsvpByEmail(String email) {
        try {
            return rsvpRepo.getRsvpByEmail(email);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public Boolean createRsvp(Rsvp rsvp) {
        return rsvpRepo.createRsvp(rsvp);
    }

    public boolean updateRsvp(Rsvp rsvp) {
        return rsvpRepo.updateRsvp(rsvp);
    }

    public Boolean deleteRsvpById(Integer id) {
        return rsvpRepo.deleteRsvpById(id);  
    }

    // Utility methods
    public void updateRsvpUtil(Rsvp rsvp, String email, Rsvp updatedRsvp) {
        rsvp.setFullName(updatedRsvp.getFullName());
        rsvp.setEmail(email);
        rsvp.setPhone(updatedRsvp.getPhone());
        rsvp.setConfirmationDate(updatedRsvp.getConfirmationDate());
        rsvp.setComments(updatedRsvp.getComments());
    }

    public Rsvp createRsvpUtil(Rsvp insertedRsvp) {

        Rsvp rsvp = new Rsvp();
        rsvp.setFullName(insertedRsvp.getFullName());
        rsvp.setEmail(insertedRsvp.getEmail());
        rsvp.setPhone(insertedRsvp.getPhone());
        rsvp.setConfirmationDate(insertedRsvp.getConfirmationDate());
        rsvp.setComments(insertedRsvp.getComments());
        
        return rsvp;
    }
}
