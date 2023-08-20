package com.example.regionkommuner2.repository;

import com.example.regionkommuner2.model.Kommune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface KommuneRepository extends JpaRepository<Kommune, String> {

    //lav find by name
    //ved at lave ekstra nemme metoder skriv optional ...

    Optional<Kommune> findByNavn(String navn); //gør det her i vores projekter

    //alle kommuner med bestemt regionskode:
    List<Kommune> findKommuneByRegion(String kode);
    //laver selv sql hvor den finder kommuner og joiner og findr region

    //hvordan kan den augo gøre det ovenover
    //JPARepo extender ogå bare et interface uden kode, alle interfaces har ikke kode, de er tomme
    //de kan kun exentede interfaces og derfor er der ikke kode i enden

    //alle sql keywords kommer frem når man skal skrive navnet på metoden
    //Optional<Kommune> findKommuneByHref();

    //det er sql som autogeneres ud fra metodenavnet
    //nej der er ikke noget kode men hvad sql du skal skrive ud fra navnet er åbenlsyt
}
