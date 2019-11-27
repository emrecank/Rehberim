package com.example.rehberim;

import android.graphics.Bitmap;

public class Kisiler
{
    private String isim;
    private String telefon_numarasi;
    private Bitmap resim=null;

    public Bitmap getResim() {
        return resim;
    }

    public void setResim(Bitmap resim) {
        this.resim = resim;
    }

    public String get_isim()
    {
        return isim;
    }

    public void set_isim(String isim)
    {
        this.isim = isim;
    }

    public String get_numara()
    {
        return telefon_numarasi;
    }

    public void set_numara(String telefon_numarasi)
    {
        this.telefon_numarasi = telefon_numarasi;
    }
}