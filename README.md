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
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

class ExampleClass {

    public ExampleClass(Player player) {
        final PaginatedMenu menu = new PaginatedMenu(player, "example", 18) {
            @Override
            public void tick() {
                for (int i = 0; i < 27; i++) {
                    this.buttons[i] = new Button(Material.ENDER_PEARL)
                            .setDisplayName("&aSlot: " + i)
                            .setClickAction(event -> event.setCancelled(true));
                }
            }
        };

        menu.updateMenu();
    }
}
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
        super(player, "example", 18, 4);
    }

    @Override
    public void tick() {
        for(int i = 0; i < 27; i++) {
            this.buttons[i] = new Button(Material.ENDER_PEARL)
                    .setDisplayName("&aSlot: " + i)
                    .setClickAction(event -> event.setCancelled(true));
        }
    }
}
```

```
new ExampleMenu(player).updateMenu();
```

### Configuring Menu Pagination
```java
PaginatedMenu#setNavigationPosition(NavigationPosition)
PaginatedMenu#setNextPageButton(Button)
PaginatedMenu#setPreviousPageButton(Button)
```

### Configuring Menu Filling
```java
Menu#addFiller(FillingType)
```