// ############################### ViewBinding 이용 ###############################
package com.example.mygallary

import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.example.mygallary.databinding.FragmentPhotoBinding

private const val ARG_URI = "uri"

class PhotoFragment : Fragment() {
    private var _binding : FragmentPhotoBinding? = null
    private val binding get() = _binding!!

    private lateinit var uri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(ARG_URI, Uri::class.java)?.let { uri = it }
        } else{
            arguments?.getParcelable<Uri>(ARG_URI)?.let { uri = it }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPhotoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val descriptor = requireContext().contentResolver.openFileDescriptor(uri, "r")
        descriptor?.use{
            val bitmap = BitmapFactory.decodeFileDescriptor(descriptor.fileDescriptor)
            binding.imageView.load(bitmap)
        }
    }

    // 메모리 누수 방지
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(uri: Uri) = PhotoFragment().apply {
            arguments = Bundle().apply { putParcelable(ARG_URI, uri) }
        }
    }
}

// ############################### findViewById 이용 ###############################
//package com.example.mygallary
//
//import android.graphics.BitmapFactory
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import coil.load
//
//private const val ARG_URI = "uri"
//
//class PhotoFragment : Fragment() {
//    private lateinit var uri: Uri
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
//            arguments?.getParcelable(ARG_URI, Uri::class.java)?.let { uri = it }
//        } else{
//            arguments?.getParcelable<Uri>(ARG_URI)?.let { uri = it }
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_photo, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        val imageView = view.findViewById<ImageView>(R.id.imageView)
//        val descriptor = requireContext().contentResolver.openFileDescriptor(uri, "r")
//        descriptor?.use{
//            val bitmap = BitmapFactory.decodeFileDescriptor(descriptor.fileDescriptor)
//            imageView.load(bitmap)
//        }
//    }
//
//    companion object {
//        @JvmStatic
//        fun newInstance(uri: Uri) = PhotoFragment().apply {
//                arguments = Bundle().apply { putParcelable(ARG_URI, uri) }
//            }
//    }
//}