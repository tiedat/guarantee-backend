package guarantee.backend.controller;

import guarantee.backend.DTO.StationDTO;
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
    public HttpStatus registerStation(@RequestBody StationDTO stationDTO) {
        if (stationService.signUpStation(stationDTO)) {
            return HttpStatus.OK;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/getall")
    public ResponseEntity<List<StationDTO>> getAll() {
        return new ResponseEntity<>(stationService.getAll(), HttpStatus.OK);
    }
}
