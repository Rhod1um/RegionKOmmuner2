package com.example.regionkommuner2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mock1 {

    @Test
    void testInlineMock(){
        Map<Integer, String> mapKommuner = new HashMap<>();
        mapKommuner.put(1085, "Roskilde"); //key value
        String str = mapKommuner.get(1085);
        System.out.println(str);
        //assert str.equals("Roskilde"); dette virker også! nu hvor den ikke vil have assertEquals uden Assertions
        Assertions.assertEquals("Roskilde", str);
    }

    @Test
    void testInlineMockMap(){
        Map<Integer, String> mapMock = mock(Map.class); //tager framework Mockito ind
        //mock(Map.class( giver mock klasse af map, den får dens metoder og laver en fake klasse
        //mock er dog stadig et tomt objekt, det kan intet. Det har metoderne af den klasse der er lavet et mock af
        //men mock klassen kan intet. Proxy objekt
        //derfor skal vi i en when metode forklare hvad den skal gøre når man kalder en bestemt metode
        //er ikke en del af eksamen men censor kan spørge hvad mocking er!
        mapMock.put(1085, "Roskilde");
        String str = mapMock.get(1085);
        assertTrue(mapMock.size()>0);
        Assertions.assertEquals("Roskilde", str);
    }

    @Test
    void testInlineMockMapWhen(){ //man laver when klasse
        Map<Integer, String> mapMock = mock(Map.class);
        when(mapMock.get(1085)).thenReturn("Roskilde");

        //mapMock.put(1085, "Roskilde");
        String str = mapMock.get(1085);
        System.out.println(str); //husk sout hvis man vil se hvad der kommer ud
        //assertTrue(mapMock.size()>0);
        Assertions.assertEquals("Roskilde", str);
    }

    @Test
    void testListThenThen(){
        List listMock = mock(List.class);
        //fjol: vi spørger om size på det tomme mock objekt og det har intet men vi specificere her
        //at den skal svare 2 hvis nogen spørger om dets size, og 3 hvis de spørger anden gang
        //giver ingen mening men viser at den kan returnere forskellige ting alt efter antal gange man spørger
        when(listMock.size()).thenReturn(2).thenReturn(3);
        Assertions.assertEquals(2, listMock.size());
        //hvad tester vi nu hvor mockobjekt er tomt,
        //mock er til at kalde et framework som ikke er færdigt endnu
        //vi kalder andet firmas kode men det er ikke lavet færdigt så vi kalder deres mocks
        //hvor deres specs står, hvad der BURDE være lavet
        //et typisk scenarie. Man er ikke færdig med kode og derfor mocker man det og andre får ens mock
        //og bruger det og når ens kode så er lavet færdigt kan andre som har brugt ens mock bare fjerne
        //mock ordet og bruge de metoder man så egentlig har
        //i det rigtige liv bruger man mock, men her skal vi bare kunne forklare hvad det er

        //mock er fake metoder. Fx sirenehistorie fra erik:
        //mockede sirener. Når fake sirenemetode kaldes giver den et eller andet svar,
        //at de hyler eller giver fejlbesked

        //her mocker vi javas egne klasser og senere mocker vi vores egne klasser
        //fx
    }
}
