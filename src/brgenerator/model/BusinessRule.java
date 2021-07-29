package brgenerator.model;

public class BusinessRule {
    private static int numRules = 0;
    protected String type;
    private int id;
    protected String name;

    public BusinessRule(String name) {
        id = ++numRules;
        this.name = name;
    }

    public BusinessRule(String name, String type) {
        id = ++numRules;
        this.name = name;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }
}
