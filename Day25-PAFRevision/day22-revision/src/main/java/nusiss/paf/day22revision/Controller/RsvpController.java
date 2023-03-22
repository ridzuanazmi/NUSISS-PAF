package nusiss.paf.day22revision.Controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import nusiss.paf.day22revision.Model.Rsvp;
import nusiss.paf.day22revision.Service.RsvpService;

@Controller
@RequestMapping("/rsvps")
public class RsvpController {
    
    @Autowired
    RsvpService rsvpSrvc;

    private Logger logger = Logger.getLogger(RsvpController.class.getName());

    @GetMapping
    public String getAllRsvp(Model model) {
        List<Rsvp> rsvpList = rsvpSrvc.getAllRsvp();
        model.addAttribute("rsvp", rsvpList);
        return "RsvpList";
    }

    @GetMapping("/addNew")
    public String createRsvpForm(Model model) {
        Rsvp rsvpForm = new Rsvp();
        model.addAttribute("rsvp", rsvpForm);
        return "RsvpForm";
    }

    @PostMapping("/create")
    public String createRsvp(@Valid @ModelAttribute("rsvp") Rsvp rsvpForm, BindingResult result) {

        if (result.hasErrors()) {
            return "RsvpForm";
        }

        Rsvp rsvp = new Rsvp();
        rsvp.setFullName(rsvpForm.getFullName());
        rsvp.setEmail(rsvpForm.getEmail());
        rsvp.setPhone(rsvpForm.getPhone());
        rsvp.setConfirmationDate(rsvpForm.getConfirmationDate());
        rsvp.setComments(rsvpForm.getComments());
        rsvpSrvc.createRsvp1(rsvp);

        return "redirect:/rsvps"; // Why is there an error when I want to redirect to RsvpList? so have to redirect to the GET path
    }

    @GetMapping("/delete/{id}")
    public String deleteRsvp(@PathVariable("id") Integer id) {
        rsvpSrvc.deleteRsvpById(id);
        return "redirect:/rsvps";
    }

    @GetMapping("/update/{id}")
    public String updateRsvp(@PathVariable("id") Integer id, Model model) {

        Rsvp updateRsvp = rsvpSrvc.getRsvpById(id);

        logger.info("GET rsvps/update/{id}: %s".formatted(updateRsvp.toString()));
        
        model.addAttribute("rsvps", updateRsvp);
        return "rsvpUpdate";
    }

    // Need to have the th:field id in the html form to submit if not it will
    // be null ("0") and MySQL wont be updated
    @PostMapping("/update")
    public String updatedRsvp(@Valid @ModelAttribute("rsvps") Rsvp rsvpForm, BindingResult result) {

        logger.info("POST /rsvps/update: %s".formatted(rsvpForm.toString()));

        if (result.hasErrors()) {
            return "rsvpUpdate";
        }

        Boolean isUpdated = rsvpSrvc.updateRsvp2(rsvpForm);
        if (isUpdated) {
            return "redirect:/rsvps";
        } else {
            return "rsvpUpdate";
        }
    }
}
