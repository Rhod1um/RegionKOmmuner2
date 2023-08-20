package com.example.regionkommuner2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region {

    @Id
    @Column(length = 4)
    private String kode;
    private String navn;
    private String href;

    @OneToMany(mappedBy = "region")  //region er one og kommune er many, blev det byttet om i book/library?
    @JsonBackReference //forhindre at den lopper ved at begge har gettere og settere der peger på hinanden
    //forhindre at region får dens tilhørende kommuner med. Skal kun ske omvendt
    private Set<Kommune> kommuner = new HashSet<>(); //<- region er parent.

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

    public Set<Kommune> getKommuner() {
        return kommuner;
    }

    public void setKommuner(Set<Kommune> kommuner) {
        this.kommuner = kommuner;
    }
}
