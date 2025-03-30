package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import kotlinx.serialization.Serializable

/**
 * TASK 2
 *   - Create a data class:
 *             1. To store the information about teams.
 *             2. Must have 2 properties in primary constructor;
 *                      1) id of type Identifier -> to identify each team.
 *                      2) Points of type Int -> to store number of points in game -> Should be 0 by default.
 *             3. Additional property name -> initialise as "Team#n" -> Displayed in leaderboard.
 */
@Serializable
data class Team(
    val id: Identifier,
    var points: Int = 0
) {
    val name: String = "Team#${id + 1}"
}