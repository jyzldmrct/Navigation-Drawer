package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentLoveQuotesBinding
import ph.edu.auf.navigationdrawerlesson.FavoriteQuotesManager
import ph.edu.auf.navigationdrawerlesson.Quote

class LoveQuotesFragment : Fragment() {
    private var _binding: FragmentLoveQuotesBinding? = null
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
        _binding = FragmentLoveQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOpenDrawer.setOnClickListener {
            listener?.onOpenDrawer()
        }

        val loveQuotes = listOf(
            Quote("I didn’t know what love was until you came into my life.", "Gap the Series"),
            Quote("Every second I spend with you feels like home.", "The Loyal Pin"),
            Quote("I don’t need the world; I just need you by my side.", "Gap the Series"),
            Quote("Even in a crowd, my eyes always find you.", "The Loyal Pin"),
            Quote("You’re my person, my always, my forever.", "Gap the Series")
        )

        var currentQuote: Quote? = null

        fun displayRandomQuote() {
            currentQuote = loveQuotes.random()
            binding.txtLoveQuote.text = "\"${currentQuote!!.text}\" – ${currentQuote!!.author}"
        }

        displayRandomQuote()

        binding.btnRandomizeLove.setOnClickListener {
            displayRandomQuote()
        }

        binding.btnSaveLove.setOnClickListener {
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