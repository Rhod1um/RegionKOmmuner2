package com.example.regionkommuner2.repository;

import com.example.regionkommuner2.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {
}
