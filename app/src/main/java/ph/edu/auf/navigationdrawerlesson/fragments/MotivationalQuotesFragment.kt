package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentMotivationalQuotesBinding
import ph.edu.auf.navigationdrawerlesson.FavoriteQuotesManager
import ph.edu.auf.navigationdrawerlesson.Quote

class MotivationalQuotesFragment : Fragment() {
    private var _binding: FragmentMotivationalQuotesBinding? = null
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
        _binding = FragmentMotivationalQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOpenDrawer.setOnClickListener {
            listener?.onOpenDrawer()
        }

        // List of motivational quotes
        val motivationalQuotes = listOf(
            Quote("The world is changing, and we must change with it.", "T'Challa, Black Panther"),
            Quote("Part of the journey is the end.", "Tony Stark, Avengers: Endgame"),
            Quote("The hardest choices require the strongest wills.", "Thanos, Avengers: Infinity War"),
            Quote("I think you look great. For what it’s worth, I’m sorry. Maybe I could have done better.", "Bruce Banner, The Avengers"),
            Quote("What is grief, if not love persevering?", "Vision, WandaVision")
        )

        fun displayRandomQuote() {
            val randomQuote = motivationalQuotes.random()
            binding.txtMotivationalQuote.text = "\"${randomQuote.text}\" – ${randomQuote.author}"
        }

        displayRandomQuote()

        binding.btnRandomizeMotivational.setOnClickListener {
            displayRandomQuote()
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

    data class Quote(val text: String, val author: String)
}