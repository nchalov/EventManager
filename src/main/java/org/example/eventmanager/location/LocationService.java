package org.example.eventmanager.location;

import lombok.AllArgsConstructor;
import org.example.eventmanager.exception.CapacityException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository repository;
    private final LocationEntityConverter converter;

    public List<Location> getAllLocations() {
        return repository.findAll().stream()
                .map(converter::entityToLocation)
                .toList();
    }

    public Location createLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Передана несуществующая локация");
        }
        var savedLocation = repository.save(converter.locationToEntity(location));
        return converter.entityToLocation(savedLocation);
    }

    public Location deleteLocation(Integer id) {
        repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Сущность не найдена"));
        var foundLocation = repository.getReferenceById(id);
        repository.deleteById(id);
        return converter.entityToLocation(foundLocation);
    }

    public Location getLocationById(Integer id) {
        repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Сущность не найдена"));
        var foundLocation = repository.getReferenceById(id);
        return converter.entityToLocation(foundLocation);
    }

    public Location updateLocationById(Integer id, Location location) {
        repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Сущность не найдена"));
        LocationEntity referenceById = repository.getReferenceById(id);
        if (referenceById.getCapacity().compareTo(location.getCapacity()) > 0) {
            throw new CapacityException("Новая вместимость локации не должна быть меньше предыдущей");
        }
        referenceById.setName(location.getName());
        referenceById.setAddress(location.getAddress());
        referenceById.setCapacity(location.getCapacity());
        referenceById.setDescription(location.getDescription());
        var savedLocation = repository.save(referenceById);
        return converter.entityToLocation(savedLocation);
    }

}
