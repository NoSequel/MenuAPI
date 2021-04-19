package io.github.nosequel.menu.pagination;

import io.github.nosequel.menu.buttons.Button;
import io.github.nosequel.menu.pagination.navigation.NextPageButton;
import io.github.nosequel.menu.pagination.navigation.PreviousPageButton;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum NavigationPosition {

    TOP(-9) {
        /**
         * Get the navigation buttons
         * for the position type.
         *
         * @return the buttons
         */
        @Override
        public Button[] getNavigationButtons(PaginatedMenu menu) {
            return new Button[]{
                    new PreviousPageButton(0, menu),
                    new NextPageButton(8, menu)
            };
        }
    },
    BOTTOM(9) {
        /**
         * Get the navigation buttons
         * for the position type.
         *
         * @return the buttons
         */
        @Override
        public Button[] getNavigationButtons(PaginatedMenu menu) {
            return new Button[]{
                    new PreviousPageButton(menu.getSize() - 9, menu),
                    new NextPageButton(menu.getSize() - 1, menu)
            };
        }
    };

    private final int buttonIndexIncrementation;

    /**
     * Get the navigation buttons
     * for the position type.
     *
     * @param menu the menu to get the data from
     * @return the buttons
     */
    public abstract Button[] getNavigationButtons(PaginatedMenu menu);

}