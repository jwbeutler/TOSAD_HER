package brgenerator.model;

import java.util.ArrayList;

public class Table {
    private static int numTables = 0;
    private int id;
    private String name;
    private ArrayList<Column> columns = new ArrayList<>();

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public Table(String name) {
        id = ++numTables;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
