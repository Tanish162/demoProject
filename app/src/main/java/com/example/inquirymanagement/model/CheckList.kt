package com.example.inquirymanagement.model

data class CheckList(
    val isInfo: Boolean,
    val chkExtra: ChkExtra,
    val chkField: String,
    val isRemark: Boolean,
    val chkFieldId: String,
    val isPlaceholder: Boolean,
    val chkFieldsInfo: String,
    val chkFieldsType: Int,
    val chkFieldsRequired: Boolean,
    val chkFieldsPlaceholder: String,
    val isAttachmentRequired: Boolean,
    val chkAttachmentLimit: Int? = null,
    val chkAttachmentAllowTypes: List<Int>? = null
)
