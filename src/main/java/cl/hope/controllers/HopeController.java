package cl.hope.controllers;

import cl.hope.entities.HopeRequest;
import cl.hope.entities.HopeResponse;
import cl.hope.mappers.HopeMapper;
import cl.hope.services.HopeService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HopeController {

    private final HopeService hopeService;
    private final HopeMapper hopeMapper = HopeMapper.INSTANCE;

    @PostMapping("/inserthope")
    public ResponseEntity<String> insertHope(@Valid @RequestBody HopeRequest hopeRequest) {
        hopeService.insertHope(hopeMapper.hopeRequestToHope(hopeRequest));
        return new ResponseEntity<>("ok", HttpStatus.ACCEPTED);
    }

    @GetMapping("/gethopes")
    public ResponseEntity<List<HopeResponse>> getHopes() {
        return new ResponseEntity<>(hopeMapper.listHopeToListHopeResponse(hopeService.getHopes()), HttpStatus.ACCEPTED);
    }

    @GetMapping("/gethope/{id}")
    public ResponseEntity<HopeResponse> getHope(@PathVariable("id") Long id) {
        return new ResponseEntity<>(hopeMapper.hopeToHopeResponse(hopeService.getHope(id)), HttpStatus.ACCEPTED);
    }

}
