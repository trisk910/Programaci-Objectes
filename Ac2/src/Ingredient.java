public class Ingredient {

    private String quantity;
    private String name;

    private String type;


    public Ingredient(String quantity, String name, String type) {
        this.quantity = quantity;
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }
}
