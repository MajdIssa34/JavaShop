public interface ItemFactory{
    /* An interface that has only 1 method 
     responsible for creating Items.
    */
    ItemInterface create(ItemDefinition def);
}