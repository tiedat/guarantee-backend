package guarantee.backend.service.Impl;

import guarantee.backend.DTO.StationDTO;
import guarantee.backend.model.Station;
import guarantee.backend.repositories.StationRepository;
import guarantee.backend.service.IStationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Component("StationService")
public class StationServiceImpl implements IStationService {
    @Autowired
    private StationRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public boolean signUpStation(StationDTO stationDTO) {
        try {
            stationDTO.setStatus("PENDING");
            stationDTO.setPassword(encodePassword(stationDTO.getPassword()));
            Station station = modelMapper.map(stationDTO, Station.class);
            repository.save(station);
            return true;
        } catch (Exception e) {
            e.getMessage();
            return false;
        }

    }

    @Override
    public List<StationDTO> getAll() {
        List<Station> list = (List<Station>) repository.findAll();
        List<StationDTO> list1 = list.stream().map(this::mapToDTO).collect(Collectors.toList());
        return list1;
    }

    @Override
    public StationDTO mapToDTO(Station station) {
        return modelMapper.map(station, StationDTO.class);
    }
}
