package jetbrains.kotlin.course.alias.util

import jetbrains.kotlin.course.alias.card.CardService
import jetbrains.kotlin.course.alias.results.GameResultsService
import jetbrains.kotlin.course.alias.state.GameStateService
import jetbrains.kotlin.course.alias.team.TeamService
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class StartupLoader(
    private val teamService: TeamService,
    private val cardService: CardService,
    private val gameResultsService: GameResultsService,
    private val gameStateService: GameStateService
) {
    @PostConstruct
    fun loadGameState() {
        println("Loading game state on startup...")
        gameStateService.loadState(teamService, cardService, gameResultsService)
        println("Game state loaded successfully.")
    }
}