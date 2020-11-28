package guarantee.backend.controller;


import guarantee.backend.model.TypeStation;
import guarantee.backend.service.ITypeStationService;
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
@RequestMapping("/api/type-station")
public class TypeStationController {
    @Autowired
    @Qualifier("TypeStationService")
    private ITypeStationService typeStationService;

    @GetMapping("/all")
    public ResponseEntity<List<TypeStation>> getAll() {
        List<TypeStation> list = typeStationService.getAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
