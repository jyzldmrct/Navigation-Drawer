package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentInspiringQuotesBinding
import ph.edu.auf.navigationdrawerlesson.FavoriteQuotesManager
import ph.edu.auf.navigationdrawerlesson.Quote

class InspiringQuotesFragment : Fragment() {
    private var _binding: FragmentInspiringQuotesBinding? = null
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
        _binding = FragmentInspiringQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOpenDrawer.setOnClickListener {
            listener?.onOpenDrawer()
        }

        val inspiringQuotes = listOf(
            Quote("No man can win every battle, but no man should fall without a struggle.", "Peter Parker, Spider-Man"),
            Quote("It’s not about how much we lost, it’s about how much we have left.", "Tony Stark, Avengers: Endgame"),
            Quote("The only thing you really fight for is yourself.", "Natasha Romanoff, Black Widow"),
            Quote("I can do this all day.", "Steve Rogers, Captain America"),
            Quote("We never lose our demons, we only learn to live above them.", "The Ancient One, Doctor Strange")
        )

        var currentQuote: Quote? = null

        fun displayRandomQuote() {
            currentQuote = inspiringQuotes.random()
            binding.txtInspiringQuote.text = "\"${currentQuote!!.text}\" – ${currentQuote!!.author}"
        }

        displayRandomQuote()

        binding.btnRandomizeInspiring.setOnClickListener {
            displayRandomQuote()
        }

        binding.btnSaveInspiring.setOnClickListener {
            currentQuote?.let { quote ->
                FavoriteQuotesManager.addQuote(quote)
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