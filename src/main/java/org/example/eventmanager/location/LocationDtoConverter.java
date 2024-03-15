package org.example.eventmanager.location;

import org.example.eventmanager.location.LocationDto;
import org.example.eventmanager.location.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationDtoConverter {

    public Location dtoToLocation(LocationDto locationDto) {
        return new Location(
                locationDto.getId(),
                locationDto.getName(),
                locationDto.getAddress(),
                locationDto.getCapacity(),
                locationDto.getDescription()
        );
    }

    public LocationDto locationToDto(Location location) {
        return new LocationDto(
                location.getId(),
                location.getName(),
                location.getAddress(),
                location.getCapacity(),
                location.getDescription()
        );
    }

}
