package hector.contreras.com.mexflikz.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hector.contreras.com.mexflikz.R
import hector.contreras.com.mexflikz.databinding.FragmentMoviesBinding
import hector.contreras.com.mexflikz.fragments.ListVideoFragment
import hector.contreras.com.mexflikz.helpers.*


class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(MoviesViewModel::class.java)

        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        generateMoviesSections()
    }


    private fun generateMoviesSections() {
        //Sections
        // Movies Sections
        val categories = arrayOf( CATEGORY_UPCOMING,CATEGORY_NOW_PLAYING, CATEGORY_POPULAR, CATEGORY_TOP)

        val ftransaction = parentFragmentManager.beginTransaction()
        for(category in categories) {
            val f = ListVideoFragment(TYPE_MOVIES,category)
            ftransaction.add(R.id.sectionsMoviesContainer,f,TYPE_MOVIES+category)
        }

        ftransaction.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}