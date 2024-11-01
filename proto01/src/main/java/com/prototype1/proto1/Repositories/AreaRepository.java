package com.prototype1.proto1.Repositories;

import com.prototype1.proto1.Models.Area;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AreaRepository extends JpaRepository<Area, Long> {
    List<Area> findAll();

    @Transactional
    @Modifying
    @Query("update Area a set a.reportCount = a.reportCount+1 where a.lng = ?1 and a.lat = ?2")
    public void updateByLngAndLat(double lo, double la);
}
