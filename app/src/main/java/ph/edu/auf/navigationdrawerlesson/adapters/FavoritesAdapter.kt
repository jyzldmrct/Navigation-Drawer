package ph.edu.auf.navigationdrawerlesson.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.auf.navigationdrawerlesson.databinding.ItemQuoteBinding
import ph.edu.auf.navigationdrawerlesson.Quote

class FavoritesAdapter(private val quotes: List<Quote>) : RecyclerView.Adapter<FavoritesAdapter.QuoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val binding = ItemQuoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(quotes[position])
    }

    override fun getItemCount(): Int = quotes.size

    class QuoteViewHolder(private val binding: ItemQuoteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(quote: Quote) {
            binding.txtQuote.text = "\"${quote.text}\""
            binding.txtAuthor.text = "- ${quote.author}"
        }
    }
}