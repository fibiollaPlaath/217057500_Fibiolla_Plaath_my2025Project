package jetbrains.kotlin.course.alias.state

import jetbrains.kotlin.course.alias.results.GameResult
import jetbrains.kotlin.course.alias.team.Team
import kotlinx.serialization.Serializable

/**
 * OPTIONAL TASK 3
 *   - Defines game state -> for saving and loading.
 *   - Make serializable
 */
@Serializable
data class GameState(
    val teams: Map<Int, Team>,
    val gameHistory: MutableList<GameResult>,
    val teamIdCounter: Int,
    val cardIdCounter: Int
)
