package jetbrains.kotlin.course.alias.state

import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.team.TeamService
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.stereotype.Service
import java.io.File

@Service
class GameStateService {
    private val json = Json { prettyPrint = true }
    private val file = File("game_state.json")

    fun saveState(
        teamService: TeamService,
        cardService: CardService,
        gameResultsService: GameResultsService
    ) {
        val gameState = GameState(
            teams = TeamService.teamsStorage,
            gameHistory = GameResultsService.gameHistory,
            teamIdCounter = teamService.getIdentifierCounter(),
            cardIdCounter = cardService.getIdentifierCounter()
        )
        file.writeText(json.encodeToString(gameState))
    }

    fun loadState(
        teamService: TeamService,
        cardService: CardService,
        gameResultsService: GameResultsService
    ) {
        if (!file.exists()) return

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
    }
}