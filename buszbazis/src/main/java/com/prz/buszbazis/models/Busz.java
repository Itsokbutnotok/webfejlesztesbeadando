package com.prz.buszbazis.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "busz")
public class Busz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String rendszam;
    private String tipus;
    private Date muszakierv;
    private int ulesekSzama;
    private String imageFileName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) {
        this.rendszam = rendszam;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }

    public Date getMuszakierv() {
        return muszakierv;
    }

    public void setMuszakierv(Date muszakierv) {
        this.muszakierv = muszakierv;
    }

    public int getUlesekSzama() {
        return ulesekSzama;
    }

    public void setUlesekSzama(int ulesekSzama) {
        this.ulesekSzama = ulesekSzama;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }
}
