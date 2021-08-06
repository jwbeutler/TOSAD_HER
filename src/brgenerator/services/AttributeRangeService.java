package brgenerator.services;

import brgenerator.model.AttributeRange;
import brgenerator.persistency.AttributeRangeDAO;
import brgenerator.persistency.AttributeRangeDAOImpl;

import java.util.List;

public class AttributeRangeService {
    AttributeRangeDAO attributeRangeDAO = new AttributeRangeDAOImpl();

    public AttributeRange createAR(String name, String operator, int minVal, int maxVal){
        AttributeRange newAr = new AttributeRange(name,operator,minVal,maxVal);
        attributeRangeDAO.create(newAr);
        return newAr;
    }
    public List<AttributeRange> findAll(){
        return attributeRangeDAO.findAll();
    }

}
