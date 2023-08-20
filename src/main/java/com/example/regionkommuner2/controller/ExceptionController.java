package com.example.regionkommuner2.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@RestController
@RequestMapping("/exp/") //alle endponints skal hedde det der først
public class ExceptionController {
    //her tester vi exeptions

    @GetMapping("/")
    public String hello(){
        return "hej";
    }

    @GetMapping("/div/{div}") //unchecked, runtime exception, dividere med 0, index out og boubds, stackoverflow
    public String divmednoget(@PathVariable int div){
        int i1 = 100;
        try {
            i1 = i1 / div; //linje smider exception hvis dividere med 0, derfor try-catch så vi kan udskrive
            //noget mere konkret til browseren frem for fejl 500 standard http error
        } catch (ArithmeticException err){ //runtime exception, man behøver ikke lave try-catch
            //man ved det ikke er en checked exception hvis java ikke tvinger en
            //alle exceptions som nedarvder fra runtime exception er unchecked
            //trhoable -> exceptions -> rumtime og checked
            return err.getMessage(); //udskriver at du dividere med 0
        }
        return "" + i1;
    }

    @GetMapping("/loop/{iloop}") //unchecked exception, hvis iloop er større end maks int værdi så exception
    public String loop(@PathVariable int iloop){
        int i2=0;
        for (int i1=0; i1 < iloop; i1++){
            i2++;
        }
        return "" + i2;
    }
    //for at man ikke skal lave try catch hver gang man tæller en variabel op
    //så laver vi vores egen exception vi kan throwe

    //skal returnere indhold af fil
    @GetMapping("/file/{filename}")
    public String filename(@PathVariable String filename){
        File file = new File(filename);
        String data="";

        try {
            //file.createNewFile();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                data += scanner.nextLine(); //kan auto laves til stringbuilder
            }

        } catch (FileNotFoundException io){ //checked exception, arver fra IOException som arver videre
            System.out.println(io.getMessage());
        }

        return "";
    }
    //exceptions: checked og unchecked,
    //alt der nedarvder fra exception bliver en checked exception. exception nedarvder fra throwable
    //hvis man åbner port eller fil så er den låst, den skal lukkes igen ellers deadlock
    //så ting skal lukkes igen når man er færdig. Man låser resourcen i try og rydder op i catch
    //eller finally
    //man kan lave sin egen scanner?

    //HUSK NÅR I TAGER FAT I EN RESSOURCE SKAL I RYDDE OP EFTER JER

    //ikke mange checked exceptions i spring. Dog jo i jdbc, connecte til database
    //man kan ikke selv bestemme om man vil lave exceptiosn i java, det kan man i andre sprog
    //i java SKAL man gøre det når man åbner resourcer


}
