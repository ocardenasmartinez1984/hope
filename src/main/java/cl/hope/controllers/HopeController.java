package cl.hope.controllers;

import cl.hope.controllers.dtos.HopeRequest;
import cl.hope.controllers.dtos.HopeResponse;
import cl.hope.mappers.HopeMapper;
import cl.hope.services.HopeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hopes")
@RequiredArgsConstructor
public class HopeController {

    private final HopeService hopeService;
    private final HopeMapper hopeMapper;

    @PostMapping
    public ResponseEntity<HopeResponse> insertHope(@Valid @RequestBody HopeRequest hopeRequest) {
        var hope = hopeService.insertHope(hopeMapper.hopeRequestToHope(hopeRequest));
        return new ResponseEntity<>(hopeMapper.hopeToHopeResponse(hope), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<HopeResponse>> getHopes() {
        return ResponseEntity.ok(hopeMapper.listHopeToListHopeResponse(hopeService.getHopes()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HopeResponse> getHope(@PathVariable Long id) {
        return ResponseEntity.ok(hopeMapper.hopeToHopeResponse(hopeService.getHope(id)));
    }

}
