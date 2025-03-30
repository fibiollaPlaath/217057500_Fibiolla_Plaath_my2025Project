package jetbrains.kotlin.course.alias.team

import jetbrains.kotlin.course.alias.util.Identifier
import jetbrains.kotlin.course.alias.util.IdentifierFactory
import org.springframework.stereotype.Service

/**
 * TASK 3
 * Game logic for teams.
 *
 *   - Add identifierFactory property:
 *             1. With type IdentifierFactory -> to generate identifiers to each team.
 *             2. Add default value by creating new instance of IdentifierFactory class.
 *
 *   - Add companion object:
 *             1. Declare teamStorage variable -> to store all previous teams.
 *             2. Storage of type MutableMap -> which maps Identifier to Team.
 *             3. Initialize it via empty map.
 *
 *   - Implement generateTeamsForOneRound():
 *             1. Must generate a list of teams.
 *             2. Store them in teamsStorage map.
 *             3. Use identifierFactory -> to create new teams by generating new ID.
 *             4. Create method to save game results for leaderboard.
 */
@Service
class TeamService(
    private var identifierFactory: IdentifierFactory = IdentifierFactory()
) {
    companion object {
        val teamsStorage: MutableMap<Identifier, Team> = mutableMapOf()
    }

    fun generateTeamsForOneRound(teamsNumber: Int): List<Team> {
        val newTeams = List(teamsNumber) {
            val id = identifierFactory.uniqueIdentifier()
            val team = Team(id)

            teamsStorage[id] = team
            team
        }
        return newTeams
    }

    fun getIdentifierCounter(): Int = identifierFactory.getCounter()
    fun setIdentifierCounter(value: Int) = identifierFactory.setCounter(value)
}
