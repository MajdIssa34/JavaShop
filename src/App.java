import javax.swing.JFrame;

public class App {
    private Player player;
    private Storage storage;
    private JFrame frame;
    private PageManager manager;

    public App(Player p, Storage s) {
        player = p;
        storage = s;
        player.setStorageView(storage.getInventory());
        storage.addSubscriber(player);    
        manager = new PageManager(player, storage);
        // Setup of sorting
        setupSearching((InventoryPage) manager.findPage("Player Inventory"));
        setupSearching((InventoryPage) manager.findPage("Storage"));

        // Setup of craftng
        setupCrafting((ItemCraftPage) manager.findPage("Item Crafting"), player);
        setupUncrafting((ProductPage) manager.findPage("Product Page"), player);

        // Window creation
        manager.refresh();
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(manager.getJPanel());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Task 1: Defining what each button in the UI will do.
    void setupSearching(InventoryPage page) {
        page.addSearchByButton(new SearchByButton("All", () -> {
            player.getInventory().setSearchStrategy(new AllSearchStrategy());
            player.getStorageView().setSearchStrategy(new AllSearchStrategy());
        }));

        page.addSearchByButton(new SearchByButton("Name", () -> {
            player.getInventory().setSearchStrategy(new NameSearchStrategy());
            player.getStorageView().setSearchStrategy(new NameSearchStrategy());
        }));

        page.addSearchByButton(new SearchByButton("Description", () -> {
            player.getInventory().setSearchStrategy(new DescSearchStrategy());
            player.getStorageView().setSearchStrategy(new DescSearchStrategy());
        }));
    }

    void setupCrafting(ItemCraftPage page, Player player) {
        //System.out.println("Majd");
        page.setCraftAction((def)-> player.craft(def.create()));
    }

    void setupUncrafting(ProductPage page, Player player) {
        //System.out.println("Majd");
        page.setUncraftAction((item) -> player.uncraft(item));        
    }
}
