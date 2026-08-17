# Implementation Plan - Redesign Profile UI

Redesign the Profile screen to be more visually appealing, following a "Premium Glassmorphic" style with better color integration and smoother transitions.

## User Review Required

> [!IMPORTANT]
> I will be using a more aggressive "Dark Mode" aesthetic with neon gradients (Cyan and Purple) to match the "Premium" feel you requested.

## Proposed Changes

### UI Redesign

#### [MODIFY] [ProfileScreen.kt](file:///D:/Github%20R/Portfolio/app/src/main/java/com/example/portfolio/features/profile/ui/ProfileScreen.kt)
- **Profile Header**: Redesign the avatar section with a double-layered glow effect (Cyan and Purple).
- **Glassmorphic Cards**: Use a more refined `CardBg` with subtle borders that catch the "glow" from the background.
- **Neon Gradients**: Apply the `ButtonGradient` (Cyan to Purple) to primary actions like "Save Changes" and "Edit Profile".
- **Edit Mode Overhaul**:
    - Use `TextField` with custom styling (no box, just a bottom line with a glow).
    - Add icons inside the text fields for better context.
- **Logout Action**: Move the logout button to a more "Settings" style section at the bottom, using a subtle but distinct red-tinted glass effect.
- **Animations**: Use `AnimatedContent` or `AnimatedVisibility` for a smoother transition between "View" and "Edit" modes.

## Verification Plan

### Manual Verification
- Deploy to device and verify the new look.
- Test the "Edit" transition smoothness.
- Ensure the neon gradients look good in both light and dark modes (though optimized for dark).
