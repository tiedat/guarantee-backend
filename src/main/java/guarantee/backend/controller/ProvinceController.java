package guarantee.backend.controller;

import guarantee.backend.model.Province;
import guarantee.backend.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/province")
public class ProvinceController {

    @Qualifier("ProvinceService")
    @Autowired
    private IProvinceService provinceService;

    @GetMapping("/all")
    public ResponseEntity<List<Province>> getAll() {
        List<Province> list = provinceService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
