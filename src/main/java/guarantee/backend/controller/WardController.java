package guarantee.backend.controller;

import guarantee.backend.model.Ward;
import guarantee.backend.repositories.WardRepository;
import guarantee.backend.service.IWardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/ward")
public class WardController {
    @Autowired
    @Qualifier("WardService")
    private IWardService wardService;

    @GetMapping("/districtid/{id}")
    public ResponseEntity<List<Ward>> getWard(@PathVariable("id") Long id) {
        List<Ward> list = wardService.getByDistrictId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{proname}/{district}")
    public ResponseEntity<List<Ward>> getWardbyName(@PathVariable("proname") String proname,@PathVariable("district") String district) {
        List<Ward> list = wardService.getByProNameDistrictName(proname,district);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
