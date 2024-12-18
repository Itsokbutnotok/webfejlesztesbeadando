package com.prz.buszbazis.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class BuszDTO {
    @NotEmpty(message = "A rendszám nem lehet üres")
    private String rendszam;

    @NotEmpty(message = "A típus nem lehet üres")
    private String tipus;

    @NotNull(message = "A műszaki érvényessége nem lehet üres")
    private LocalDate muszakierv;

    @NotEmpty(message = "Az ülések száma nem lehet üres")
    private String ulesekSzama;

    private MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }

    public @NotEmpty(message = "Az ülések száma nem lehet üres") String getUlesekSzama() {
        return ulesekSzama;
    }

    public void setUlesekSzama(@NotEmpty(message = "Az ülések száma nem lehet üres") String ulesekSzama) {
        this.ulesekSzama = ulesekSzama;
    }

    public @NotNull(message = "A műszaki érvényessége nem lehet üres") LocalDate getMuszakierv() {
        return muszakierv;
    }

    public void setMuszakierv(@NotNull(message = "A műszaki érvényessége nem lehet üres") LocalDate muszakierv) {
        this.muszakierv = muszakierv;
    }

    public @NotEmpty(message = "A típus nem lehet üres") String getTipus() {
        return tipus;
    }

    public void setTipus(@NotEmpty(message = "A típus nem lehet üres") String tipus) {
        this.tipus = tipus;
    }

    public @NotEmpty(message = "A rendszám nem lehet üres") String getRendszam() {
        return rendszam;
    }

    public void setRendszam(@NotEmpty(message = "A rendszám nem lehet üres") String rendszam) {
        this.rendszam = rendszam;
    }


}
