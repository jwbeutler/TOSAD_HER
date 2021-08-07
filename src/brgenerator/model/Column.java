package brgenerator.model;

public class Column {
    private static int numColumns = 0;
    private int id;
    private String name;
    private String type;
    private int tableid;


    public Column(String name, String type) {
        id = ++numColumns;
        this.name = name;
        this.type = type;

    }
    public Column(int id,String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;

    }
    public Column(int id,String name, String type,int tableid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.tableid = tableid;

    }

    public int getTableid() {
        return tableid;
    }

    public static void setNumColumns(int numColumns) {
        Column.numColumns = numColumns;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
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
