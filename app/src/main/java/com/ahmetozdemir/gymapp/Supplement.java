package com.ahmetozdemir.gymapp;

import java.io.Serializable;

public class Supplement implements Serializable //Serializable interface'i ile oluşturulan nesneyi farklı yerlerde de kullanabiliyoruz.
{
    String supplementName; // Supplement adı
    String supplementInfo; // Supplement bilgisi
    int supplementImage;   // Supplement görseli

    public Supplement(String supplementName, String supplementInfo, int supplementImage) // Constructor
    {
        this.supplementName = supplementName;
        this.supplementInfo = supplementInfo;
        this.supplementImage = supplementImage;
    }
}
