import java.util.List;

public class CraftableItem extends Item {

    private List<ItemInterface> components;

    public CraftableItem(ItemDefinition def, List<ItemInterface> components) {
        super(def);
        this.components = components;
    }

    @Override
    public String getName() {
        //System.out.println(definition.getDescription());
        return definition.getName();
    }

    @Override
    public String getDescription() {
        return definition.getDescription();
    }

    @Override
    public double getWeight() {
        // Calculate the weight of the craftable item as the sum of its components' weights
        double totalWeight = 0;
        for(ItemInterface i : components){
            totalWeight+= i.getWeight();
        }
        return totalWeight;
    }

    @Override
    public ItemDefinition getDefinition() {
        return definition;
    }

    @Override
    public String getCompositionDescription() {
        // Create a composition description by listing the names of components
        StringBuilder composition = new StringBuilder();
        for (ItemInterface component : components) {
            composition.append(component.getName()).append(", ");
        }
        if (composition.length() > 2) {
            // Remove the trailing ", " if there are components
            composition.setLength(composition.length() - 2);
        }
        return composition.toString();
    }

    @Override
    public boolean equals(ItemInterface other) {
        return isOf(other.getDefinition());
    }

    @Override
    public boolean isOf(ItemDefinition def) {
        return getName().equals(def.getName());
    }

    // @Override
    // public boolean isBaseItem() {
    //     return false;
    // }

    public List<ItemInterface> getComponents(){
        return this.components;
    }

    @Override
    public String toString() {
        String output = String.format("Item: %s\nDescription: %s\nWeight: %.2f",
            getName(), getDescription(), getWeight());
        output += "\nHashCode: " + Integer.toHexString(this.hashCode());
        return output;
    }
}

