package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ph.edu.auf.navigationdrawerlesson.R
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentQuotesOfTheDayBinding
import ph.edu.auf.navigationdrawerlesson.helpers.QuotesGenerator

class QuotesOfTheDayFragment : Fragment() {

    private var _binding: FragmentQuotesOfTheDayBinding? = null
    private val binding get() = _binding!!
    private lateinit var quotesGenerator: QuotesGenerator
    private var currentQuote: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentQuotesOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize QuotesGenerator and get the first random quote
        quotesGenerator = QuotesGenerator.generateAllQuotes().build()
        currentQuote = quotesGenerator.getRandomQuote()
        binding.txtQuoteOfDay.text = currentQuote

        // Set up the button to randomize the quote
        binding.btnRandomizeQuote.setOnClickListener {
            currentQuote = quotesGenerator.getRandomQuote()
            binding.txtQuoteOfDay.text = currentQuote
        }

        // Set up the button to save/remove the quote from favorites
        binding.btnFavoriteQuote.setOnClickListener {
            toggleFavoriteQuote(currentQuote)
        }
    }

    /**
     * Toggles saving/removing the current quote from the favorites list using SharedPreferences
     */
    private fun toggleFavoriteQuote(quote: String) {
        val sharedPreferences = activity?.getSharedPreferences("FavoriteQuotes", Context.MODE_PRIVATE)
        val editor = sharedPreferences?.edit()

        // Retrieve current favorites from SharedPreferences
        val favoriteQuotes = sharedPreferences?.getStringSet("favorites", mutableSetOf()) ?: mutableSetOf()

        if (favoriteQuotes.contains(quote)) {
            // If the quote is already in favorites, remove it
            favoriteQuotes.remove(quote)
            editor?.putStringSet("favorites", favoriteQuotes)?.apply()
            Toast.makeText(requireContext(), "Quote removed from favorites", Toast.LENGTH_SHORT).show()
            binding.btnFavoriteQuote.text = "Save to Favorites"
        } else {
            // Add the current quote to the favorites
            favoriteQuotes.add(quote)
            editor?.putStringSet("favorites", favoriteQuotes)?.apply()
            Toast.makeText(requireContext(), "Quote added to favorites", Toast.LENGTH_SHORT).show()
            binding.btnFavoriteQuote.text = "Remove from Favorites"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
