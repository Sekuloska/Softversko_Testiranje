package org.example;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class АpplicationsTest {


    // List<String> ukVisaApplications=new List<>(List.of("1","2","3","4"));

   // List<String> usaVisaApplications=new List<>(List.of("5","6","7","8"));
    Аpplications applications=new Аpplications();


    private List<String> ukVisaApplications;
    private List<String> usaVisaApplications;
    private Iterator<String> itr;
    private Iterator<String> itr1;

    //@BeforeEach
    public void setUp()         // set up test fixture
    {
        ukVisaApplications = new ArrayList<String>();
        ukVisaApplications.add("5");
        ukVisaApplications.add("6");
        ukVisaApplications.add("7");

        usaVisaApplications=new ArrayList<String>();
        usaVisaApplications.add("2");
        usaVisaApplications.add("3");
        usaVisaApplications.add("4");

         itr = ukVisaApplications.iterator();
        itr1=usaVisaApplications.iterator();
    }

    //    T T C 3.2
    @Test
    public void test_1(){
        ukVisaApplications = new ArrayList<String>();
        ukVisaApplications.add("1");
        ukVisaApplications.add("2");
        ukVisaApplications.add("3");

        usaVisaApplications=new ArrayList<String>();
        usaVisaApplications.add("2");
        usaVisaApplications.add("3");
        usaVisaApplications.add("4");
        assertNotNull(applications.applicantsForBothVisas(ukVisaApplications,usaVisaApplications));
    }

    // F F C3.1
    @Test
    public void test_2()
    {
        ukVisaApplications = new ArrayList<String>();
        ukVisaApplications=null;
        usaVisaApplications=new ArrayList<String>();
        usaVisaApplications=null;
        assertThrows(NullPointerException.class, () ->{
            applications.applicantsForBothVisas(ukVisaApplications,usaVisaApplications);
        });
    }

    // T F C3.1
    @Test
    public void test_3(){
        ukVisaApplications = new ArrayList<String>();

        usaVisaApplications=new ArrayList<String>();
        usaVisaApplications.add("2");
        usaVisaApplications.add("3");
        usaVisaApplications.add("4");
        assertThrows(IllegalArgumentException.class,() -> {
            applications.applicantsForBothVisas(ukVisaApplications, usaVisaApplications);
        });
    }

    //T T C3.1
    @Test
    public void test_4(){
        ukVisaApplications = new ArrayList<String>();
        ukVisaApplications.add("1");
        ukVisaApplications.add("2");
        ukVisaApplications.add("3");

        usaVisaApplications=new ArrayList<String>();
        usaVisaApplications.add("7");
        usaVisaApplications.add("9");
        usaVisaApplications.add("10");
        assertNull(applications.applicantsForBothVisas(ukVisaApplications,usaVisaApplications));
    }






}