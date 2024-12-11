package com.example.inquirymanagement.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.DocumentsContract
import android.text.InputType
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.inquirymanagement.R
import com.example.inquirymanagement.Utils.INQUIRY_ATTACHMENT_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_BOOLEAN_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_DATE_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_DECIMAL_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_EMAIL_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_LOCATION_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_NUMBER_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_PHONE_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_SELECTION_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_TEXT_AREA_INPUT
import com.example.inquirymanagement.Utils.INQUIRY_TEXT_INPUT
import com.example.inquirymanagement.Utils.checkBoxFun
import com.example.inquirymanagement.Utils.chipGroupPadding
import com.example.inquirymanagement.Utils.editTextLine
import com.example.inquirymanagement.Utils.editTextVisibility
import com.example.inquirymanagement.Utils.spinnerPadding
import com.example.inquirymanagement.Utils.spinnerVal
import com.example.inquirymanagement.Utils.textBorder
import com.example.inquirymanagement.Utils.textDrawable
import com.example.inquirymanagement.Utils.textFont
import com.example.inquirymanagement.Utils.textViewBtnClick
import com.example.inquirymanagement.Utils.textViewPadding
import com.example.inquirymanagement.Utils.textVisibility
import com.example.inquirymanagement.Utils.validationEditText
import com.example.inquirymanagement.databinding.ItemFormBinding
import com.example.inquirymanagement.interfaces.OnClickListenerInterface
import com.example.inquirymanagement.model.CheckList
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

class FormAdapter(private val list: List<CheckList>, private val context: Context) :
    RecyclerView.Adapter<FormAdapter.ViewHolder>() {

    private var onClick: OnClickListenerInterface? = null
    private var fileAttachments = mutableListOf<Uri>()

    class ViewHolder(val binding: ItemFormBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            (ItemFormBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        )
    }

    fun onClickAdapterListener(new: OnClickListenerInterface) {
        onClick = new
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.apply {
            binding.apply {
                if (currentItem.isInfo) {
                    form.visibility = View.VISIBLE
                    when (currentItem.chkFieldsType) {
                        INQUIRY_BOOLEAN_INPUT -> {
                            textFont(textView, 15f)
                            textVisibility(textView, currentItem.chkField)
                            textViewPadding(textView, 12)
                            checkBoxFun(true, checkBox)
                            checkBox.isChecked
                        }

                        INQUIRY_TEXT_INPUT -> {
                            editTextVisibility(
                                editText,
                                currentItem.chkField,
                                InputType.TYPE_CLASS_TEXT,
                                editTextIL
                            )
                            if (currentItem.chkFieldsRequired) validationEditText(
                                editText,
                                editTextIL
                            )
                            btnAttachPick.visibility = View.GONE
                            chipGroup.visibility = View.GONE
                            textView.visibility = View.GONE
                            btnAttachPick.visibility = View.GONE
                        }

                        INQUIRY_NUMBER_INPUT -> {
                            editTextVisibility(
                                editText,
                                currentItem.chkField,
                                InputType.TYPE_CLASS_NUMBER,
                                editTextIL
                            )
                            if (currentItem.chkFieldsRequired) validationEditText(
                                editText,
                                editTextIL
                            )
                        }

                        INQUIRY_DECIMAL_INPUT -> {
                            editTextVisibility(
                                editText,
                                currentItem.chkField,
                                InputType.TYPE_NUMBER_FLAG_DECIMAL,
                                editTextIL
                            )
                            if (currentItem.chkFieldsRequired) validationEditText(
                                editText,
                                editTextIL
                            )
                        }

                        INQUIRY_DATE_INPUT -> {
                            textVisibility(textView, context.getString(R.string.pick_date))
                            textViewBtnClick(
                                textView
                            )
                            checkBox.visibility = View.GONE
                            textViewPadding(textView, 46)
                            textFont(textView, 17f)
                            textBorder(textView, context, true)
                            textDrawable(textView, context)
                        }

                        INQUIRY_SELECTION_INPUT -> {
                            spinnerPadding(spinner, top = 46, left = 20, right = 0, bottom = 46)
                            spinnerVal(
                                spinner,
                                backgroundBol = true,
                                currentItem.chkExtra.fieldSelection,
                                context
                            )
                            checkBox.visibility = View.GONE
                        }

                        INQUIRY_TEXT_AREA_INPUT -> {
                            editTextVisibility(
                                editText,
                                currentItem.chkField,
                                InputType.TYPE_NUMBER_FLAG_DECIMAL,
                                editTextIL
                            )
                            editTextLine(editText, 2, 3)
                            if (currentItem.chkFieldsRequired) validationEditText(
                                editText,
                                editTextIL
                            )
                        }

                        INQUIRY_ATTACHMENT_INPUT -> {
                            textFont(textView, 15f)
                            textVisibility(textView, currentItem.chkField)
                            textViewPadding(textView, 12)
                            //Attach
                            btnAttachPick.visibility = View.VISIBLE
                            btnAttachPick.setOnClickListener {
//                                chipGroupPadding(chipGroup, 15, 15, 15, 15)
                                onClick!!.onClickBtn()
                                btnAttachPick.visibility = View.GONE
                                textView.visibility = View.GONE
                                chipGroup.visibility = View.GONE
                            }
                            chipGroupPadding(chipGroup, 48, 15, 15, 48)
//                            chipGroup.removeAllViews() // Clear existing chips
                            Log.d("TAG", "$fileAttachments")
                            for (file in fileAttachments) {
                                addChipToChipGroup(chipGroup, file)

                                btnAttachPick.visibility = View.VISIBLE
                                textView.visibility = View.VISIBLE
                                chipGroup.visibility = View.VISIBLE
                            }
                        }

                        INQUIRY_LOCATION_INPUT -> {

                        }

                        INQUIRY_PHONE_INPUT -> {
                            editTextVisibility(
                                editText,
                                currentItem.chkField,
                                InputType.TYPE_CLASS_NUMBER,
                                editTextIL
                            )
                            if (currentItem.chkFieldsRequired) validationEditText(
                                editText,
                                editTextIL
                            )
                        }

                        INQUIRY_EMAIL_INPUT -> {
                            editTextVisibility(
                                editText,
                                currentItem.chkField,
                                InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS,
                                editTextIL
                            )
                            if (currentItem.chkFieldsRequired) validationEditText(
                                editText,
                                editTextIL
                            )
                        }

                        else -> {

                        }
                    }
                } else {
                    form.visibility = View.GONE
                }
            }
        }
    }


    private fun addChipToChipGroup(chipGroup: ChipGroup, uri: Uri) {
        val chiptxt = getFileName(uri)
        val chip = Chip(context).apply {
            text = chiptxt
            isCloseIconVisible = true
            setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, uri)
                context.startActivity(intent)
            }
            setOnCloseIconClickListener {
                chipGroup.removeView(this)
                fileAttachments.remove(uri)
                onClick?.onClickBtnRemove(uri)
                notifyDataSetChanged()
            }
        }
        chipGroup.addView(chip)
    }

    private fun getFileName(uri: Uri): String {
        val cursor = context.contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val nameIndex = it.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME)
            return it.getString(nameIndex)
        }
        return uri.lastPathSegment ?: "Unknown"
    }

    fun updateFileAttachments(newAttachments: List<Uri>) {
        fileAttachments = newAttachments.toMutableList()
        notifyDataSetChanged()  // Refresh the adapter
    }
}
