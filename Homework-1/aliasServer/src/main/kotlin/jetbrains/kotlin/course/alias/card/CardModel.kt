package jetbrains.kotlin.course.alias.card

import jetbrains.kotlin.course.alias.util.Identifier

/**
 * TASK 4
 * Create 2 classes to work with cards.
 *
 *   - Create value class Word:
 *             1. With word property of type string -> to store a word.
 *
 *   - Create data class Card:
 *             1. To store information for each card.
 *             2. Must store an id of type Identifier
 *             3. Must store a list of words.
 *             4. These don't have default values.
 *             5. Must be defined in primary constructor.
 */
@JvmInline
value class Word(
    val word: String
)

data class Card(
    val id: Identifier,
    val words: List<Word>
)
