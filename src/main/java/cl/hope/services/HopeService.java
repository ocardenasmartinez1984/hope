package cl.hope.services;

import cl.hope.services.entities.Hope;
import cl.hope.mappers.HopeMapper;
import cl.hope.repositories.HopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HopeService {

    private final HopeRepository hopeRepository;
    private final HopeMapper hopeMapper = HopeMapper.INSTANCE;

    public void insertHope(Hope hope) {
        hopeRepository.save(hopeMapper.hopeToHopeEntity(hope));
    }

    public Hope getHope(Long id) {
        return hopeMapper.hopeEntityTityToHope(hopeRepository.findById(id).orElseThrow());
    }

    public List<Hope> getHopes() {
        return hopeMapper.listHopeEntityToListHope(hopeRepository.findAll());
    }

}
