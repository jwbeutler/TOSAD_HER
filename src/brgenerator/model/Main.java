package brgenerator.model;

import brgenerator.persistency.*;
import brgenerator.services.*;

public class Main {


    public static void main(String[] args) {
        AttributeRangeService arService = ServiceProvider.getAttributeRangeService();

        for(AttributeRange ar : arService.findAll()){
            System.out.println(ar.getId());
        }







    }


    }


