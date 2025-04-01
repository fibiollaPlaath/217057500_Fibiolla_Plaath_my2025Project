package jetbrains.kotlin.course.alias.util

import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.state.GameStateService
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Component
import javax.annotation.PreDestroy

/**
 * OPTIONAL TASK 5
 *   - Saves game state when server shuts down.
 */
@Component
class ShutDownSaver(
    private val teamService: TeamService,
    private val cardService: CardService,
    private val gameResultsService: GameResultsService,
    private val gameStateService: GameStateService
) {
    @PreDestroy
    fun saveGameState() {
        println("Saving game state before shutdown")
        gameStateService.saveState(teamService, cardService, gameResultsService)
        println("Game state saved")
    }
}