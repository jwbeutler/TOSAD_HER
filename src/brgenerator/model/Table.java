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
    public Table(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public Table() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static void setNumTables(int numTables) {
        Table.numTables = numTables;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
}

