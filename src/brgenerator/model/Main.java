package brgenerator.model;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<BusinessRule> allRules = new ArrayList<>();
        AttributeRange ar = new AttributeRange("AR1", "Between", 1, 100);
        ar.setMinvalue(2);
        allRules.add(ar);

        Table t1 = new Table();
        Column c1 = new Column("Attributen");
        Column c2 = new Column("Apparaten");

        t1.getColumns().add(c1);
        t1.getColumns().add(c2);

        System.out.println(t1.getColumns());


    }
}
