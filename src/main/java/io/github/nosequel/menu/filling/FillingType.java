package io.github.nosequel.menu.filling;

import io.github.nosequel.menu.Menu;
import io.github.nosequel.menu.buttons.Button;

import java.util.ArrayList;
import java.util.List;

public enum FillingType {

    BORDER {
        /**
         * Fill the slots inside of the menu with the
         * specified filling type.
         *
         * @param menu    the menu to fill
         * @param buttons the buttons which are currently used
         * @return the filling buttons
         */
        @Override
        public List<Button> fillMenu(Menu menu, List<Button> buttons) {
            final List<Button> fillers = new ArrayList<>();

            for(int i = 0; i < menu.getSize(); i++) {
                boolean applicableSlot = true;

                for (Button button : buttons) {
                    if (button.getIndex() == i) {
                        applicableSlot = false;
                        break;
                    }
                }

                if (applicableSlot) {
                    if (i < 9 || i >= menu.getSize() - 9 || i % 9 == 0 || i % 9 == 8) {
                        fillers.add(new Button(i, menu.getFillerType().getType()).setData(menu.getFillerType().getData().getData()));
                    }
                }
            }

            return fillers;
        }
    },

    EMPTY_SLOTS {
        /**
         * Fill the slots inside of the menu with the
         * specified filling type.
         *
         * @param menu    the menu to fill
         * @param buttons the buttons which are currently used
         * @return the filling buttons
         */
        @Override
        public List<Button> fillMenu(Menu menu, List<Button> buttons) {
            final List<Button> fillers = new ArrayList<>();

            for (int i = 0; i < menu.getSize(); i++) {
                boolean applicableSlot = true;

                for (Button button : buttons) {
                    if (button.getIndex() == i) {
                        applicableSlot = false;
                        break;
                    }
                }

                if (applicableSlot) {
                    fillers.add(new Button(i, menu.getFillerType().getType()).setData(menu.getFillerType().getData().getData()));
                }
            }

            return fillers;
        }
    };

    /**
     * Fill the slots inside of the menu with the
     * specified filling type.
     *
     * @param menu    the menu to fill
     * @param buttons the buttons which are currently used
     * @return the filling buttons
     */
    public abstract List<Button> fillMenu(Menu menu, List<Button> buttons);

}