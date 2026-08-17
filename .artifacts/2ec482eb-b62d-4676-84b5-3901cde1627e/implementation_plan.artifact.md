# Implementation Plan - Skills Section & Enhanced Layout

This plan covers the implementation of the **Skills** section with a vibrant, adaptive grid layout and its integration into the app's navigation.

## Proposed Changes

### [Feature: Skills]

#### [MODIFY] [skills.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/skills/ui/skills.kt)
- Implement `SkillsScreen` using a `Scaffold` and the existing `BottomNavBar`.
- Use a `LazyVerticalGrid` with adaptive columns:
    - 2 columns for smaller screens.
    - 3 columns for larger screens.
- Create `SkillCard` component:
    - **Vibrant Design**: Use gradients (Cyan to Purple) and semi-transparent backgrounds to match the "glow" theme.
    - **Content**: Title and a short description for each skill.
    - **Skills to Include**: Java, Spring Boot, Kotlin, Android Application, C, C++, and basic Golang.

#### [NEW] [Skill.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/skills/model/Skill.kt)
- Define a data class `Skill(val title: String, val info: String, val icon: ImageVector)`.

### [Navigation]

#### [MODIFY] [appNavigation.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/navigation/appNavigation.kt)
- Register the `Skills` route in the `NavHost`.
- Pass the `navController` or a navigation callback to the `SkillsScreen`.

### [Components]

#### [MODIFY] [home.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/home/ui/home.kt)
- Ensure the "Skills" navigation from the `BottomNavBar` works correctly.

## Verification Plan

### Automated Tests
- Use `render_compose_preview` for `SkillsPreview` to check the grid layout across different sizes.

### Manual Verification
- Deploy the app and navigate to the Skills section.
- Check the layout on phone (portrait) and tablet/emulator (landscape/large screen) to verify column count.
