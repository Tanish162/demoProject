package com.example.inquirymanagement.model

data class ChkExtra(
    val max: String? = null,
    val min: String? = null,
    val maxDec: String? = null,
    val minDec: String? = null,
    val selectionType: String? = null,
    val selectionView: String? = null,
    val fieldSelection: List<String>? = null
)
