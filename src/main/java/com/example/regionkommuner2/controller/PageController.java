package com.example.regionkommuner2.controller;

import com.example.regionkommuner2.model.Kommune;
import com.example.regionkommuner2.repository.KommuneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PageController {

    @Autowired
    KommuneRepository kommuneRepository;
    //eriks autowire var rød hvorfor? typisk fejl -
    //når spring containeren starter laver den instanser af klasserne
    //gør den kun hvis den fortælles den skal gøre det - @RestController gør det

    @GetMapping("/kommunepage")
    public ResponseEntity<List<Kommune>> getPageOfKommune() {
        int page  = 0; //hvor mange sider. og hvilken side du starter på.
        //^starter på 0. Er page på 1 starter den på side to
        //hardcoded her,kommer gentlig fra front end
        int size = 5; //hvor mange objekter på side

        PageRequest kommunePage = PageRequest.of(page, size); //page request kan alt muligt også sortere pages
        //er en del af jpa, jpa understøtter pagination
        Page<Kommune> pageKommune = kommuneRepository.findAll(kommunePage);
        //generee sql

        //ovreload: ved findAll med kommunepage, find all returnere forskellige
        //typer alt efter paratemter. Uden parameter returnere den liste med kommuner,
        //nu returnere den en Page fordi PageRequest selv har en findAll som kaldes her i stedet for
        //vores eget repo med JPARepository
        var obj = kommuneRepository.findAll(kommunePage);

        List<Kommune> kommuneList = pageKommune.getContent();
        return new ResponseEntity<>(kommuneList, HttpStatus.OK);
        /*if (kommuneList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        //else
        return new ResponseEntity<>(kommuneList, HttpStatus.OK);*/
    }

    @GetMapping("/kommunepageparm")
    public ResponseEntity<Map<String, Object>> getPageOfKommuner(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Kommune> pageKommune = kommuneRepository.findAll(paging);

        List<Kommune> kommuner = pageKommune.getContent();

        if (kommuner.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, Object> response = new HashMap<>();
        response.put("kommuner", kommuner);
        response.put("currentPage", pageKommune.getNumber());
        response.put("totalItems", pageKommune.getTotalElements());
        response.put("totalPages", pageKommune.getTotalPages());
        //det her adder de her ting til json objektet
        //vi gør det så man kan se det i url, man kan også skrive asc og des
        //og sortere det asc des. Gøres senere

        return new ResponseEntity<>(response, HttpStatus.OK);
        //på json prikker man så på hvor mange objekter der er, hvor mange sier og current page
    }

    @GetMapping("kommunepagesort")
    public ResponseEntity<Map<String, Object>> getCountyPageAndSort(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "kode,desc") String[] sort) {

        //http://localhost:8080/kommunepagesort?sort=kode,desc
        //http://localhost:8080/kommunepagesort?page=1&size=10&sort=kode,desc

        List<Sort.Order> orders = new ArrayList<>();
        if (sort[0].contains(",")) {
            for (String sortOrder: sort) {
                String[] _sort = sortOrder.split(",");
                orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
            }
        } else {
            orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
        }

        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
        Page<Kommune> pageCounty = kommuneRepository.findAll(pagingSort);
        List<Kommune> county = pageCounty.getContent();

        if (county.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        Map<String, Object> response = new HashMap<>();
        response.put("county", county);
        response.put("currentPage", pageCounty.getNumber());
        response.put("totalItems", pageCounty.getTotalElements());
        response.put("totalPages", pageCounty.getTotalPages());

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }
        return Sort.Direction.ASC;
    }
}
//pagination i regionkommun2. pages på fx jobindex, nederst på siden står der 1, 2, 3 osv og en pil
//altså html siden har flere sider

//sidetal står i json'en. Json med jobannoncer her
//i url'en stpr der sidetal. man kan manuelt skrive sidenummer i url for at komme til den side
//man bestemmer selv hvor mange json objekter der skal vises per side fx 10 eller 20, så bestemmer det hvor
//mange sider der kommer

//url &

//eksamen: dekorer backend med et eller andet såsom pagination, exceptions, DTO
