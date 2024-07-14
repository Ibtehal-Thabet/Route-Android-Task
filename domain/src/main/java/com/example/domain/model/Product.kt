package com.example.domain.model

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import kotlinx.parcelize.RawValue

@Parcelize
data class Product(
	val images: List<String?>? = null,
	val thumbnail: String? = null,
	val minimumOrderQuantity: Int? = null,
	val rating: @RawValue Any? = null,
	val returnPolicy: String? = null,
	val description: String? = null,
	val weight: Int? = null,
	val warrantyInformation: String? = null,
	val title: String? = null,
	val tags: List<String?>? = null,
	val discountPercentage: @RawValue Any? = null,
	val reviews: List<ReviewsItem?>? = null,
	val price: @RawValue Any? = null,
	val meta: Meta? = null,
	val shippingInformation: String? = null,
	val id: Int? = null,
	val availabilityStatus: String? = null,
	val category: String? = null,
	val stock: Int? = null,
	val sku: String? = null,
	val dimensions: Dimensions? = null,
	val brand: String? = null
) : Parcelable

@Parcelize
data class ReviewsItem(
	val date: String? = null,
	val reviewerName: String? = null,
	val reviewerEmail: String? = null,
	val rating: Int? = null,
	val comment: String? = null
) : Parcelable

@Parcelize
data class Dimensions(
	val depth: @RawValue Any? = null,
	val width: @RawValue Any? = null,
	val height: @RawValue Any? = null
) : Parcelable

@Parcelize
data class Meta(
	val createdAt: String? = null,
	val qrCode: String? = null,
	val barcode: String? = null,
	val updatedAt: String? = null
) : Parcelable
