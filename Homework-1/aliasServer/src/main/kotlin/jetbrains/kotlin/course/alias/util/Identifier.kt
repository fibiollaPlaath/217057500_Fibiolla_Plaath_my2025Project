package jetbrains.kotlin.course.alias.util

/**
 * TASK 1
 *   - Create type alias Identifier:
 *             1. Need to be alias for type Int.
 *
 *   - Create IdentifierFactory class:
 *             1. For generating unique identifiers.
 *             2. Should have special counter. Should be zero by default.
 *             3. Int property for storing last unique numbers.
 *
 *   - Create a uniqueIdentifier function:
 *             1. Return unique identifier by incrementing the counter and returning it.
 */
typealias Identifier = Int

class IdentifierFactory {
    private var counter: Identifier = 0

    fun uniqueIdentifier(): Identifier = counter++
}
