package guarantee.backend.controller;

import guarantee.backend.DTO.StationDTO;
import guarantee.backend.model.Station;
import guarantee.backend.service.IStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/station")
public class StationController {
    @Autowired
    @Qualifier("StationService")
    private IStationService stationService;

    @PostMapping("/register")
    public ResponseEntity registerStation(@RequestBody StationDTO stationDTO) {
        if (stationService.signUpStation(stationDTO)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<StationDTO>> getAll() {
        return new ResponseEntity<>(stationService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/all-pending")
    public ResponseEntity<List<StationDTO>> getAllPending() {
        List<StationDTO> list = stationService.getAllPending();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/all-accepted")
    public ResponseEntity<List<StationDTO>> getAllAccepted() {
        List<StationDTO> list = stationService.getAllAccept();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/accept")
    public ResponseEntity acceptStation(@RequestBody Long id) {
        if (stationService.acceptStation(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/remove")
    public ResponseEntity removeStation(@RequestBody Long id) {
        if (stationService.removeStation(id))
            return new ResponseEntity(HttpStatus.OK);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
