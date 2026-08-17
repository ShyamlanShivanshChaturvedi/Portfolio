# Implementation Plan - Enhanced Loading Screen with Logo

This plan outlines the enhancements for the `LoadingScreen` to include the app logo and improved animations, while maintaining theme consistency.

## User Review Required

> [!IMPORTANT]
> The `LoadingScreen` will now feature the `app_logo.jpg`. I will add a subtle pulsing animation to the logo to make it feel more dynamic.

## Proposed Changes

### UI Utilities

#### [MODIFY] [loadingScreen.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/ui/utils/loadingScreen.kt)

- Update `LoadingScreen` to include an `Image` component for `app_logo.jpg`.
- Implement an infinite pulsing animation (scale) for the logo using `rememberInfiniteTransition`.
- Use custom theme colors (`DeepNavy` for dark, `LightBg` for light) explicitly for the background to match the app's style.
- Arrange the logo, progress indicator, and message text in a visually balanced vertical layout.
- Add a subtle glow or shadow effect to the logo container.

## Verification Plan

### Automated Tests
- I will update the previews to verify the logo's appearance and the overall layout in both themes.

### Manual Verification
- The user can verify the new design using the updated previews in Android Studio.
