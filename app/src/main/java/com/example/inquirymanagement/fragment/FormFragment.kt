package com.example.inquirymanagement.fragment


import android.net.Uri
import android.os.Bundle
import android.provider.DocumentsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inquirymanagement.BaseFragment
import com.example.inquirymanagement.R
import com.example.inquirymanagement.Utils.getEnquiryForm
import com.example.inquirymanagement.adapter.FormAdapter
import com.example.inquirymanagement.databinding.FragmentFormBinding
import com.example.inquirymanagement.interfaces.OnClickListenerInterface

class FormFragment : BaseFragment<FragmentFormBinding>(), OnClickListenerInterface {
    private lateinit var adapter: FormAdapter
    private lateinit var getFile: ActivityResultLauncher<String>
    private val fileAttachments = mutableListOf<Uri>()
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFormBinding {
        return FragmentFormBinding.inflate(inflater, container, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = inflateBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            toolbarTitle(getString(R.string.form))
            toolbarAddClick(false, this@FormFragment, 101, null)
        }
        val formList = getEnquiryForm()
        adapter = FormAdapter(formList, requireContext())
        binding.apply {
            recycleView.layoutManager = LinearLayoutManager(requireContext())
            recycleView.adapter = adapter
            adapter.onClickAdapterListener(this@FormFragment)
        }
        getFile = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val mimeType = requireContext().contentResolver.getType(uri)
                if (mimeType != null) {
                    when {
                        mimeType.startsWith("image/") -> {
//                            val fileName = getFileName(uri)
                            fileAttachments.add(uri)
                        }

                        mimeType == "application/pdf" -> {
//                            val fileName = getFileName(uri)
                            fileAttachments.add(uri)
                        }

                        else -> {
                            Toast.makeText(context, "Invalid file type", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } ?: run {
                Toast.makeText(context, "No file selected", Toast.LENGTH_SHORT).show()
            }
            adapter.updateFileAttachments(fileAttachments)
        }

    }

    override fun onClickBtn() {
        getFile.launch("application/pdf, image/*")
    }

    override fun onClickBtnRemove(uri: Uri) {
        fileAttachments.remove(uri)
        adapter.updateFileAttachments(fileAttachments)
    }

    private fun getFileName(uri: Uri): String {
        val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            it.moveToFirst()
            val nameIndex = it.getColumnIndex(DocumentsContract.Document.COLUMN_DISPLAY_NAME)
            return it.getString(nameIndex)
        }
        return uri.lastPathSegment ?: "Unknown"
    }
}