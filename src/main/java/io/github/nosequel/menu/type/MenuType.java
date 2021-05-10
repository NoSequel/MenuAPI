package io.github.nosequel.menu.type;

import io.github.nosequel.menu.Menu;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public enum MenuType {

    HOPPER {
        /**
         * Create a new inventory with the menu type
         *
         * @param menu the menu to create it for
         * @return the inventory
         */
        @Override
        public Inventory createInventory(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.HOPPER, menu.getTitle());
        }
    },

    INVENTORY {
        /**
         * Create a new inventory with the menu type
         *
         * @param menu the menu to create it for
         * @return the inventory
         */
        @Override
        public Inventory createInventory(Menu menu) {
            return Bukkit.createInventory(null, menu.getSize(), menu.getTitle());
        }
    },

    FURNACE {
        /**
         * Create a new inventory with the menu type
         *
         * @param menu the menu to create it for
         * @return the inventory
         */
        @Override
        public Inventory createInventory(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.FURNACE, menu.getTitle());
        }
    },

    BREWING_STAND {
        /**
         * Create a new inventory with the menu type
         *
         * @param menu the menu to create it for
         * @return the inventory
         */
        @Override
        public Inventory createInventory(Menu menu) {
            return Bukkit.createInventory(null, InventoryType.BREWING, menu.getTitle());
        }
    };

    /**
     * Create a new inventory with the menu type
     *
     * @param menu the menu to create it for
     * @return the inventory
     */
    public abstract Inventory createInventory(Menu menu);

}
