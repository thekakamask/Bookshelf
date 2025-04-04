package com.dcac.bookshelf

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.semantics.getOrNull
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.SemanticsNodeInteractionCollection
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.rules.ActivityScenarioRule

/*These three functions are extensions to the AndroidComposeTestRule class, enabling you to find semantic nodes in a Compose UI using string resource IDs instead of literal strings.
(Compose UI) using string resource IDs instead of string literals.
They extend AndroidComposeTestRule to search for semantic nodes via string resource IDs, using the methods
onNodeWithText, onNodeWithContentDescription, and onNodeWithTag methods of the Compose UI test API. Instead of passing a literal string, they pass
the string corresponding to the resource identifier.

AndroidComposeTestRule: This is a test rule for Compose UI tests in Android. It allows you to configure and control
activity state during tests.
ComponentActivity: This is a base class for Android activities that support Jetpack components.
SemanticsNodeInteraction: This class represents a semantic node in a Compose user interface. It lets you interact with the
node for testing purposes.

These functions are useful for UI testing, as they enable strings and content descriptions to be referenced by their
resource identifiers, making tests more robust and less likely to fail due to string modifications.*/


fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>.onNodeWithStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithText(activity.getString(id))

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>
        .onNodeWithContentDescriptionForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithContentDescription(activity.getString(id))

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>
        .onNodeWithTagForStringId(
    @StringRes id: Int
): SemanticsNodeInteraction = onNodeWithTag(activity.getString(id))

fun <A : ComponentActivity> AndroidComposeTestRule<ActivityScenarioRule<A>, A>
        .onNodeWithTagValue(tag: String, useUnmergedTree: Boolean = false): SemanticsNodeInteraction =
    onNodeWithTag(tag, useUnmergedTree)

fun SemanticsNodeInteractionsProvider.onAllNodesWithTagPrefix(prefix: String): SemanticsNodeInteractionCollection {
    return onAllNodes(
        SemanticsMatcher("TestTag starts with $prefix") { node ->
            val tag = node.config.getOrNull(SemanticsProperties.TestTag)
            tag?.startsWith(prefix) == true
        }
    )
}