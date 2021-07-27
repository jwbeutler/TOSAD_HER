package brgenerator.model;

import java.util.ArrayList;

public class Table {
    private int id;
    private String name;
    private ArrayList<Column> columns = new ArrayList<>();

    public ArrayList<Column> getColumns() {
        return columns;
    }
}
