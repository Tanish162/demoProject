package com.example.inquirymanagement.interfaces

import android.net.Uri
import com.google.android.material.chip.ChipGroup

interface OnClickListenerInterface {

    fun onClickBtn()
    fun onClickBtnRemove(text:Uri)
}