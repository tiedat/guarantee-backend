package guarantee.backend.controller;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.service.RequestGuaranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/requestguarantee")
public class RequestGuaranteeController {

    @Autowired
    RequestGuaranteService requestGuaranteService;

    @PostMapping("/save")
    public ResponseEntity<RequestGuaranteeDTO> savePolicy(@RequestBody RequestGuaranteeDTO requestGuaranteeDTO) {
        Boolean saveStatus = requestGuaranteService.saveRequestGuarante(requestGuaranteeDTO);
        if (saveStatus){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
