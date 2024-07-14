package com.example.data.model.product

import com.google.gson.annotations.SerializedName

data class Dimensions(

	@field:SerializedName("depth")
	val depth: Any? = null,

	@field:SerializedName("width")
	val width: Any? = null,

	@field:SerializedName("height")
	val height: Any? = null
)