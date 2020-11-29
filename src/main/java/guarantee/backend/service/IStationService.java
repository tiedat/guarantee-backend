package guarantee.backend.service;

import guarantee.backend.DTO.StationDTO;
import guarantee.backend.model.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IStationService {
    boolean signUpStation(StationDTO station);

    List<StationDTO> getAll();

    StationDTO mapToDTO(Station station);
}
