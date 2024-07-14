package com.example.routeandroidtask.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.routeandroidtask.R
import com.example.routeandroidtask.databinding.ActivityMainBinding
import com.example.routeandroidtask.ui.productList.ProductListFragment
import com.example.routeandroidtask.ui.productList.ProductListViewModel
import com.example.routeandroidtask.ui.search.SearchFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _viewBinding: ActivityMainBinding? = null
    private val viewBinding get() = _viewBinding!!

    private lateinit var viewModel: ProductListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ProductListViewModel::class.java]
        _viewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(viewBinding.root)

        if (savedInstanceState == null)
            pushFragment(ProductListFragment())

        initViews()
    }

    private fun initViews() {

        viewBinding.edtSearch.onFocusChangeListener = View.OnFocusChangeListener { _, isFocus ->
            if (isFocus) {
                pushFragment(SearchFragment())
            }else{
                pushFragment(ProductListFragment())
            }
        }
        viewBinding.edtSearch.addTextChangedListener(object: TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                pushFragment(SearchFragment())
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.setSearchText(p0.toString())
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        // hide keyboard
        viewBinding.edtSearch.setOnKeyListener { view, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_BACK) {
                viewBinding.edtSearch.clearFocus()
                view.hideKeyboard()
            }
            true
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    override fun onDestroy() {
        super.onDestroy()
//        _viewBinding = null
    }
}