package com.dongeul.adpager.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.dongeul.adpager.databinding.FragmentHomeBinding
import com.dongeul.adpager.model.Content
import com.dongeul.adpager.model.Result
import com.dongeul.adpager.viewmodel.PagerViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : PagerViewModel by viewModels()
    private val pagedAdapter : PagerAdapter by lazy {
        PagerAdapter().apply {
            listener = {content->
                val directions = HomeFragmentDirections.actionHomeFragmentToDetailFragment(content)
                findNavController().navigate(directions)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pager.adapter =  pagedAdapter
            lifecycleScope.launch {
                viewModel.post.collectLatest {result->
                    when (result) {
                        is Result.Loading->{
                            loading.visibility = View.VISIBLE

                        }
                        is Result.Success->{
                            loading.visibility = View.GONE
                            pagedAdapter.submitList(result.data)
                        }
                    }
                }
            }
        }
    }
}