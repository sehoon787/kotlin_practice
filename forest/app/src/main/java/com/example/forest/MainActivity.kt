package com.example.forest

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.forest.databinding.ActivityMainBinding
import com.example.forest.ui.login.LoginViewModel
import com.example.forest.data.models.User
import com.example.forest.ui.profile.ProfileViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var profileViewModel: ProfileViewModel

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                val permissionName = it.key
                val isGranted = it.value
                if(!isGranted) {
                    Toast.makeText(this, "$permissionName 권한 거부 됨", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun allPermissionsGranted(permissions: Array<String>) = permissions.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions(permissions: Array<String>, title: String, message: String) {
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("권한 요청"){ _, _ ->
                requestPermissionLauncher.launch(permissions)
            }
            setNegativeButton("거부", null)
        }.show()
    }

    companion object {
        private val CAMERA_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        private var GALLERY_PERMISSIONS = arrayOf(
            android.Manifest.permission.READ_MEDIA_IMAGES,
        )
    }

    private fun setToken(){
        val token = intent.getStringExtra("token")
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        loginViewModel.updateToken(token!!)
    }

    private fun setProfile(){
        val user = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getSerializableExtra("user", User::class.java)
        } else {
            intent.getSerializableExtra("user") as User
        }

        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        profileViewModel.updateUser(user!!)

    }

    private fun addSurvey(navController: NavController){
        binding.fab.setOnClickListener {
            navController.navigate(R.id.navigation_register)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // hide appbar
        supportActionBar?.hide()

        if(!allPermissionsGranted(CAMERA_PERMISSIONS)) {
            requestPermissions(CAMERA_PERMISSIONS, title="카메라 권한 요청", message = "카메라 권한 필요")
        }

        if(32 < Build.VERSION.SDK_INT){
            GALLERY_PERMISSIONS = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE,)
            if(!allPermissionsGranted(GALLERY_PERMISSIONS)) {
                requestPermissions(GALLERY_PERMISSIONS, title="갤러리 권한 요청", message = "갤러리 권한 필요")
            }
        }

        setToken()
        setProfile()

        val navView: BottomNavigationView = binding.bottomNavigationView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_last_schedule, R.id.navigation_register,
                R.id.navigation_analysis, R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        addSurvey(navController)

        navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.navigate(R.id.navigation_home)
                    true
                }
                R.id.navigation_last_schedule -> {
                    navController.navigate(R.id.navigation_last_schedule)
                    true
                }
                R.id.navigation_analysis -> {
                    navController.navigate(R.id.navigation_analysis)
                    true
                }
                R.id.navigation_profile -> {
                    navController.navigate(R.id.navigation_profile)
                    true
                }
                else -> false
            }
        }
    }
}

class ForestBottomNavigationView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr) {

    init {
        val menuView = getChildAt(0) as ViewGroup
        menuView.getChildAt(2).isClickable = false
    }
}