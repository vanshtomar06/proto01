package com.prototype1.proto1.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "area")
@NoArgsConstructor
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double lat;
    private double lng;
    private double radius;
    private String color;
    private Integer reportCount;

    public Area(double lat, double lng, double radius, String color) {
        this.lat = lat;
        this.lng = lng;
        this.radius = radius;
        this.color = color;
    }
}
