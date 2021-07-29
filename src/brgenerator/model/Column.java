package brgenerator.model;

public class Column {
    private static int numColumns = 0;
    private int id;
    private String name;
    private String type;

    public Column(String name, String type) {
        id = ++numColumns;
        this.name = name;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

}
