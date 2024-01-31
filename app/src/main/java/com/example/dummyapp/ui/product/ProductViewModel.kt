package com.example.dummyapp.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dummyapp.domain.model.product.Product
import com.example.dummyapp.domain.model.product.Result
import com.example.dummyapp.domain.usecase.GetCategoryUseCase
import com.example.dummyapp.domain.usecase.GetProductUseCase
import com.example.dummyapp.domain.usecase.SetProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val getProductUseCase: GetProductUseCase, private val getCategoryUseCase: GetCategoryUseCase, private val setProductUseCase: SetProductUseCase): ViewModel(){

    private var _state = MutableStateFlow<ProductState>(ProductState.Loading)
    val state: StateFlow<ProductState> = _state

    private var _stateAddProduct = MutableStateFlow<AddProductState>(AddProductState.Loading)
    val stateAddProduct: StateFlow<AddProductState> = _stateAddProduct

    private var _product: MutableStateFlow<Result>? = null
    val product: StateFlow<Result>? = _product

    private var _categories: MutableStateFlow<List<String>>? = null
    val categories: StateFlow<List<String>>? = _categories

    private var _newProduct: MutableStateFlow<Product>? = null
    val newProductproduct: StateFlow<Product>? = _newProduct

    fun getProduct(){
        viewModelScope.launch {
            _state.value = ProductState.Loading
            val result = withContext(Dispatchers.IO) { getProductUseCase()}
            if (result != null) {
                _product?.value = result
                _state.value = ProductState.Success(result)
            }
        }
    }

    fun getCategories(): List<String> {
        val result : List<String> = listOf(
            "all",
            "smartphones",
            "laptops",
            "fragrances",
            "skincare",
            "groceries",
            "home-decoration",
            "furniture",
            "tops",
            "womens-dresses",
            "womens-shoes",
            "mens-shirts",
            "mens-shoes",
            "mens-watches",
            "womens-watches",
            "womens-bags",
            "womens-jewellery",
            "sunglasses",
            "automotive",
            "motorcycle",
            "lighting")
        return result
    }

    fun addProduct(product: Product){
        viewModelScope.launch {
            _stateAddProduct.value = AddProductState.Loading
            val result = withContext(Dispatchers.IO) { setProductUseCase(product)}
            if (result != null) {
                _newProduct?.value = result
                _stateAddProduct.value = AddProductState.Success(result)
            }
        }
    }

}