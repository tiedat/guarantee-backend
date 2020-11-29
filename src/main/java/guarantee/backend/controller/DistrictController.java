package guarantee.backend.controller;

import guarantee.backend.model.District;
import guarantee.backend.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/district")
public class DistrictController {

    @Autowired
    @Qualifier("DistrictService")
    private IDistrictService districtService;

    @GetMapping("/provinceid/{id}")
    public ResponseEntity<List<District>> getByProvinceId(@PathVariable("id") Long id) {
        List<District> list = districtService.getByProvinceId(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/get/{proname}")
    public ResponseEntity<List<District>> getByProvinceName(@PathVariable("proname") String proname) {
        List<District> list = districtService.getByProvinceName(proname);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
