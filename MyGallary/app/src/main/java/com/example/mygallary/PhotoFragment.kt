package com.example.mygallary

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load

private const val ARG_URI = "uri"

class PhotoFragment : Fragment() {
    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getParcelable<Uri>(ARG_URI)?.let { uri = it }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val descriptor = requireContext().contentResolver.openFileDescriptor(uri, "r")
        descriptor?.use{
            val bitmap = BitmapFactory.decodeFileDescriptor(descriptor.fileDescriptor)
            imageView.load(bitmap)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(uri: String) = PhotoFragment().apply {
                arguments = Bundle().apply { putString(ARG_URI, uri) }
            }
    }
}