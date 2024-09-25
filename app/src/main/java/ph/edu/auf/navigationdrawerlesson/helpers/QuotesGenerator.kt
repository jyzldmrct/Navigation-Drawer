package ph.edu.auf.navigationdrawerlesson.helpers

import kotlin.random.Random

class QuotesGenerator private constructor(private val quotes: List<String>){
    fun getRandomQuote(): String {
        val random = Random.nextInt(0,quotes.size)
        return quotes[random]
    }

    class QuoteBuilder{
        internal val quotes = mutableListOf<String>()
        fun build() = QuotesGenerator(quotes)
    }

    companion object {
        private val loveQuotes = listOf(
            "I love you.",
            "I love you more than yesterday.",
            "I love you more than I did yesterday.",
            "I love you more than I did yesterday and less than I will tomorrow.",
        )

        private val motivationalQuotes = listOf(
            "You are capable of amazing things.",
            "You are capable of more than you know.",
            "You are capable of more than you think.",
            "You are capable of more than you think you are.",
        )

        fun generateLoveQuotes(): QuoteBuilder {
            return QuoteBuilder().apply { quotes.addAll(loveQuotes) }
        }

        fun generateAllQuotes(): QuoteBuilder {
            val allQuotes = mutableListOf<String>()
            allQuotes.addAll(loveQuotes)
            allQuotes.addAll(motivationalQuotes)
            return QuoteBuilder().apply { quotes.addAll(allQuotes) }
        }

    }

}