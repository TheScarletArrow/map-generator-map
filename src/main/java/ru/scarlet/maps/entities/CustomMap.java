package ru.scarlet.maps.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class CustomMap {
    @Id
    private UUID uuid;
    private String mapName;
    @OneToOne
    private CustomMapTypeClass mapType;

    @ElementCollection
    private List<Countries> countries;
    @OneToOne
    private AppUser owner;

    public UUID uuid() {
        return uuid;
    }





}
