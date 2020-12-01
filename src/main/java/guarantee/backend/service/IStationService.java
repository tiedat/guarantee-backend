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

    List<StationDTO> getAllPending();

    List<StationDTO> getAllAccept();

    boolean acceptStation(Long id);

    boolean removeStation(Long id);
}
