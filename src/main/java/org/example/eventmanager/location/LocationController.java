package org.example.eventmanager.location;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
@AllArgsConstructor
public class LocationController {

    private final LocationService service;
    private final LocationDtoConverter converter;

    @GetMapping
    public ResponseEntity<List<LocationDto>> getAllLocations() {
        var allLocations = service.getAllLocations().stream()
                .map(converter::locationToDto)
                .toList();
        return ResponseEntity.ok(allLocations);
    }

    @PostMapping
    public ResponseEntity<LocationDto> createLocation(@RequestBody @Validated LocationDto locationDto) {
        var createLocation = service.createLocation(converter.dtoToLocation(locationDto));
        return new ResponseEntity<>(converter.locationToDto(createLocation), HttpStatus.CREATED);
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<LocationDto> deleteLocation(@PathVariable("locationId") Integer locationId) {
        var location = service.deleteLocation(locationId);
        return new ResponseEntity<>(converter.locationToDto(location), HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{locationId}")
    public ResponseEntity<LocationDto> getLocationById(@PathVariable Integer locationId) {
        var gotLocation = service.getLocationById(locationId);
        return ResponseEntity.ok(converter.locationToDto(gotLocation));
    }

    @PutMapping("/{locationId}")
    public ResponseEntity<LocationDto> updateLocation(@PathVariable Integer locationId,
                                               @RequestBody @Validated LocationDto locationDto) {
        var location = service.updateLocationById(locationId, converter.dtoToLocation(locationDto));
        return ResponseEntity.ok(converter.locationToDto(location));
    }

}
