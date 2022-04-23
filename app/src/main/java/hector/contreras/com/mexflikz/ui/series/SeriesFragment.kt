package hector.contreras.com.mexflikz.ui.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import hector.contreras.com.mexflikz.R
import hector.contreras.com.mexflikz.databinding.FragmentSeriesBinding
import hector.contreras.com.mexflikz.fragments.ListVideoFragment
import hector.contreras.com.mexflikz.helpers.CATEGORY_LATEST
import hector.contreras.com.mexflikz.helpers.CATEGORY_POPULAR
import hector.contreras.com.mexflikz.helpers.CATEGORY_TOP
import hector.contreras.com.mexflikz.helpers.TYPE_TVSHOW

class SeriesFragment : Fragment() {

    private var _binding: FragmentSeriesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val seriesViewModel =
            ViewModelProvider(this).get(SeriesViewModel::class.java)

        _binding = FragmentSeriesBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textDashboard
//        seriesViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        generateTvShowSections()
        return root
    }

    private fun generateTvShowSections() {
        //Sections
        // TV shows Sections
        val categories = arrayOf( CATEGORY_POPULAR, CATEGORY_TOP)

        val ftransaction = parentFragmentManager.beginTransaction()
        for(category in categories) {
            val f = ListVideoFragment(TYPE_TVSHOW,category)
            ftransaction.add(R.id.sectionsTvShowContainer,f, TYPE_TVSHOW +category)
        }
        ftransaction.commit()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}