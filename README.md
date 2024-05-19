Let's adjust the README to remove the reference to the `INSTALL.md` file and instead include basic setup instructions directly within the README.

---

# __Inventory Management System__

## __Project Overview:__

This Inventory Management System is a comprehensive Java application designed to showcase the practical use of several important design patterns, including Observer, Composite, Singleton, Factory, and Strategy. The system allows for dynamic inventory management, item crafting and uncrafting, and offers a flexible search functionality, making it an ideal project for demonstrating advanced object-oriented design principles.

### __1. Key Features:__
+ **Dynamic Inventory Management:** Manage a collection of items with functionalities to add, remove, and search items within the inventory.
+ **Crafting and Uncrafting Mechanism:** Craft items from basic components and uncraft them back to their original components, demonstrating the Composite pattern.
+ **Flexible Item Search:** Implement multiple search strategies for item retrieval, illustrating the Strategy pattern in action.
+ **Design Patterns Integration:** Seamlessly integrates Observer, Composite, Singleton, Factory, and Strategy patterns to solve various design challenges.
+ **Modular Design:** Highly modular code structure that facilitates easy extension and maintenance.

### __2. Design Patterns Used:__
+ **Observer Pattern:** For updating various components of the system when changes occur in the inventory.
+ **Composite Pattern:** To handle items that can be crafted from and uncrafted into other items.
+ **Singleton Pattern:** Ensures a single instance of the Storage class throughout the application.
+ **Factory Pattern:** Used for creating items in a flexible manner, allowing for future expansion.
+ **Strategy Pattern:** Enables switching between different item search strategies dynamically.

## __Implementation Details:__

### Task 1: Behavioural Pattern - *Observer* Pattern
#### Changes Made:
1. **Added `StorageSubscriber` Interface:**
    - Interface with method `update(Inventory inv)`. 
    - `Player` class implements `StorageSubscriber` and calls `refreshInventory` on `storageViewInventory` within `update`.

2. **Storage Class Updates:**
    - Added `ArrayList<StorageSubscriber>` to hold subscribers.
    - Added `add(StorageSubscriber)` and `remove(StorageSubscriber)` methods to manage subscribers.
    - Implemented `informSubscribers()` method to notify all subscribers when `store` or `retrieve` is invoked.

### Task 2: Structural Pattern - *Composite* Pattern
#### Changes Made:
1. **Created `CraftableItem` Class:**
    - Extends `Item` and has an `ArrayList` of base items.
    - Added `getWeight()` and `getCompositionDescription()` methods to handle composite items.

2. **Added Methods to `ItemInterface`:**
    - `isBaseItem()`: Returns `true` for `Item` and `false` for `CraftableItem`.
    - `getComponents()`: Returns component items for `CraftableItem` or throws an error for `Item`.

3. **Added Craft and Uncraft Methods to `Player` Class:**
    - `craft(ItemInterface item)`: Checks for item availability, removes components, and adds crafted item.
    - `uncraft(ItemInterface item)`: Removes crafted item and adds its components back to inventory.

4. **Changed `ItemDefinition.create()` Method:**
    - Implemented factory pattern for creating items.

5. **SetUpCrafting/Uncrafting Methods in `App`:**
    - Used lambda expressions to call `craft` and `uncraft` methods with the respective items.

### Task 3: Design Improvements
#### Improvements Made:
1. **Singleton Pattern for Storage:**
    - Ensures a single instance of the Storage class.

2. **Factory Pattern for Creating Items:**
    - Created `ItemFactory` interface with `create(ItemDefinition itemDef)`.
    - Implemented `BaseItemFactory` and `CraftableItemFactory` for item creation.
    - Added method in `ItemDictionary` for item definitions used by `CraftableItemFactory`.

3. **Strategy Pattern for Item Searching:**
    - Added `SearchStrategy` interface and implemented `AllSearchStrategy`, `DescSearchStrategy`, and `NameSearchStrategy`.
    - Updated `Inventory` class to use `SearchStrategy` for item searching.

## __Getting Started:__

### Prerequisites:
- Java Development Kit (JDK) 8 or higher
- An Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse

### Installation:
1. **Clone the repository:**
    ```sh
    git clone https://github.com/MajdIssa34/JavaShop.git
    ```
2. **Navigate to the project directory:**
    ```sh
    cd JavaShop
    ```
3. **Open the project in your preferred IDE.**

### Running the Application:
1. **Build the project using your IDE’s build tools.**
2. **Run the `Main` class to start the application.**

### Usage:
- Add, remove, and search items within the inventory.
- Craft and uncraft items to see the Composite pattern in action.
- Experiment with different item search strategies to see the Strategy pattern in use.

---
