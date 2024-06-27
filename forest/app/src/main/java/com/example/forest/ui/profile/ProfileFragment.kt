package com.example.forest.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.forest.R
import com.example.forest.databinding.FragmentProfileBinding
import com.example.forest.ui.profile.ProfileViewModel
import com.google.android.material.bottomsheet.BottomSheetDialog


class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    private val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()) {
        val galleryUri = it
        try{
            binding.ivProfile.setImageURI(galleryUri)
        }catch(e:Exception){
            e.printStackTrace()
        }
    }

    private val cameraLauncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) { bitmap ->
        bitmap.let { binding.ivProfile.setImageBitmap(bitmap) }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(R.layout.profile_bottom_sheet_dialog, null)
        bottomSheetDialog.setContentView(view)

        val cameraLayout: LinearLayout = view.findViewById(R.id.layout_camera)
        val galleryLayout: LinearLayout = view.findViewById(R.id.layout_gallery)

        cameraLayout.setOnClickListener {
            cameraLauncher.launch(null)
            bottomSheetDialog.dismiss()
        }

        galleryLayout.setOnClickListener {
            galleryLauncher.launch("image/*")
            bottomSheetDialog.dismiss()
        }

        bottomSheetDialog.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel = ViewModelProvider(requireActivity())[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root


        profileViewModel.user.observe(viewLifecycleOwner) {
            binding.tvNameText.text = it.name
            binding.tvDeptPositionText.text = "${it.department}/${it.position}"
            binding.tvPhoneText.text = it.phoneNumber
            binding.tvEmailText.text = it.email
            Glide.with(this).load(it.imageUrl).into(binding.ivProfile)
        }


        binding.ibEditProfile.setOnClickListener {
            showBottomSheetDialog()
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}