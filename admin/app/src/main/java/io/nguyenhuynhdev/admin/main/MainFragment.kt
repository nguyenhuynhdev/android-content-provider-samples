package io.nguyenhuynhdev.admin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import io.nguyenhuynhdev.admin.R
import io.nguyenhuynhdev.admin.databinding.FragmentMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_main, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val documentAdapter = DocumentAdapter()
        binding.bindAdapter(documentAdapter)

        lifecycleScope.launch {
            mainViewModel.data.collectLatest {
                documentAdapter.submitData(it)
            }
        }
    }


    /**
     * Sets up the [RecyclerView] and binds [ArticleAdapter] to it
     */
    private fun FragmentMainBinding.bindAdapter(documentAdapter: DocumentAdapter) {
        rvMain.adapter = documentAdapter
        rvMain.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}