public class BaseItemFactory implements ItemFactory{

    public ItemInterface create(ItemDefinition def){
        return new Item(def);
    }

}
