package com.example.data.dataSource.products

import com.example.data.dataSourceContract.ProductsDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun provideProductsDataSource(
        productsDataSourceImpl: ProductsDataSourceImpl
    ): ProductsDataSource
}