package jetbrains.kotlin.course.alias.results

import jetbrains.kotlin.course.alias.team.Team
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Service

/**
 * TASK 6
 *   - Add type alias GameResult:
 *             1. Refers to List<Team>
 *
 *   - Add companion object:
 *             1. Declare gameHistory variable -> for storing the list of game results.
 *             2. By default, it must be initialized via empty list.
 *
 *   - Implement saveGameResults():
 *             1. Adds result to gameHistory.
 *             2. Before adding, check 2 requirements & throw error if they are broken;
 *                         1) result must not be empty
 *                         2) all team IDs must be in TeamService.teamsStorage
 *
 *   - Implement getAllGameResults():
 *             1. Returns reversed gameHistory list.
 */
typealias GameResult = List<Team>

@Service
class GameResultsService {
    companion object {
        val gameHistory: MutableList<GameResult> = mutableListOf()
    }

    fun saveGameResults(result: GameResult): Unit {
        if (result.isEmpty()) {
            throw IllegalArgumentException("Game result cannot be empty.")
        }

        val invalidTeamIds = result.filter { team ->
            !TeamService.teamsStorage.containsKey(team.id)
        }.map { it.id }

        if (invalidTeamIds.isNotEmpty()) {
            throw IllegalArgumentException("Invalid team ID(s): ${invalidTeamIds.joinToString(", ")}")
        }

        // Add to gameHistory if all validations pass
        gameHistory.add(result)
        println("Game result saved successfully: $result")

    }

    fun getAllGameResults(): List<GameResult> = gameHistory.toList().asReversed()
}
