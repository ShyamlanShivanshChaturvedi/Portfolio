# Implementation Plan - Enhance Projects Screen and Add Portfolio Project

Implement a themed Projects Screen, add the current "Portfolio" project to it, and provide a placeholder function for GitHub integration.

## User Review Required

> [!IMPORTANT]
> The GitHub link for the Portfolio project will be implemented as a placeholder function `onGithubClick()` as requested, so you can provide the specific link later.

## Proposed Changes

### Navigation

#### [MODIFY] [screen.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/navigation/screen.kt)
- Add `object Projects : Screen("Projects")` to the `Screen` sealed class.

#### [MODIFY] [appNavigation.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/navigation/appNavigation.kt)
- Add the `composable(Screen.Projects.route)` destination to the `NavHost`.

### Data Models

#### [NEW] [Project.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/projects/model/Project.kt)
- Define a `Project` data class with fields: `title`, `description`, `icon`, `techStack`, and `githubUrl`.

### UI Components

#### [NEW] [projects.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/projects/ui/projects.kt)
- Implement `ProjectsScreen` using a glassmorphic design consistent with the rest of the app.
- Create `ProjectCard` to display project details, tech stack chips, and an "Open GitHub" button.
- Add the "Portfolio App" project as the first entry.

#### [MODIFY] [homeActions.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/home/ui/homeActions.kt)
- Update `HomeActions` to accept an `onProjectClick` lambda.
- Hook up the "Project Cards" action to trigger navigation to the Projects screen.

#### [MODIFY] [home.kt](file:///D:/Github R/Portfolio/app/src/main/java/com/example/portfolio/home/ui/home.kt)
- Pass the navigation callback to `HomeActions`.

## Verification Plan

### Manual Verification
- Navigate to the Projects screen from the Home screen or the Bottom Navigation Bar.
- Verify that the "Portfolio App" project is displayed with its description and tech stack.
- Check that the "Open GitHub" button exists on the card.
- Ensure the theme (Dark/Light) is correctly applied and consistent with the Home and Skills screens.
