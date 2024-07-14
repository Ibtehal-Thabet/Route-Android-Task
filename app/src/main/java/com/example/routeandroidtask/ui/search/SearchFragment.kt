package com.example.routeandroidtask.ui.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.domain.model.Product
import com.example.routeandroidtask.R
import com.example.routeandroidtask.databinding.FragmentSearchBinding
import com.example.routeandroidtask.ui.productList.ProductListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment: Fragment() {

    private lateinit var viewBinding: FragmentSearchBinding

    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ProductListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        viewBinding.lifecycleOwner = viewLifecycleOwner

        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    private val adapter = SearchAdapter(null)
    private fun initView(){

        viewModel.loadingLiveData.observe(viewLifecycleOwner){
            showLoading()
        }

        var searchedText = ""
        viewModel.errorLiveData.observe(viewLifecycleOwner){
            showError(searchedText, it?:"Error")
        }

        viewModel.searchTextLiveData.observe(viewLifecycleOwner){
            it.let {
                viewModel.getSearchedProduct(it!!)
                searchedText = it
            }
        }

        viewBinding.recyclerSearch.adapter = adapter

        viewModel.productsLiveData.observe(viewLifecycleOwner){
//            view?.hideKeyboard()
            bindProducts(it!!)
        }
    }

    private fun showError(query: String, message: String) {
        viewBinding.loadingView.isVisible = false
        viewBinding.errorView.isVisible = true
        viewBinding.successView.isVisible = false
        viewBinding.errorText.text = message
        viewBinding.btnTryAgain.setOnClickListener {
            viewModel.getSearchedProduct(query)
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

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Remove the observer when the Fragment's view is destroyed
        viewModel.searchTextLiveData.removeObservers(viewLifecycleOwner)
    }
}