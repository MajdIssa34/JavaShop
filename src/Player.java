import java.util.List;
public class Player implements StorageSubscriber{
    private String name;
    private Inventory inventory;
    private double carryWeightCapacity;
    private Inventory storageView;

    public Player(String playerName, double carryCapacity, Inventory sInventory) {
        name = playerName;
        carryWeightCapacity = carryCapacity;
        inventory = sInventory;
    }

    public void setStorageView(Inventory storageInventory) {
        storageView = storageInventory;
    }

    public Inventory getStorageView() {
        return storageView;
    }

    public String getName() {
        return name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public double getCarryCapacity() {
        return carryWeightCapacity;
    }

    public double getCurrentWeight() {
        double carrying = 0;
        for (ItemInterface item : getInventory().searchItems("")) {
            carrying += item.getWeight();
        }
        return carrying;
    }

    public void store(ItemInterface item, Storage storage) throws ItemNotAvailableException {
        // Do we have the item we are trying to store
        if (!inventory.searchItems("").contains(item)) {
            throw new ItemNotAvailableException(item.getDefinition());
        }
        storage.store(inventory.remove(item));
    }


    public void retrieve(ItemInterface item, Storage storage) throws ItemNotAvailableException, ExceedWeightCapacity {
        // Does the Storage have the item we are trying to retrieve
        if (!storageView.searchItems("").contains(item)) {
            throw new ItemNotAvailableException(item.getDefinition());
        }
        if (getCurrentWeight() + item.getWeight() > getCarryCapacity()) {
            throw new ExceedWeightCapacity(this, item);
        }
        inventory.addOne(storage.retrieve(item));
    }

    @Override
    public void update(Inventory inv) {
        this.storageView.refreshInventory(inv);
    }
    
    public void craft(ItemInterface item) throws ItemNotAvailableException { 
        CraftableItem craftableItem = (CraftableItem) item;
        List<ItemInterface> components = craftableItem.getComponents();
        for (ItemInterface component : components) {
            if (!(inventory.contains(component))) {      
                throw new ItemNotAvailableException(component.getDefinition());
            }
        }
        for (ItemInterface component : components) {
            inventory.removeOne(component.getDefinition());
        }
        this.inventory.addOne(craftableItem);               
    }

    public void uncraft(ItemInterface item) throws ItemNotAvailableException{
         
        // Get the components of the craftable item
        List<ItemInterface> components = item.getComponents();

        // Remove the craftable item from the player's inventory
        inventory.remove(item);

        // Add the components to the player's inventory
        for (ItemInterface component : components) {
            inventory.addOne(component);
        }
    }

}
