package ph.edu.auf.navigationdrawerlesson

object FavoriteQuotesManager {
    private val favoriteQuotes = mutableListOf<Quote>()

    fun addQuote(quote: Quote) {
        favoriteQuotes.add(quote)
    }

    fun getQuotes(): List<Quote> {
        return favoriteQuotes
    }
}