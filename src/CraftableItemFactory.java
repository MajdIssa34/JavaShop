import java.util.ArrayList;
import java.util.List;

public class CraftableItemFactory implements ItemFactory{
    
    ItemDictionary itemDic = ItemDictionary.get(); 

    public ItemInterface create(ItemDefinition definition) {
        List <ItemInterface> componentList = new ArrayList<>();
        String [] componentNames = definition.getDefinitionComponents();
        for(int i = 0 ; i < componentNames.length ; i++){
            ItemInterface item = new Item(itemDic.defByName(componentNames[i]).get());
            componentList.add(item);
        }
        ItemInterface craftable = new CraftableItem(definition, componentList);      
        // An ItemDefinition for a craftable item might follow a similar pattern
        // to how a craftable/composite item looks.
        return craftable; 
    }
}
