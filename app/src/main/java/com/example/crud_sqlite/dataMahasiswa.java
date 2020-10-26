package com.example.crud_sqlite;

public class dataMahasiswa {
    private String ID;
    private String NAMA;
    private String FAKULTAS;
    private String JURUSAN;
    private String SEMESTER;


    public dataMahasiswa() {
    }

    public dataMahasiswa(String ID, String NAMA, String FAKULTAS, String JURUSAN, String SEMESTER) {
        this.ID = ID;
        this.NAMA = NAMA;
        this.FAKULTAS = FAKULTAS;
        this.JURUSAN = JURUSAN;
        this.SEMESTER = SEMESTER;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNAMA() {
        return NAMA;
    }

    public void setNAMA(String NAMA) {
        this.NAMA = NAMA;
    }

    public String getFAKULTAS() {
        return FAKULTAS;
    }

    public void setFAKULTAS(String FAKULTAS) {
        this.FAKULTAS = FAKULTAS;
    }

    public String getJURUSAN() {
        return JURUSAN;
    }

    public void setJURUSAN(String JURUSAN) {
        this.JURUSAN = JURUSAN;
    }

    public String getSEMESTER() {
        return SEMESTER;
    }

    public void setSEMESTER(String SEMESTER) {
        this.SEMESTER = SEMESTER;
    }
}
