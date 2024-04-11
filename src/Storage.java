import java.util.ArrayList;
import java.util.List;

public class Storage{
    private String storageName;
    private Inventory items;
    private List<StorageSubscriber> subs;
    private static Storage instance;


    private Storage(String name, Inventory startingInventory) {
        storageName = name;
        items = startingInventory;
        subs = new ArrayList<>();
    }

    static Storage getInstance(String name, Inventory startingInventory){
        if(instance == null){
            instance = new Storage(name, startingInventory);
        }
        return instance;
    } 
    public void addSubscriber(StorageSubscriber sub){
        subs.add(sub);
    }

    public void removeSubscriber(StorageSubscriber sub){
        subs.remove(sub);
    }

    /*should inform all subscribers(observers) 
    that there's an aupdate */
    public void informSubscribers(){
        for(StorageSubscriber sub : subs){
            sub.update(items);
        }
    }
    
    public Inventory getInventory() {
        return items;
    }

    public String getName() {
        return storageName;
    }

    /* whenever there's a change in the
    inventory, we notify subscribers.*/
    public void store(ItemInterface item) {
        items.addOne(item);
        informSubscribers();
    }

    public ItemInterface retrieve(ItemInterface item) throws ItemNotAvailableException {
        ItemInterface removed = items.remove(item);
        informSubscribers();
        return removed;
    }
    
}
