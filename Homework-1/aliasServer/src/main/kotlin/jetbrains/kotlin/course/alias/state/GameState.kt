package jetbrains.kotlin.course.alias.state

import jetbrains.kotlin.course.alias.results.GameResult
import jetbrains.kotlin.course.alias.team.Team
import kotlinx.serialization.Serializable

@Serializable
data class GameState(
    val teams: Map<Int, Team>,
    val gameHistory: List<GameResult>,
    val teamIdCounter: Int,
    val cardIdCounter: Int
)
