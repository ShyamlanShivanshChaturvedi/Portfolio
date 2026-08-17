# Walkthrough - Enhanced Project Cards

I have updated the action cards on the Home screen to feature a modern vertical layout with larger icons and themed glow effects.

## Changes Made

### Action Card Redesign
- **Vertical Orientation:** Icons are now placed directly above the labels for a cleaner, app-like look.
- **Side-by-Side Layout:** The "Projects" and "Experience" cards are now arranged in a row, making better use of screen width.
- **Larger Icons:** Icon size increased to **32.dp** within a **64.dp** circular container.
- **Themed Glow Effect:** Added a radial gradient background behind icons using the theme's selection color (Cyan/Purple) with a subtle transparency.
- **Improved Styling:** Increased corner radius to **24.dp** and set font weight to **Bold** for the titles.

## Verification Results

### UI Verification
Verified with Compose Previews in Light and Dark modes.

#### Enhanced Home UI (Light Theme)
![Enhanced Home UI](file:///D:/Github%20R/Portfolio/.artifacts/3f557ef8-1e4f-40b8-b400-3684d550cd9e/enhanced_cards_preview.png)

> [!TIP]
> The side-by-side layout works well for mobile screens. If you plan to add more cards in the future, we could consider a scrollable row or a grid layout.
