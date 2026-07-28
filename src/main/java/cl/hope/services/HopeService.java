package cl.hope.services;

import cl.hope.exceptions.HopeNotFoundException;
import cl.hope.mappers.HopeMapper;
import cl.hope.repositories.HopeRepository;
import cl.hope.services.entities.Hope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HopeService {

    private final HopeRepository hopeRepository;
    private final HopeMapper hopeMapper;

    public Hope insertHope(Hope hope) {
        var entity = hopeRepository.save(hopeMapper.hopeToHopeEntity(hope));
        return hopeMapper.hopeEntityToHope(entity);
    }

    public Hope getHope(Long id) {
        var entity = hopeRepository.findById(id)
                .orElseThrow(() -> new HopeNotFoundException("Hope not found with id: " + id));
        return hopeMapper.hopeEntityToHope(entity);
    }

    public List<Hope> getHopes() {
        return hopeMapper.listHopeEntityToListHope(hopeRepository.findAll());
    }

}
