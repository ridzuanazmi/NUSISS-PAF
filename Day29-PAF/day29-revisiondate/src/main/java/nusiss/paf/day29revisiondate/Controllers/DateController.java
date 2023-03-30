package nusiss.paf.day29revisiondate.Controllers;


import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DateController {
    
    @GetMapping(path = "date")
    public String getDate(@RequestParam String dueDate) {

        // Date format 2023-03-30
        // yyyy-MM-dd

        System.out.printf("----- dueDate: %s\n", dueDate);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date = dateFormat.parse(dueDate);
            System.out.printf(">>>>> date: %s\n", date.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error.html";
        }

        return "redirect:/";
    }
}
