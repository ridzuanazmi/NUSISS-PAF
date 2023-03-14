package nusiss.paf.day23.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nusiss.paf.day23.Model.Dependent;
import nusiss.paf.day23.Service.DependentService;

@RestController
@RequestMapping(path = "/api/deps")
public class DependentController {
    
    @Autowired
    DependentService depSrvc;

    @GetMapping
    public ResponseEntity<?> findAllDep() {

        List<Dependent> depList = depSrvc.findAllDep();

        if (!depList.isEmpty()) {
            return ResponseEntity.ok()
                    .body(depList);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Could not find Dependent table");
        }
    }

    @PostMapping
    public ResponseEntity<?> createDep(@RequestBody Dependent dep) {
        Boolean isCreated = depSrvc.createDep(dep);

        if (isCreated) {
            return ResponseEntity.ok()
                    .body(dep);
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409))
                    .body("Dependent record creation unsuccessful");
        }
    }

    @DeleteMapping(path = "/{dep-id}")
    public ResponseEntity<?> deleteDep(@PathVariable("dep-id") Integer id) {
        Boolean isDeleted = depSrvc.deleteDep(id);

        if (isDeleted) {
            return ResponseEntity.ok()
                    .body("Record of id:%d successfully deleted".formatted(id));
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409))
                    .body("Dependent record deletion unsuccessful");
        }
    }

    @PutMapping
    public ResponseEntity<?> updateDep(@RequestBody Dependent dep) {
        Boolean isUpdated = depSrvc.updateDep(dep);

        if (isUpdated) {
            return ResponseEntity.ok()
                    .body("Record of id:%d successfully updated".formatted(dep.getId()));
        } else {
            return ResponseEntity.status(HttpStatusCode.valueOf(409))
                    .body("Dependent record update unsuccessful");
        }
    }
}
