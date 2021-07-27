package brgenerator.model;

public class BusinessRule {
    protected String type;
    private int id;
    protected String name;

    public BusinessRule(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public BusinessRule(String name, String type) {
        this.name = name;
        this.type = type;
    }

}
