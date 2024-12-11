package com.example.inquirymanagement

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import com.example.inquirymanagement.model.CheckList
import com.example.inquirymanagement.model.ChkExtra
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Utils {
    const val INQUIRY_BOOLEAN_INPUT = 1
    const val INQUIRY_TEXT_INPUT = 2
    const val INQUIRY_NUMBER_INPUT = 3
    const val INQUIRY_DECIMAL_INPUT = 4
    const val INQUIRY_DATE_INPUT = 5
    const val INQUIRY_SELECTION_INPUT = 6
    const val INQUIRY_TEXT_AREA_INPUT = 7
    const val INQUIRY_ATTACHMENT_INPUT = 8
    const val INQUIRY_LOCATION_INPUT = 9
    const val INQUIRY_PHONE_INPUT = 10
    const val INQUIRY_EMAIL_INPUT = 11
    const val INQUIRY_OUTLET_INPUT = 12
    const val INQUIRY_PRICE_INPUT = 13
    const val WORKPERMIT_STAFF_INPUT = 14
    const val WORKPERMIT_DATE_TIME_INPUT = 16
    const val WORKPERMIT_TIME_INPUT = 17

    private const val jsonParse = "[" +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"First name\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"f9742a01c1df1f0c\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Enter first name\"," +
            "\"chk_fields_type\": 2," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Enter first name\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Last name\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"0aad77890957acf3\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Enter last name\"," +
            "\"chk_fields_type\": 2," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Enter last name\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {" +
            "\"max\": \"200\"," +
            "\"min\": \"100\"" +
            "}," +
            "\"chk_field\": \"Number (1,2,3...) Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"d3d9e8989016e9df\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"info for Number (1,2,3...) Value\"," +
            "\"chk_fields_type\": 3," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Number (1,2,3...) Value\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {" +
            "\"max_dec\": \"200\"," +
            "\"min_dec\": \"101\"" +
            "}," +
            "\"chk_field\": \"Decimal (4.52) Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"389dc349c29c6fc7\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Decimal (4.52) Value\"," +
            "\"chk_fields_type\": 4," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Decimal (4.52) Value\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Select Date\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"67d2323f0a323b03\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Date Value\"," +
            "\"chk_fields_type\": 5," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Date Value\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {" +
            "\"selection_type\": \"1\"," +
            "\"selection_view\": \"2\"," +
            "\"field_selection\": [" +
            "\"Option 01\"," +
            "\"Option 02\"" +
            "]" +
            "}," +
            "\"chk_field\": \"Selection Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"b5999c8529d99b03\"," +
            "\"is_placeholder\": false," +
            "\"chk_fields_info\": \"\"," +
            "\"chk_fields_type\": 6," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Textarea Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"2ea0fde8b6ccdf28\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Textarea Value\"," +
            "\"chk_fields_type\": 7," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Textarea Value\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Attachments Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"732cf61f4bcea27e\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Attachments Value\"," +
            "\"chk_fields_type\": 8," +
            "\"chk_fields_required\": true," +
            "\"chk_attachment_limit\": 5," +
            "\"chk_fields_placeholder\": \"Placeholder for Attachments Value\"," +
            "\"is_attachment_required\": false," +
            "\"chk_attachment_allow_types\": [" +
            "1," +
            "2," +
            "3," +
            "4," +
            "5," +
            "6," +
            "7," +
            "8" +
            "]" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Latitude & Longitude Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"5faecc6da4880f68\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Latitude & Longitude Value\"," +
            "\"chk_fields_type\": 9," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Latitude & Longitude Value\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Enter Phone Number\"," +
            "" +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"86b62399359a72ee\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Phone Number Value\"," +
            "\"chk_fields_type\": 10," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Phone Number Value\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"Email Value\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"1d6ff43055ab2696\"," +
            "\"is_placeholder\": true," +
            "\"chk_fields_info\": \"Info for Email Value\"," +
            "\"chk_fields_type\": 11," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"Placeholder for Email\"," +
            "\"is_attachment_required\": false" +
            "}," +
            "{" +
            "\"is_info\": true," +
            "\"chk_extra\": {}," +
            "\"chk_field\": \"I agree to the terms and conditions as set out by the user agreement.\"," +
            "\"is_remark\": false," +
            "\"chk_field_id\": \"5638ba63e8dc27c3\"," +
            "\"is_placeholder\": false," +
            "\"chk_fields_info\": \"\"," +
            "\"chk_fields_type\": 1," +
            "\"chk_fields_required\": true," +
            "\"chk_fields_placeholder\": \"\"," +
            "\"is_attachment_required\": false" +
            "}" +
            "]"

    fun getEnquiryForm(): MutableList<CheckList> {
        Log.d("DATALIST", jsonParse)
        val enquiryFormItems = mutableListOf<CheckList>()
        val jsonArray = JSONArray(jsonParse)
        for (i in 0 until jsonArray.length()) {
            val loopObject = jsonArray.optJSONObject(i) ?: JSONObject()
            val isInfo = loopObject.optBoolean("is_info")
            Log.d("CheckField", "isInfo: $isInfo")

            val chkExtra = loopObject.optJSONObject("chk_extra")?.let { jsonObject ->
                ChkExtra(
                    max = jsonObject.optString("max"),
                    min = jsonObject.optString("min"),
                    maxDec = jsonObject.optString("max_dec"),
                    minDec = jsonObject.optString("min_dec"),
                    selectionType = jsonObject.optString("selection_type"),
                    selectionView = jsonObject.optString("selection_view"),
                    fieldSelection = jsonObject.optJSONArray("field_selection")?.let { jsonArray ->
                        (0 until jsonArray.length()).map { jsonArray.optString(it) }
                    }
                )
            } ?: ChkExtra()

            val chkField = loopObject.optString("chk_field")
            Log.d("CheckField", "chkField: $chkField")

            val isRemark = loopObject.optBoolean("is_remark")
            Log.d("CheckField", "isRemark: $isRemark")

            val chkFieldId = loopObject.optString("chk_field_id")
            Log.d("CheckField", "chkFieldId: $chkFieldId")

            val isPlaceholder = loopObject.optBoolean("is_placeholder")
            Log.d("CheckField", "isPlaceholder: $isPlaceholder")

            val chkFieldsInfo = loopObject.optString("chk_fields_info")
            Log.d("CheckField", "chkFieldsInfo: $chkFieldsInfo")

            val chkFieldsType = loopObject.optInt("chk_fields_type")
            Log.d("CheckField", "chkFieldsType: $chkFieldsType")

            val chkFieldsRequired = loopObject.optBoolean("chk_fields_required")
            Log.d("CheckField", "chkFieldsRequired: $chkFieldsRequired")

            val chkFieldsPlaceholder = loopObject.optString("chk_fields_placeholder")
            Log.d("CheckField", "chkFieldsPlaceholder: $chkFieldsPlaceholder")

            val isAttachmentRequired = loopObject.optBoolean("is_attachment_required")
            Log.d("CheckField", "isAttachmentRequired: $isAttachmentRequired")

            val chkAttachmentLimit = loopObject.optInt("chk_attachment_limit", -1)
            Log.d("CheckField", "chkAttachmentLimit: $chkAttachmentLimit")

            val chkAttachmentAllowTypes =
                loopObject.optJSONArray("chk_attachment_allow_types")?.let { jsonArray2 ->
                    (0 until jsonArray2.length()).map { jsonArray2.optInt(it) }
                } ?: emptyList()
            Log.d("CheckField", "chkAttachmentAllowTypes: $chkAttachmentAllowTypes")
            val list = CheckList(
                isInfo, chkExtra,
                chkField,
                isRemark,
                chkFieldId,
                isPlaceholder,
                chkFieldsInfo,
                chkFieldsType,
                chkFieldsRequired,
                chkFieldsPlaceholder,
                isAttachmentRequired,
                chkAttachmentLimit,
                chkAttachmentAllowTypes
            )
            enquiryFormItems.add(list)
        }
        return enquiryFormItems
    }

    fun editTextVisibility(
        editText: TextInputEditText,
        hintOBJ: CharSequence?,
        typeClassText: Int,
        editTextIL: TextInputLayout,
    ) {
        editText.apply {
            hint = hintOBJ
            inputType = typeClassText
        }
        editTextIL.apply {
            visibility = View.VISIBLE
            hint = hintOBJ
        }
    }

    fun validationEditText(editText: TextInputEditText, editTextIL: TextInputLayout) {
        editText.onFocusChangeListener =
            View.OnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) { // When the EditText loses focus
                    val text = editText.text.toString()
                    if (text.isEmpty()) editTextIL.error =
                        "This field is required*"
                    else editTextIL.error = null

                }
            }
    }

    fun editTextLine(editText: TextInputEditText, min: Int, max: Int) {
        editText.apply {
            minLines = min
            maxLines = max
        }
    }

    fun textFont(textView: TextView, fl: Float) {
        textView.textSize = fl
    }

    fun textDrawable(textView: TextView, context: Context) {
        textView.apply {
            setCompoundDrawablesRelativeWithIntrinsicBounds(
                null, // start
                null, // top
                context.getDrawable(R.drawable.baseline_perm_contact_calendar_24),
                null  // bottom
            )
        }
    }

    fun textBorder(textView: TextView, context: Context, backgroundBol: Boolean) {
        textView.apply {
            if (backgroundBol) {
                background = context.getDrawable(R.drawable.spinner_outline_background)
            } else {
                Log.d("textStyle()", "No-BackGround")
            }
        }
    }

    fun textVisibility(textView: TextView, title: String) {
        textView.visibility = View.VISIBLE
        textView.text = title
        textView.hint = title
    }

    fun textViewPadding(textView: TextView, fl: Int) {
        textView.setPadding(fl, fl, fl, fl)
    }

    fun chipGroupPadding(chipGroup: ChipGroup, top: Int, left: Int, right: Int, bottom: Int) {
        chipGroup.setPadding(left, top, right, bottom)
    }

    fun checkBoxFun(b: Boolean, checkBox: CheckBox) {
        checkBox.visibility = View.VISIBLE
    }


    fun spinnerPadding(spinner: Spinner, top: Int, left: Int, right: Int, bottom: Int) {
        spinner.setPadding(left, top, right, bottom)
    }

    fun spinnerVal(
        spinner: Spinner,
        backgroundBol: Boolean,
        fieldSelection: List<String>?,
        context: Context
    ) {
        spinner.visibility = View.VISIBLE
        val listWithSelect = mutableListOf("Select")
        listWithSelect.addAll(fieldSelection ?: emptyList())

        val adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            listWithSelect
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.setSelection(0) // Select "Select" by default

        if (backgroundBol) {
            spinner.background = context.getDrawable(R.drawable.spinner_outline_background)
        } else {
            Log.d("spinnerVal()", "No Background")
        }
    }

    fun textViewBtnClick(
        textView: TextView,
    ) {
        textView.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val datePickerDialog = DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        // Handle date selection
                        val selectedDate = Calendar.getInstance().apply {
                            set(year, month, dayOfMonth)
                        }
                        val formattedDate = SimpleDateFormat(
                            "dd/MM/yyyy",
                            Locale.getDefault()
                        ).format(selectedDate.time)
                        textView.text = formattedDate
                    },
                    year,
                    month,
                    day
                )
                datePickerDialog.show()
            }
        }
    }
}