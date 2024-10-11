package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import ph.edu.auf.navigationdrawerlesson.FavoriteQuotesManager
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentQuotesOfTheDayBinding
import ph.edu.auf.navigationdrawerlesson.Quote

class QuotesOfTheDayFragment : Fragment() {
    private var _binding: FragmentQuotesOfTheDayBinding? = null
    private val binding get() = _binding!!

    private val quotesOfTheDay = listOf(
        Quote("The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"),
        Quote("The future belongs to those who believe in the beauty of their dreams.", "Eleanor Roosevelt"),
        Quote("Do not watch the clock. Do what it does. Keep going.", "Sam Levenson"),
        Quote("Keep your face always toward the sunshine—and shadows will fall behind you.", "Walt Whitman"),
        Quote("The best way to predict the future is to create it.", "Peter Drucker")
    )

    interface OnOpenDrawerListener {
        fun onOpenDrawer()
    }

    private var listener: OnOpenDrawerListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnOpenDrawerListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnOpenDrawerListener")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuotesOfTheDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayRandomQuote()

        binding.btnSaveQuote.setOnClickListener {
            val quoteText = binding.txtQuoteOfTheDay.text.toString()
            val quote = Quote(quoteText, "Author")
            FavoriteQuotesManager.addQuote(quote)
            showQuoteSavedDialog()
        }

        binding.btnRandomizeQuoteOfTheDay.setOnClickListener {
            displayRandomQuote()
        }

        binding.btnOpenDrawer.setOnClickListener {
            listener?.onOpenDrawer()
        }
    }

    private fun displayRandomQuote() {
        val randomQuote = quotesOfTheDay.random()
        binding.txtQuoteOfTheDay.text = "\"${randomQuote.text}\" – ${randomQuote.author}"
    }

    private fun showQuoteSavedDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Quote Saved")
            .setMessage("Your quote has been saved successfully.")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}