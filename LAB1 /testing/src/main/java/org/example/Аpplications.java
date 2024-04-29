package org.example;

import java.util.ArrayList;
import java.util.List;

public class Аpplications {

    public Object applicantsForBothVisas;
    private List<String> ukVisaApplications;
    private List<String> usaVisaApplications;


    public Аpplications(List<String> ukVisaApplications, List<String> usaVisaApplications) {
        this.ukVisaApplications = ukVisaApplications;
        this.usaVisaApplications = usaVisaApplications;

    }

    public Аpplications() {
    }

    public static List<String> applicantsForBothVisas(List<String> ukVisaApplications, List<String> usaVisaApplications)
    {
        if(ukVisaApplications==null || ukVisaApplications==null)
        {
            throw new NullPointerException("The arrays must not be null");
        }

        if(ukVisaApplications.isEmpty() || usaVisaApplications.isEmpty()){
            throw new IllegalArgumentException("The arrays must not be empty");
        }

        List<String> ukAndUsaVisaApplications = new ArrayList<>();


        for(int i=0;i<ukVisaApplications.size();i++)
        {
            for (int j=0;j<usaVisaApplications.size();j++){

                if (ukVisaApplications.get(i) == usaVisaApplications.get(j)) {
                    ukAndUsaVisaApplications.add(ukVisaApplications.get(i));

                }
            }
        }

        if(ukAndUsaVisaApplications.isEmpty()){
            return null;
        }else {
            return ukAndUsaVisaApplications;
        }



    }




}
