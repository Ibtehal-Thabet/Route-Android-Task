package com.example.routeandroidtask.ui.productList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.Product
import com.example.routeandroidtask.R
import com.example.routeandroidtask.databinding.ActivityMainBinding
import com.example.routeandroidtask.databinding.FragmentProductListBinding
import com.example.routeandroidtask.databinding.FragmentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductListFragment: Fragment() {


    private lateinit var viewBinding: FragmentProductListBinding
    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentProductListBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private val adapter = ProductListAdapter(null)
    private fun initViews() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            showLoading()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            showError(it?:"Error")
        }

        viewBinding.productsRecyclerView.adapter = adapter
        viewModel.getProducts()
        viewModel.productsLiveData.observe(viewLifecycleOwner) {
                bindProducts(it!!)
        }

        adapter.onFavClickListener = ProductListAdapter.OnFavClickListener { position, product ->
        }
    }

    private fun showError(message: String) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = true
        viewBinding.successView.isVisible = false
        viewBinding.errorText.text = message
        viewBinding.btnTryAgain.setOnClickListener {
            viewModel.getProducts()
        }
    }

    private fun showLoading() {
        viewBinding.loadingView.isVisible = true
        viewBinding.errorView.isVisible = false
        viewBinding.successView.isVisible = false
    }

    private fun bindProducts(products: List<Product?>) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = false
        viewBinding.successView.isVisible = true
        adapter.bindProducts(products)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove the observer when the Fragment's view is destroyed
        viewModel.productsLiveData.removeObservers(viewLifecycleOwner)
    }
}