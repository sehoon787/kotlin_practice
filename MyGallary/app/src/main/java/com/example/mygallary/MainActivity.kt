package com.example.mygallary

import android.content.ContentUris
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.example.mygallary.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private val requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted ->
        if(isGranted){ getAllPhotos() }
        else{ Toast.makeText(this, "권한 거부 됨", Toast.LENGTH_SHORT).show() }
    }

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private fun getAllPhotos(){
        val uris = mutableListOf<Uri>()

        contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            null,
            null,
            null,
            "${MediaStore.Images.ImageColumns.DATE_TAKEN} DESC",
        )?.use { cursor ->
            while (cursor.moveToNext()){
                val id = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
                val contentUri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)
                uris.add(contentUri)
            }
        }
        Log.d("MainActivity", "getAllPhotos: $uris")

        val adapter = MyPageAdapter(supportFragmentManager, lifecycle)
        adapter.uris = uris
        binding.viewPager.adapter = adapter

        timer(period = 1000){
            runOnUiThread {
                if(binding.viewPager.currentItem < adapter.itemCount - 1){
                    binding.viewPager.currentItem = binding.viewPager.currentItem + 1
                } else{
                    binding.viewPager.currentItem = 0
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(32 < Build.VERSION.SDK_INT){
            if(ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_MEDIA_IMAGES)){
                    AlertDialog.Builder(this).apply {
                        setTitle("권한이 필요한 이유")
                        setMessage("사진 정보를 얻으려면 이미지 접근 권한이 필요합니다.")
                        setPositiveButton("권한 요청"){ _, _ ->
                            requestPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                        }
                        setNegativeButton("거부", null)
                    }.show()
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_MEDIA_IMAGES)
                }
                return
            }
        }
        else{
            if(ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED){
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)){
                    AlertDialog.Builder(this).apply {
                        setTitle("권한이 필요한 이유")
                        setMessage("사진 정보를 얻으려면 외부 저장소 권한이 필요합니다.")
                        setPositiveButton("권한 요청"){ _, _ ->
                            requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                        }
                        setNegativeButton("거부", null)
                    }.show()
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                }
                return
            }
        }
        getAllPhotos()
    }
}