package io.github.nosequel.menu.listener;

import io.github.nosequel.menu.Menu;
import io.github.nosequel.menu.MenuHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.Optional;

@RequiredArgsConstructor
public class MenuListener implements Listener {

    private final MenuHandler menuHandler;

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        final Player player = (Player) event.getWhoClicked();
        final Optional<Menu> menu = this.menuHandler.findMenu(player);

        if (menu.isPresent() && event.getCurrentItem() != null) {
            menu.get().click(event);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        final Player player = (Player) event.getPlayer();
        final Optional<Menu> menu = this.menuHandler.findMenu(player);

        menu.ifPresent(value -> value.handleClose(event));
    }
}