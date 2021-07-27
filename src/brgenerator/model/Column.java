package brgenerator.model;

public class Column {
    private int id;
    private String name;

    public Column(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Column(String name) {
        this.name = name;
    }
}
