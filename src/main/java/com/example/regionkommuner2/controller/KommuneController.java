package com.example.regionkommuner2.controller;

import com.example.regionkommuner2.exception.ResourceNotFoundException;
import com.example.regionkommuner2.model.Kommune;
import com.example.regionkommuner2.repository.KommuneRepository;
import com.example.regionkommuner2.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class KommuneController {

    @Autowired
    KommuneRepository kommuneRepository;
    @Autowired
    RegionRepository regionRepository;

    @GetMapping("/kommuner")
    public List<Kommune> getKommuner(){
        return kommuneRepository.findAll();
    }


    @GetMapping("kommune/kode/{code}") //giver kommune der passer til indskrevne kommunekode
    public Kommune getKommuneByKode(@PathVariable String code){
        return kommuneRepository.findById(code).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Kommune med kode=" +code));
        //ved orElseThrow har paramtre supplier og andre mærkelige ting, forklares før påske
        //erik skrev først var obj = kom... for at se at man kunne gøre det? og så ændrede til return efter
        //ResponseStatusException er ny, lavet inden for et år. Men vi vil pakke fores exception ind i
        //vores egen exception, men man kan også bare bruge javas ResponseStatusException
        //orElseThrow thrower, inde i den er der anonym funktion som passer vores exception ind
    }

    @GetMapping("kommune/navn/{name}") //giver kommune der passer til indskrevne kommunekode
    //vi bruger navn og kode her på dansk fordi vi henter dansk kode fra skyen og ellers skulle vi konvertere det
    public Kommune getKommuneByName(@PathVariable String name){
        Optional<Kommune> kom = kommuneRepository.findByNavn(name);
        //optional bruges her fordi i repo skal jpa enten returnere en liste eller optional og derfor
        //får vi optional tilbage når vi kalder de metoder
        //vi får optional tilbage fra findByName men fordi vi bruger orElse så fpr vi kommune tilbage og ikke optional
        //det er optional som har den der orElse! eller orElseThrow
        //inde i optionalobjektet findes orElse metoden allerede og tester det dér
        System.out.println(kom);

        return kommuneRepository.findByNavn(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, " Kommune med navn=" +name));
        //med vores egen fejlmeddelse:
        //return kommuneRepository.findByNavn(name).orElseThrow(() -> new ResourceNotFoundException("Kommune med navn=" +name));

        //vi har lavet metode krop i interfacet for findByName men koden er der ikke så den giver fejl
        //hvorfor bruger vi optional? wrapper, kan have kommune eller null, uden optional skal man manuelt teste
        //om den er null
        //men stadig hvorfor

        //i jdkDynamicAopProxy.class
        //invoke metode dynamisk
        //publiv Object invoke(...
        //det der fortolker koden, dynamisk generere kode som laver den metode der passer til metodenavnet i repo
        //en hel metoe kan være en parameter
        //man kan gøre meget i java som minder om et foroltkningssprog som js

        //til eksamen hvordan skal vi forklare hvordan JPA gør ting bagom?
        //hvordan kommer man til de bagom sider med invokse
        //debugger, step ned
        //lav breakpoint på en sout der udskriver det vi tester?
        //gennem det med step ned kan man finde de bagom sider åbenbart

        //hvorfor optional
        //hvordan finder man de bagom javasider - hold ctr nede og tryk! Kan trykkes på alt/hvad som helst!

        //hvorfor skal errorMessage ikke implementere throwable?
        //hvorfor extends RuntimeException i reorucenotfount men ikke errormessage?
        //errormessage er en dum beholder vi bare lægger ting ned i, resourcenotfound er en exception
        //vi laver egne exceptions for at undgå try-catch, når vi newer resourcenotfpound så undgår vi try-catch

        //skal vi fremlægge det? Ja, vi skal vise hvis vi har brugt noget spæendende
        //vigtigt at vi lære at bruge git! hvordan man passer det sammen flere mennesker

        //fejl som man kan få ved at bruge de der hente metoder:
        //hvis databasen er slukket så har den en timeout og venter på serveren men lukker ikke ned
        //starter man databasen og går til endpointet igen så får man det man vil have
        //vi vil have ordentlige fejlmeddensler for at serveren er langsom/ikke svarer

        //husk servicelag, hav de her kode med exceptios i service?
        //vi skal beskrive til eksamen hvordan vi håndtere exceptions
        //eksamen: spørger ind til det vi har lavet primært

        //vi vil have flere fornuftige fejlmeddelser som ErrorMessage? vi bruger @ExceptionHandler

        //test i frontend: lav simple gui med knap som henter entpint og se hvad der sker, tester exceptions
    }

    @GetMapping("/kommuner/region/kode/{code}") //vil lave endpoint for at finde kommuner ud fra region
    //som beskrevet i kommuneRepo med findKommuneByRegion
    public List<Kommune> getKommunerByRegionKode(@PathVariable String code){
        regionRepository.findById(code).orElseThrow(() -> new ResourceNotFoundException("Region med kode=" + code));
        return kommuneRepository.findKommuneByRegion(code);
        //kan ikke bruge orElse her fordi man får liste tilbage
        //får man tom liste så er der noget fejl herinde
        //så man kan finde array på flere linjer
        //man kan lave repsonseentity med liste med kommuner + statuskode
        //hvordan ville det se ud?
        //List<Komuner> list.. repo.findByKOmmuneRehoiom.... får liste
        //tjek if(list.size() > 0) ... return new Reponsentiti<..>(list, httpstatus.OK)
        //else return new Reponsentiti<..>(list, httpstatus.notfount)
        //så manuelt tester man om arrayet er fyldt. Med optional tester den selv om der er objekt eller null
        //men kan jo så kun teste på objekt og ikke array
        //erik vil se om man kan teste array på en linje

        //returnere man List<kommune giver man ikke mulighed for at sende fejlmeddelse

        //nu finder vi bare først region og ser om den findes og giver fejl hvis den ikke finde
        //hvis den findes så virker næste linje også

        //hvordan ville det se ud hvis man lavede den anonyme funktion som normal funktion?

        //projekt: erik er tilgændelig konstant, også kl 10 om aftenen. Er der nogle gange fysisk
    }

    //vigtigt til eksamen: endpoints skal overholde navnekonvention: igen verber kun navneord og
    //ental og flertal som giver mening

    @PostMapping("/kommune")
    @ResponseStatus(HttpStatus.CREATED)
    public Kommune saveKommune(@RequestBody Kommune kommune){
        System.out.println(kommune);
        return kommuneRepository.save(kommune);
    }

    @PutMapping("/kommune/{id}") //putmapping
    public ResponseEntity<Kommune> updateCount(@PathVariable String id, @RequestBody Kommune kommune) {
        Optional<Kommune> optKommune = kommuneRepository.findById(id);
        if (optKommune.isPresent()) {
            kommuneRepository.save(kommune);
            return new ResponseEntity<>(kommune, HttpStatus.OK);
        } else {
            //throw new ResourceNotFoundException("Kommune med id=" + id + " findes ikke");
            //hvorfor ikke smide exception her for at være konsekvente med det ovenover?
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    } //erik kalder det skrammelkode fordi der er mange linjer. Heller en linje hvis man kan
}
