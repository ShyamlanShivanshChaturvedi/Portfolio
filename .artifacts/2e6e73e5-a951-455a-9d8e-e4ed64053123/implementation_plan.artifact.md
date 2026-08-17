# Refactor Scaffold and Bottom Navigation to Features Container

This plan outlines the steps to move the common `Scaffold` and `BottomNavBar` from individual feature screens (`Home`, `Skills`, `Projects`) into a centralized `FeaturesScreen` in `features.kt`. This will improve code maintainability and provide a smoother navigation experience.

## Proposed Changes

### [Component: Features Container]

#### [MODIFY] [features.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/features/features.kt)
- Rename `Features` to `FeaturesScreen`.
- Implement `Scaffold` with `BottomNavBar`.
- Add a nested `NavHost` to handle navigation between `Home`, `Skills`, and `Projects`.
- Manage navigation state locally within `FeaturesScreen`.

### [Component: Navigation]

#### [MODIFY] [appNavigation.kt](file:///D:/D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/navigation/appNavigation.kt)
- Introduce a new route or update existing routes to point to `FeaturesScreen`.
- A good approach is to have a "Main" route that hosts the `FeaturesScreen`.
- I'll add `Screen.Main` to `screen.kt` and use it in `AppNavigation`.

#### [MODIFY] [screen.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/navigation/screen.kt)
- Add `object Main : Screen("Main")`.

### [Component: Feature Screens]

#### [MODIFY] [home.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/features/home/ui/home.kt)
- Remove `Scaffold` and `BottomNavBar`.
- Remove `onNavBarItemClick` parameter as navigation will be handled by the parent container (or via the nested `navController`).
- Simplify the layout to just the content.

#### [MODIFY] [skills.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/features/skills/ui/skills.kt)
- Remove `Scaffold` and `BottomNavBar`.
- Remove `onNavBarItemClick` parameter.

#### [MODIFY] [projects.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/features/projects/ui/projects.kt)
- Remove `Scaffold` and `BottomNavBar`.
- Remove `onNavBarItemClick` parameter.

## Verification Plan

### Automated Tests
- I will verify that the project builds successfully after refactoring.
- I will check for any broken references in Previews.

### Manual Verification
- Deploy the app to a device/emulator.
- Verify that navigating between Home, Skills, and Projects via the Bottom Navigation Bar works as expected.
- Ensure the `Scaffold` and `BottomNavBar` persist correctly across these screens without flickering or being recreated.
