package ph.edu.auf.navigationdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import ph.edu.auf.navigationdrawerlesson.FavoriteQuotesManager
import ph.edu.auf.navigationdrawerlesson.adapters.FavoritesAdapter
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentFavoritesQuotesBinding
import ph.edu.auf.navigationdrawerlesson.Quote

class FavoriteQuotesFragment : Fragment() {
    private var _binding: FragmentFavoritesQuotesBinding? = null
    private val binding get() = _binding!!
    private var listener: OnOpenDrawerListener? = null
    private lateinit var adapter: FavoritesAdapter

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
        _binding = FragmentFavoritesQuotesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnOpenDrawer.setOnClickListener {
            listener?.onOpenDrawer()
        }

        setupRecyclerView()
        loadFavoriteQuotes()
    }

    private fun setupRecyclerView() {
        adapter = FavoritesAdapter(mutableListOf()) { quote ->
            FavoriteQuotesManager.removeFavoriteQuote(quote)
            loadFavoriteQuotes()
        }
        binding.recyclerFavorites.layoutManager = LinearLayoutManager(context)
        binding.recyclerFavorites.adapter = adapter
    }

    private fun loadFavoriteQuotes() {
        val favoriteQuotes = FavoriteQuotesManager.getFavoriteQuotes()
        adapter.apply {
            quotes.clear()
            quotes.addAll(favoriteQuotes)
            notifyDataSetChanged()
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