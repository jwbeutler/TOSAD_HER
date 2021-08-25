package brgenerator.services;

import brgenerator.model.AttributeRange;
import brgenerator.persistency.AttributeRangeDAO;
import brgenerator.persistency.AttributeRangeDAOImpl;

import java.sql.SQLException;
import java.util.List;

public class AttributeRangeService {
    AttributeRangeDAO attributeRangeDAO = new AttributeRangeDAOImpl();

    public AttributeRange createAR(String name, String operator, int minVal, int maxVal) throws SQLException {
        AttributeRange newAr = new AttributeRange(name,operator,minVal,maxVal);
        int id = attributeRangeDAO.create(newAr);
        newAr.setId(id);
        return newAr;
    }
    public List<AttributeRange> findAll(){
        return attributeRangeDAO.findAll();
    }
    public AttributeRange findById(int id){
        return attributeRangeDAO.findById(id);
    }

}
