package io.github.nosequel.menu.pagination;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.navigation.NextPageButton;
import io.github.nosequel.menu.pagination.navigation.PreviousPageButton;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Getter
public enum NavigationPosition {

    TOP {
        /**
         * Get the navigation buttons
         * for the position type.
         *
         * @return the buttons
         */
        @Override
        public Button[] getNavigationButtons(PaginatedMenu menu) {
            final List<Button> buttons = new ArrayList<>(Arrays.asList(
                    new PreviousPageButton(0, menu),
                    new NextPageButton(8, menu)
            ));

            return buttons.toArray(new Button[0]);
        }

        /**
         * Get a list of buttons in the range of the
         * current menu's page.
         *
         * @param buttons the list of buttons to get the buttons in range from
         * @param menu    the menu to get the data from
         * @return the buttons in range
         */
        @Override
        public List<Button> getButtonsInRange(List<Button> buttons, PaginatedMenu menu) {
            final List<Button> buttonList = new ArrayList<>();

            final int size = menu.getSize();
            final int page = menu.getPage();

            final int maxElements = size - 9;

            final int start = ((page - 1) * maxElements);
            final int end = (start + maxElements) - 1;

            for (Button button : buttons) {
                if (button.getIndex() >= start && button.getIndex() <= end)
                    buttonList.add(button.clone()
                            .setIndex(button.getIndex() - ((maxElements * (page - 1))) + 9)
                    );
            }

            buttonList.addAll(menu.getNavigationBar());

            return buttonList;
        }
    },

    BOTTOM {
        /**
         * Get the navigation buttons
         * for the position type.
         *
         * @return the buttons
         */
        @Override
        public Button[] getNavigationButtons(PaginatedMenu menu) {
            final List<Button> buttons = new ArrayList<>(Arrays.asList(
                    new PreviousPageButton(menu.getSize() - 9, menu),
                    new NextPageButton(menu.getSize() - 1, menu)
            ));

            return buttons.toArray(new Button[0]);
        }

        /**
         * Get a list of buttons in the range of the
         * current menu's page.
         *
         * @param buttons the list of buttons to get the buttons in range from
         * @param menu    the menu to get the data from
         * @return the buttons in range
         */
        @Override
        public List<Button> getButtonsInRange(List<Button> buttons, PaginatedMenu menu) {
            final List<Button> buttonList = new ArrayList<>();

            final int size = menu.getSize();
            final int page = menu.getPage();

            final int maxElements = size - 9;

            final int start = (page - 1) * maxElements;
            final int end = (start + maxElements) - 1;

            for (Button button : buttons) {
                if (button.getIndex() >= start && button.getIndex() <= end)
                    buttonList.add(button.clone()
                            .setIndex(button.getIndex() - ((maxElements * (page - 1))))
                    );
            }

            buttonList.addAll(menu.getNavigationBar());

            return buttonList;
        }
    };

    /**
     * Get the navigation buttons
     * for the position type.
     *
     * @param menu the menu to get the data from
     * @return the buttons
     */
    public abstract Button[] getNavigationButtons(PaginatedMenu menu);

    /**
     * Get a list of buttons in the range of the
     * current menu's page.
     *
     * @param buttons the list of buttons to get the buttons in range from
     * @param menu    the menu to get the data from
     * @return the buttons in range
     */
    public abstract List<Button> getButtonsInRange(List<Button> buttons, PaginatedMenu menu);

}