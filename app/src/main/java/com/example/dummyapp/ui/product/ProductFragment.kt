package com.example.dummyapp.ui.product

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dummyapp.databinding.ProductFragmentBinding
import com.example.dummyapp.domain.model.product.Product
import com.example.dummyapp.ui.product.adapter.ProductAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductFragment: Fragment() {
    private var categoryAdapter: ArrayAdapter<String>? = null
    private var _binding: ProductFragmentBinding? = null
    private val binding get() = _binding!!

    private val productViewModel: ProductViewModel by viewModels()
    private var productAdapter: ProductAdapter? = null
    private var categories : List<String>? = null
    private var listProduct = arrayListOf<Product>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ProductFragmentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productViewModel.getProduct()
        categories = productViewModel.getCategories()
        unitUI()

    }


    private fun unitUI() {
        categoryAdapter = context?.let {
            ArrayAdapter<String>(it, R.layout.simple_spinner_dropdown_item)
        }
        categories?.let { categoryAdapter?.addAll(it) }
        binding.spinnerFilter.adapter = categoryAdapter
        initUIState()
        initListener()
    }

    private fun initListener() {
        binding.spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val listFiltered = listProduct.filter { it.category.toString() == categories?.get(position).toString() }
                if (categories?.get(position) == "all"){
                    productAdapter?.updateList(listProduct)
                }else{
                    productAdapter?.updateList(listFiltered)
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}


        }

        binding.floatingButton.setOnClickListener {
            val product = Product(102, "Teclado Gaming", "Increible teclado", 15, 5.0, 5.0, 10, "", "smartphone","",
                arrayListOf()
            )
            productViewModel.addProduct(product)
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                productViewModel.state.collect{
                    when(it){
                        is ProductState.Error -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        is ProductState.Loading -> binding.progressBarProduct.isVisible = true
                        is ProductState.Success -> {
                            successState(it.product.products)
                            listProduct = it.product.products
                        }
                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                productViewModel.stateAddProduct.collect {
                    when (it) {
                        is AddProductState.Error -> Toast.makeText(
                            context,
                            "Error",
                            Toast.LENGTH_SHORT
                        ).show()

                        is AddProductState.Loading -> binding.progressBarProduct.isVisible = true
                        is AddProductState.Success -> Toast.makeText(
                            context,
                            "Producto creado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    private fun successState(result: ArrayList<Product>) {
        refreshList(result)
        binding.progressBarProduct.isVisible = false
    }

    private fun refreshList(result: ArrayList<Product>) {
        productAdapter = ProductAdapter(result)
        productAdapter?.updateList(result)
        binding.rvProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter

        }
    }
}