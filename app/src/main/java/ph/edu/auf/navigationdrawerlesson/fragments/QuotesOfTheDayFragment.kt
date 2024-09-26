package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentQuotesOfTheDayBinding
import ph.edu.auf.navigationdrawerlesson.FavoriteQuotesManager
import ph.edu.auf.navigationdrawerlesson.Quote

class QuotesOfTheDayFragment : Fragment() {
    private var _binding: FragmentQuotesOfTheDayBinding? = null
    private val binding get() = _binding!!
    private var listener: OnOpenDrawerListener? = null

    interface OnOpenDrawerListener {
        fun onOpenDrawer()
    }

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


        binding.btnOpenDrawer.setOnClickListener {
            listener?.onOpenDrawer()

            binding.btnSaveQuoteOfTheDay.setOnClickListener (View.OnClickListener {
                val quote = binding.txtQuoteOfTheDay.text.toString()
                FavoriteQuotesManager.addQuote(Quote(quote, ""))
            })

        }

        val quotesOfTheDay = listOf(
            "Sometimes the hardest part is letting go of what we thought would happen.",
            "Love isn't always easy, but it's always worth fighting for.",
            "We create our own paths; it's up to us to decide where they lead.",
            "It's the little moments that make the biggest impact on our lives.",
            "True connection comes from being honest with ourselves and each other."
        )

        var currentQuote: String? = null

        fun displayRandomQuote() {
            currentQuote = quotesOfTheDay.random()
            binding.txtQuoteOfTheDay.text = currentQuote
        }

        displayRandomQuote()

        binding.btnRandomizeQuoteOfTheDay.setOnClickListener {
            displayRandomQuote()
        }

        binding.btnSaveQuoteOfTheDay.setOnClickListener {
            currentQuote?.let { quote ->
                FavoriteQuotesManager.addQuote(Quote(quote, ""))
            }
        }
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