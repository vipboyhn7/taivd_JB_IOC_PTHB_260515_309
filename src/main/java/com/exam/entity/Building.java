package com.exam.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "buildings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "building_name", length = 100, nullable = false, unique = true)
    private String buildingName;

    @Column(name = "building_area", nullable = false)
    private Double buildingArea;

    @Column(name = "area_unit", length = 10, nullable = false)
    private String areaUnit;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "time", nullable = false)
    private Integer time;

    @Column(name = "time_unit", length = 10, nullable = false)
    private String timeUnit;

    @Column(name = "design", length = 255, nullable = false)
    private String design;

    @Column(name = "content", length = 255, nullable = false)
    private String content;

    @Column(name = "status", nullable = false)
    private Short status;
}
