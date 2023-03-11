package nusiss.paf.day22workshop.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nusiss.paf.day22workshop.Model.Rsvp;
import nusiss.paf.day22workshop.Repository.RsvpRepository;

@Service
public class RsvpService {
    
    @Autowired
    private RsvpRepository rsvpRepo;

    public List<Rsvp> getAll() {
        return rsvpRepo.getAll();
    }

    public List<Rsvp> getRsvpByName(String fullName) {
        return rsvpRepo.getRsvpByName(fullName);
    }

    public Rsvp getRsvpByEmail(String email) {
        return rsvpRepo.getRsvpByEmail(email);
    }

    public Boolean create(Rsvp rsvp) {
        return rsvpRepo.create(rsvp);
    }

    public Boolean update(Rsvp rsvp) {
        return rsvpRepo.update(rsvp);
    }
}
