package brgenerator.model;

import brgenerator.persistency.*;
import brgenerator.services.AttributeRangeService;
import brgenerator.services.ServiceProvider;
import brgenerator.services.TargetDBSerivce;

public class Main {


    public static void main(String[] args) {
        TargetDBSerivce service = ServiceProvider.getTargetDBService();
        AttributeRangeService service1 = ServiceProvider.getAttributeRangeService();
        service1.createAR("AR","Between",1,10);
        service1.createAR("AR2","Between",1,10);



    }


    }


