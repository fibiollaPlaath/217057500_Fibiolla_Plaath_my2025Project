package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.IdentifierFactory
import jetbrains.kotlin.course.alias.util.words
import org.springframework.stereotype.Service

/**
 * TASK 5
 * Add several properties and methods.
 *
 *   - Add identifierFactory:
 *             1. Of type IdentifierFactory -> to generate identifiers for each card.
 *             2. Add default value -> by creating new instance of IdentifierFactory class.
 *
 *   - Add cards:
 *             3. That stores a list of cards.
 *             4. Initialize it by calling generateCards().
 *
 *   - Add companion object:
 *             1. Declare WORDS_IN_CARD const variable -> to store number of words for the cards.
 *             2. Assign value 4 to it.
 *             3. Declare cardsAmount -> stores possible number of cards: words.size / WORDS_IN_CARD
 *             4. Project contains predefined list of words: words
 *
 *   - Add toWords function:
 *             1. Extension function for List<String>.
 *             2. Converts each element from this list into Word.
 *
 *   - Implement generateCards() to:
 *             1. Shuffles the words list.
 *             2. Splits it into chunks with WORDS_IN_CARD.
 *             3. Takes cardsAmount chunks for cardsAmount cards.
 *             4. Creates a new Card for each chunk.
 *
 *   - Implement getCardByIndex():
 *             1. Accepts index & the Card at this index.
 *             2. If card does not exist, throw error.
 */
@Service
class CardService(
    private var identifierFactory: IdentifierFactory = IdentifierFactory()
) {
    private val cards: List<Card> = generateCards()

    companion object {
        private const val WORDS_IN_CARD = 4

        val cardsAmount: Int = words.size / WORDS_IN_CARD
    }

    private fun generateCards(): List<Card> {
        return words.shuffled()
            .chunked(WORDS_IN_CARD)
            .take(cardsAmount)
            .map { chunk ->
                Card(
                    id = identifierFactory.uniqueIdentifier(),
                    words = chunk.toWords()
                )
            }
    }

    private fun List<String>.toWords(): List<Word> = this.map { Word(it) }

    fun getCardByIndex(index: Int): Card {
        return cards.getOrNull(index) ?: error("Card with index $index not found.")
    }

    fun getIdentifierCounter(): Int = identifierFactory.getCounter()
    fun setIdentifierCounter(value: Int) = identifierFactory.setCounter(value)
}
