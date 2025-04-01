package jetbrains.kotlin.course.alias.state

import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.TeamService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File

/**
 * OPTIONAL TASK 4
 *   - Create persistence manager -> Handles saving and loading of game state.
 *   - Add method -> Checks if saved file exist.
 */
@Service
class GameStateService {
    private val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }
    private val file = File("src/main/resources/static/game_state.json")

    fun saveState(teamService: TeamService, cardService: CardService, gameResultsService: GameResultsService) {
        val gameState = GameState(
            teams = TeamService.teamsStorage,
            gameHistory = GameResultsService.gameHistory,
            teamIdCounter = teamService.getIdentifierCounter(),
            cardIdCounter = cardService.getIdentifierCounter()
        )
        println("Saving game state: $gameState")

        if (!file.parentFile.exists()) {
            file.parentFile.mkdirs()
            println("Created directory for game state: ${file.parentFile.absolutePath}")
        }

        file.writeText(json.encodeToString(gameState))
        println("Game state saved successfully at: ${file.absolutePath}")

    }

    fun loadState(teamService: TeamService, cardService: CardService, gameResultsService: GameResultsService) {
        if (!file.exists()) {
            println("No saved game state found at: ${file.absolutePath}")
            return
        }

        println("Loading game state from: ${file.absolutePath}")
        val gameState = json.decodeFromString<GameState>(file.readText())

        // Restore teams
        TeamService.teamsStorage.clear()
        TeamService.teamsStorage.putAll(gameState.teams)

        // Restore game history
        GameResultsService.gameHistory.clear()
        GameResultsService.gameHistory.addAll(gameState.gameHistory)

        // Restore counters
        teamService.setIdentifierCounter(gameState.teamIdCounter)
        cardService.setIdentifierCounter(gameState.cardIdCounter)
        println("Game state loaded successfully!")
    }

    fun isSavedGameAvailable(): Boolean {
        if (!file.exists() || file.length().toInt() == 0) {
            println("Saved game not available at: ${file.absolutePath}")
            return false
        }

        println("Checking saved game availability at: ${file.absolutePath}")
        val gameState = json.decodeFromString<GameState>(file.readText())
        return gameState.teams.isNotEmpty() || gameState.gameHistory.isNotEmpty()


    }
}