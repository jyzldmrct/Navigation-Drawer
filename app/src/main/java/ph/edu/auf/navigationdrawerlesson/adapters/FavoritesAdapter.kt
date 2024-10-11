package ph.edu.auf.navigationdrawerlesson.adapters

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.auf.navigationdrawerlesson.Quote
import ph.edu.auf.navigationdrawerlesson.databinding.ItemFavoriteQuoteBinding

class FavoritesAdapter(
    val quotes: MutableList<Quote>,
    private val onDeleteClick: (Quote) -> Unit
) : RecyclerView.Adapter<FavoritesAdapter.FavoritesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        val binding = ItemFavoriteQuoteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoritesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(quotes[position])
    }

    override fun getItemCount(): Int = quotes.size

    inner class FavoritesViewHolder(private val binding: ItemFavoriteQuoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(quote: Quote) {
            binding.tvFavoriteQuote.text = "\"${quote.text}\" - ${quote.author}"
            binding.btnDeleteQuote.setOnClickListener {
                showDeleteConfirmationDialog(quote)
            }
        }

        private fun showDeleteConfirmationDialog(quote: Quote) {
            AlertDialog.Builder(binding.root.context)
                .setTitle("Delete Quote")
                .setMessage("Are you sure you want to delete this quote?")
                .setPositiveButton("Yes") { _, _ ->
                    onDeleteClick(quote)
                }
                .setNegativeButton("No", null)
                .show()
        }
    }
}