# Implementation Plan - Darker Aesthetics

Darken the home background and refine the bottom navigation bar color to create a more immersive and high-contrast dark theme experience.

## Proposed Changes

### [Theming]

#### [MODIFY] [Color.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/ui/theme/Color.kt)
- **DeepNavy**: Darken from `0xFF0B0E14` to `0xFF05070A` for a deeper, more professional background.
- **CardBg**: Darken from `0xFF1E2228` to `0xFF0D1016` to better integrate the action cards and navigation bar with the new background.

### [Navigation UI]

#### [MODIFY] [bottomNavBar.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/ui/utils/bottomNavBar.kt)
- Ensure the `BottomNavBar` uses the updated `CardBg` and adjust alpha/borders if necessary to maintain visibility against the darker background.

## Verification Plan

### Manual Verification
- Review the `HomeSearchSelectedPreview` to verify the new darker background and navigation bar integration.
- Ensure text and glow effects remain vibrant and legible against the deeper colors.
