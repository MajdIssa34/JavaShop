# Task 1
Behavioural pattern - Options: *Strategy* or *Observer* pattern.
You chose: <*Observer* pattern>

## Itemise changes made (which class files were modified)

For this task, I did the following changes:

1. Added StorageSubscriber <<Interface>>:

  This interface has one method update(Inventory inv). Player implements StorageSubscriber as he is one, and in the implemented method update, we call a refreshInventory on the player's storageViewInventory. And the refresh inventory takes an inventory as a parameter(which is the updated version of the inventory that every subscriber must have), and creates a new instance of that inventory's stock list, and copies every element to that new list, and then references the old list as the new instance. 

2. Storage Class updates:

  1. I added an ArrayList of StorageSubsrcibers in the Storage class, which holds all the subscribers that wish to see the updated storage. 
  
  2. I added a remove(StorageSubscriber), and add(StorageSubscriber) which simply adds new StorageSubscribers to the list of subscribers.  

  3. I added a method called infromSubscribers, which loops through the list of subscribers and calls the update method on each subscriber. And every time the methods store and retrieve are invoked, we call the informSubscribers() method.

# Task 2
Structural pattern - *Composite* pattern.

For this task I changed a couple of things which are the following: 
## Itemise changes made (which class files were modified)

1. Created CraftableItem class:

  This class represents all craftable items, extends Item, and has an ArrayList of the base items that create this craftable item. And has 2 extra methods compared to Item:

  1. getWeight(), this method loops through each itemInterface of the ArrayList and calls the getweight() method on each of them and returns the total, instead of returning just the baseItem's weight as in the Item class.

  2. getCompositionDescription(), this method uses a String Builder to create a String that contains all ItemInterfaces inside the ArrayList separated by a comma and then returns it. 

2. Added 2 methods to ItemInterface:

  1. isBaseItem() which returns a boolean of true in case of Item class, and false in the case of the CraftableItem class. 
  
  2. getComponents(), which returns an ArrayList of ItemInterface containing the craftable item's component in the case of CraftableItem's class, and throws an error in case of the baseItem since it does not have any components.

3. Added Craft and Uncraft Methods to the Player class:

  1. craft(ItemInterface item):

    This method takes a craftable item only (because of the GUI, can't change it), and this method loops through each of this CraftableItem's components and searches if these items are available in the player's inventory, if not it throws an ItemNotFoundException on the item that was not found, however if all the items are available, we remove them from the player's inventory and add the CraftableItem given in the parameters to the player's inventory. (Be mindfull that we do not need to check any weights since the CraftableItem's weight is the same weight as it's components, and since we're creating the CraftableItem from the player's inventory, hence there is always enough weight.)

  2. uncraft(ItemInterface item):

    This method take a craftable item only (because of the GUI, can't change it), and then takes this craftableItem's components and creates an ArrayList of these items, then removes the craftableItem from the player's inventory, then adds all of it's components to the player's inventory one by one. (Again we do not have a weight problem.)

4. Changed ItemDefinition create() method:

  I implemented the factory pattern in the ItemDefinition class for Task 3 as an improvement.

5. setUpCrafting/Uncrafting methods in App:

  For the setupCrafting method, we use the lambda expression to call player's craft function and passing in a CraftableItem using the definition.create() function we implemented in part 4. For the setupUncrafting method, we use the lambda expression to call player's uncraft method and passing in the craftbaleItem given by the lambda expression.

# Task 3

Improvement 1: Singleton Pattern for Storage.

By implementing the Singleton Pattern in the Storage class, we prevent the creation of multiple instances of Storage, which is logical, since for this application we only have 1 storage as the place to store all of the items that the player doesn't want to hold. Implementing the Singleton Pattern ensures that the uniqueness of the Storage class is maintained in order to avoid any confusion or bugs when creating more than 1 instance of Storage. Plus this single instance is globally accessible in all our classes which is a benefit for our first task.

Imporvement 2: Factory Pattern for Creating Items.

Since the ItemDefinition class was the class used to create the Items, I felt like it had no business whatsover to create the items, hence we have 3 new classes:

  1. ItemFactory which is an interface having only 1 method called create(ItemDefinition itemDef).
    
  2. BaseItemFactory and CraftableItemFactory which are our 2 other new classes implementing ItemFactory. Mainly nothing changed in the program, everything that was in the ItemDefinition.create() method, was split into baseItem creation for BaseItemFactory class, and craftableItem creation for CraftableItemFactory class.
  
  3. I added 1 more method in the ItemDictionary class, which returns the String array of the item definition which is used specifically in the craftableItemFactory class. 
  
  By implementing this pattern, we encapsulate the item creation logic and make it easier to add new item types in the future and especially since the ItemDefinition class has nothing to do with ItemCreation. 

Improvement 3: Strategy Pattern - Item Searching.

For this improvement I changed/added the following files:

  1. Added 4 new classes:

    1. SearchStrategy: Interface that has 1 method called searchItems(ArrayList<ItemInterface> items, String searchTerm).

    2. 3 classes: AllSearchStrategy, DescSearchStrategy, NameSearchStrategy. All of these classes implement SearchStrategy, and each one of them implements the inherited method for it's own purposes (All, description, or Name), which are basically the same code that was in Inventory.searchItems() split into 3 parts.

  2. Changes in Inventory class:

    1. Added SearchStrategy attribute, and added a setSearchStrategy(SearchStrategy searchStrategy) in order to change searching strategy and deleted old setSearch(String searchterm) method, as well as String searchBy attribute as we don't need it anymore. 

    2. Changed the Inventory.searchItems(String) method, to just call SearchStrategy.searchItems().

    3. Changed the App.SetupSearching() method so that, instead of calling setSearch(String s) on the player's inventory and storage view inventory, it calls the new setSearchStrategy() with the corresponsding search strategy as attribute according to the search term given in the SearchByButton instance. 