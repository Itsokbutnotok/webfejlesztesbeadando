package com.prz.buszbazis.services;

import com.prz.buszbazis.models.Busz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BuszRepository extends JpaRepository<Busz, Integer> {

}
