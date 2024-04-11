import java.util.ArrayList;

public interface SearchStrategy{
    ArrayList<ItemInterface> searchItems(ArrayList<ItemInterface> items, String searchTerm);
}