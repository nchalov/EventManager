package org.example.eventmanager.location;

import org.springframework.stereotype.Component;

@Component
public class LocationEntityConverter {

    public Location entityToLocation(LocationEntity locationEntity) {
        return new Location(
                locationEntity.getId(),
                locationEntity.getName(),
                locationEntity.getAddress(),
                locationEntity.getCapacity(),
                locationEntity.getDescription()
        );
    }

    public LocationEntity locationToEntity(Location location) {
        return new LocationEntity(
                location.getId(),
                location.getName(),
                location.getAddress(),
                location.getCapacity(),
                location.getDescription()
        );
    }

}
