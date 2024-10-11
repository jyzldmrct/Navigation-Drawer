package ph.edu.auf.navigationdrawerlesson.helpers

class QuotesGenerator private constructor(private val quotes: List<String>) {

    fun getRandomQuote(): String {
        return quotes.random()
    }

    class Builder {
        private val quotes = mutableListOf<String>()

        fun addQuote(quote: String): Builder {
            quotes.add(quote)
            return this
        }

        fun build(): QuotesGenerator {
            return QuotesGenerator(quotes)
        }
    }
}