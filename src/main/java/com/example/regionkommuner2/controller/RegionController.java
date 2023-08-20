package com.example.regionkommuner2.controller;

import com.example.regionkommuner2.model.Kommune;
import com.example.regionkommuner2.model.Region;
import com.example.regionkommuner2.repository.KommuneRepository;
import com.example.regionkommuner2.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //til at alle frontend kan kalde vores endpoints
public class RegionController {
    //alt det her med jpa, workbench database skal være færdig kl 10 når eksamen starter kl 9. copy paste
    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/regioner")  //hav get og postmappingen på samme endpoint. Nogle censorer går meget op i det
    //i det her tilfælde skal det hedde regioner fordi vi henter flere, men vi kunne have flere getmappings
    //hvor en henter en enkelt, så skulle det hedde /region
    public List<Region> getRegioner(){ //returner liste ved findAll
        return regionRepository.findAll(); //husk findAll, ikke getAll
    }

    @PostMapping("/region") //altid ental
    @ResponseStatus(HttpStatus.CREATED) //husk responsestatus http created, er det nødvendigt?
    public Region saveRegion(@RequestBody Region region){  //husk RequestBody ved postmapping
        System.out.println(region);
        return regionRepository.save(region);
    }
}
