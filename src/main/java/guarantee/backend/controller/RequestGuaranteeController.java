package guarantee.backend.controller;

import guarantee.backend.DTO.RequestGuaranteeDTO;
import guarantee.backend.service.IWarrantyService;
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

    @Autowired
    IWarrantyService warrantyService;

    @PostMapping("/save")
    public ResponseEntity<RequestGuaranteeDTO> saveRequestGuarantee(@RequestBody RequestGuaranteeDTO requestGuaranteeDTO) {
        Boolean saveStatus = requestGuaranteService.saveRequestGuarantee(requestGuaranteeDTO);
        if (saveStatus) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/get")
    public ResponseEntity<List<RequestGuaranteeDTO>> getRequestGuarantee(@RequestBody String serial) {
        List<RequestGuaranteeDTO> result = requestGuaranteService.searchBySerial(serial);
        if (null != result){
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        if (null == result) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
    public ResponseEntity acceptRequestGuarantee(@RequestBody String id) {
        try {
            Long value = Long.valueOf(id);
        } catch (Exception e) {
            return new ResponseEntity<>(id + " is not id",HttpStatus.BAD_REQUEST);
        }
        boolean result = requestGuaranteService.acceptBySerial(id);
        if (result) {
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/reject")
    public ResponseEntity rejectRequestGuarantee(@RequestBody String id) {
        try {
            Long value = Long.valueOf(id);
        } catch (Exception e) {
            return new ResponseEntity<>(id + " is not id",HttpStatus.BAD_REQUEST);
        }
        boolean result = requestGuaranteService.rejectBySerial(id);
        if (result) {
            return new ResponseEntity<>(null,HttpStatus.OK);
        }
        return new ResponseEntity<>(result,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/checkserial/{serial}")
    public ResponseEntity checkSerial(@RequestParam String serial) {
        try {
            warrantyService.findBySerialNumber(serial);
            return new ResponseEntity(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
