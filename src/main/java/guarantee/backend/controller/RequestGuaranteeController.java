package guarantee.backend.controller;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.model.RequestGuarantee;
import guarantee.backend.service.RequestGuaranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/requestguarantee")
public class RequestGuaranteeController {

    @Autowired
    RequestGuaranteService requestGuaranteService;

    @PostMapping("/save")
    public ResponseEntity<RequestGuaranteeDTO> saveRequestGuarantee(@RequestBody RequestGuaranteeDTO requestGuaranteeDTO) {
        Boolean saveStatus = requestGuaranteService.saveRequestGuarante(requestGuaranteeDTO);
        if (saveStatus){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/get")
    public ResponseEntity<RequestGuaranteeDTO> getRequestGuarantee(@RequestBody String serial) {
        RequestGuaranteeDTO result = requestGuaranteService.searchBySerial(serial);
        if (null != result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        if (null == result) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/get")
    public ResponseEntity<List<RequestGuaranteeDTO>> getAllRequestGuarantee() {
        List<RequestGuaranteeDTO> result = requestGuaranteService.findAll();
        if (null != result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/accept")
    public ResponseEntity acceptRequestGuarantee(@RequestBody String serial) {
        boolean result = requestGuaranteService.acceptBySerial(serial);
        if (result) {
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reject")
    public ResponseEntity rejectRequestGuarantee(@RequestBody String serial) {
        boolean result = requestGuaranteService.rejectBySerial(serial);
        if (result) {
            return new ResponseEntity<>(result,HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }
}
