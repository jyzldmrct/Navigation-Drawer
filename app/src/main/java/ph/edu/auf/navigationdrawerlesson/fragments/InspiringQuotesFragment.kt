package ph.edu.auf.navigationdrawerlesson.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ph.edu.auf.navigationdrawerlesson.R
import ph.edu.auf.navigationdrawerlesson.databinding.FragmentInspiringQuotesBinding

class InspiringQuotesFragment : Fragment() {

        private var _binding: FragmentInspiringQuotesBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            // Inflate the layout for this fragment
            _binding = FragmentInspiringQuotesBinding.inflate(inflater,container,false)

            return binding.root
        }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
}