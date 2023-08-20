package com.example.regionkommuner2.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class Kommune {
    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;
    @Column(name = "hrefphoto")
    private String hrefPhoto;  //i sql laver den camelcase som defautl det til href_photo, det er grimt
    //så er der lavet kolonne med null værdier i workbenh for hver række

    @ManyToOne
    @JoinColumn(name = "regionkode", referencedColumnName = "kode") //sidste SKAL hedde det PK er for Region
    //første kommer til at hedde det man skriver her i databasen
    private Region region;


    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getHrefPhoto() {
        return hrefPhoto;
    }

    public void setHrefPhoto(String hrefPhoto) {
        this.hrefPhoto = hrefPhoto;
    }
}
