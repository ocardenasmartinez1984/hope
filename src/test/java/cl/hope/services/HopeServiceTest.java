package cl.hope.services;

import cl.hope.exceptions.HopeNotFoundException;
import cl.hope.mappers.HopeMapper;
import cl.hope.repositories.HopeRepository;
import cl.hope.repositories.entities.HopeEntity;
import cl.hope.services.entities.Hope;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HopeServiceTest {

    @Mock
    private HopeRepository hopeRepository;

    @Mock
    private HopeMapper hopeMapper;

    @InjectMocks
    private HopeService hopeService;

    @Test
    void insertHope_shouldSaveAndReturnHope() {
        var hope = new Hope();
        hope.setName("John");
        hope.setLastName("Doe");
        hope.setBirthDate(LocalDate.of(1990, 1, 15));

        var entity = new HopeEntity();
        entity.setId(1L);
        entity.setName("John");
        entity.setLastName("Doe");
        entity.setBirthDate(LocalDate.of(1990, 1, 15));

        var expectedHope = new Hope();
        expectedHope.setName("John");
        expectedHope.setLastName("Doe");
        expectedHope.setBirthDate(LocalDate.of(1990, 1, 15));

        when(hopeMapper.hopeToHopeEntity(hope)).thenReturn(entity);
        when(hopeRepository.save(entity)).thenReturn(entity);
        when(hopeMapper.hopeEntityToHope(entity)).thenReturn(expectedHope);

        var result = hopeService.insertHope(hope);

        assertThat(result.getName()).isEqualTo("John");
        assertThat(result.getLastName()).isEqualTo("Doe");
        verify(hopeRepository).save(entity);
    }

    @Test
    void getHope_shouldReturnHope_whenExists() {
        var entity = new HopeEntity();
        entity.setId(1L);
        entity.setName("Jane");
        entity.setLastName("Smith");
        entity.setBirthDate(LocalDate.of(1985, 5, 20));

        var hope = new Hope();
        hope.setName("Jane");
        hope.setLastName("Smith");
        hope.setBirthDate(LocalDate.of(1985, 5, 20));

        when(hopeRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(hopeMapper.hopeEntityToHope(entity)).thenReturn(hope);

        var result = hopeService.getHope(1L);

        assertThat(result.getName()).isEqualTo("Jane");
        assertThat(result.getLastName()).isEqualTo("Smith");
    }

    @Test
    void getHope_shouldThrowException_whenNotFound() {
        when(hopeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> hopeService.getHope(99L))
                .isInstanceOf(HopeNotFoundException.class)
                .hasMessageContaining("Hope not found with id: 99");
    }

    @Test
    void getHopes_shouldReturnAllHopes() {
        var entities = List.of(new HopeEntity(), new HopeEntity());
        var hopes = List.of(new Hope(), new Hope());

        when(hopeRepository.findAll()).thenReturn(entities);
        when(hopeMapper.listHopeEntityToListHope(entities)).thenReturn(hopes);

        var result = hopeService.getHopes();

        assertThat(result).hasSize(2);
    }

}
