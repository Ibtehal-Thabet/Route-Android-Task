package com.example.data.repository.products

import com.example.domain.repositories.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class di {

    @Binds
    abstract fun provideProductRepository(
        productsRepositoryImpl: ProductsRepositoryImpl
    ): ProductsRepository
}