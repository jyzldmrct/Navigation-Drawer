package ph.edu.auf.navigationdrawerlesson

import android.util.Log

object FavoriteQuotesManager {
    private val favoriteQuotes = mutableListOf<Quote>()

    fun addQuote(quote: Quote) {
        favoriteQuotes.add(quote)
        Log.d("FavoriteQuotesManager", "Quote added: $quote")
    }

    fun getFavoriteQuotes(): List<Quote> {
        Log.d("FavoriteQuotesManager", "Getting favorite quotes: $favoriteQuotes")
        return favoriteQuotes
    }

    fun removeFavoriteQuote(quote: Quote) {
        favoriteQuotes.removeAll { it.text == quote.text }
        Log.d("FavoriteQuotesManager", "Quote removed: ${quote.text}")
    }
}
