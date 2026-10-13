package src;

import java.awt.*;

public class ResponsiveGridLayout implements LayoutManager2 {

        private final int minCardWidth = 140;
        private final int maxCardWidth = 180;
        private final int gap = 14;

    
    // Ideal size of the layout container
    @Override 
    public Dimension preferredLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();
            int count = parent.getComponentCount();

            int columns = Math.max(1, Math.min(count, 3));
            int width = columns * maxCardWidth
                    + (columns - 1) * gap
                    + insets.left + insets.right;

            int rows = count == 0 ? 0
                    : (count + columns - 1) / columns;

            int height = rows == 0 ? insets.top + insets.bottom
                    : rows * (maxCardWidth * 4 / 3)
                    + (rows - 1) * gap
                    + insets.top + insets.bottom;
            return new Dimension(width, height);
        }
    }

    // Minimum size of the layout container
    @Override
    public Dimension minimumLayoutSize(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();

            return new Dimension(
                minCardWidth + insets.left + insets.right,
                minCardWidth * 4 / 3 + insets.top + insets.bottom
            );
        }
    }

    // Calculate the position and size of every anime card
    @Override 
    public void layoutContainer(Container parent) {
        synchronized (parent.getTreeLock()) {
            Insets insets = parent.getInsets();

            int availableWidth = parent.getWidth()
                - insets.left- insets.right;

            int componentCount = parent.getComponentCount();

            if (componentCount == 0) {
                return;
            }

            // Determine the number of columns
            int columns = Math.max(1, 
                (availableWidth + gap) 
                / (minCardWidth + gap)
            );
            
            // Calculate the width of each card
            int cardWidth = Math.min(
                    maxCardWidth,
                    (availableWidth - (columns - 1) * gap) / columns
            );

            // Never shrink cards below the minimum width
            cardWidth = Math.max(minCardWidth, cardWidth);

            // Maintain a 3:4 width-to-height ratio
            int cardHeight = cardWidth * 4 / 3;

            // Position every card
            for (int i = 0; i < componentCount; i++) {

                int row = i / columns;
                int column = i % columns;

                int x = insets.left + column * (cardWidth + gap);
                int y = insets.top + row * (cardHeight + gap);

                Component component = parent.getComponent(i);

                component.setBounds(
                    x,
                    y,
                    cardWidth,
                    cardHeight
                );
                
            }
        }


    }

     // Maximum size of the layout container
    @Override
    public Dimension maximumLayoutSize(Container target) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0.0f;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0.0f;
    }

    @Override
    public void invalidateLayout(Container target) {
        // No cached layout info to clear
    }

    @Override
    public void addLayoutComponent(Component component, Object constraints) {
        // No constraints are needed for this layout
    }

    @Override
    public void addLayoutComponent(String name, Component component) {
        // No component needed
    }

    @Override
    public void removeLayoutComponent(Component component) {
        // No extra bookkeeping needed
    }
    
}
