package nusiss.paf.day27lectureworkshop.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import nusiss.paf.day27lectureworkshop.Model.Shows;
import nusiss.paf.day27lectureworkshop.Service.ShowsService;

@Controller
public class ShowsController {
    
    @Autowired
    private ShowsService showsSrvc;

    @GetMapping("/shows")
    public String findAllTvShows(Model model) {

        List<Shows> tvShowList = showsSrvc.findAllTvShow();
        model.addAttribute("shows", tvShowList);
        return "view1";
    }

    @GetMapping("/updateShowForm")
    public String updateShowForm(@RequestParam("name") String name, Model model) {
        Shows existingShow = showsSrvc.findShowByName(name);
        model.addAttribute("show", existingShow);
        return "view2";
    }

    @PostMapping("/updateShow")
    public String updateShow(Shows updatedShow) {
        showsSrvc.updateShow(updatedShow);
        return "redirect:/view1";
    }
}
