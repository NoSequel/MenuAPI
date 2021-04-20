# Menu API
This is a simplistic Menu API made for the Spigot API, with built-in pagination, actions, and more.

# Note
- Menu fillers are currently in development, and issues may occur.

# Usage
## Registering the menu handler:
You have to register the ``MenuHandler`` object somewhere, preferably inside of the main class, for example:

```java
@Override
public void onEnable() {
    new MenuHandler(this);
    // your code
}
```

## Creating a new menu
You have to make a class extending the Menu class (or PaginatedMenu), or make a new anonymous class, for example:
### Anonymous Class
```java
import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import org.bukkit.inventory.ItemStack;

import java.util.List;

final PaginatedMenu menu = new PaginatedMenu(player, "example", 18) {
    
    @Override
    public List<Button> buttons = new ArrayList<>() {
        final List<Button> butons = new ArrayList<>();

        for (int i = 0; i < 27; i++) {
            buttons.add(new Button(i, Material.ENDER_PEARL)
            .setDisplayName("&aSlot: " + i)
            .setClickAction(event -> event.setCancelled(true));
        }
        
        return buttons;
    }
    
}

menu.updateMenu();
```

### Extending Menu

```java
import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.PaginatedMenu;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ExampleMenu extends PaginatedMenu {

    public ExampleMenu(Player player) {
        super(player, "example", 18);
    }

    /**
     * Get the buttons to display in the menu.
     * <p>
     * These buttons will be converted
     * into an {@link ItemStack}
     * and will be displayed inside of the menu.
     *
     * @return the list of buttons
     */
    @Override
    public List<Button> getButtons() {
        final List<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 27; i++) {
            buttons.add(new Button(i, Material.ENDER_PEARL)
                    .setDisplayName("&aSlot: " + i)
                    .setClickAction(event -> event.setCancelled(true));
        }

        return buttons;
    }
}
```

```
new ExampleMenu(player).updateMenu();
```

### Configuring Menu Pagination
```java
PaginatedMenu#setNavigationPosition(NavigationPosition)
PaginatedMenu#setFillNavBar(Boolean)
PaginatedMenu#setPaginationButtonType(ItemStack)
```