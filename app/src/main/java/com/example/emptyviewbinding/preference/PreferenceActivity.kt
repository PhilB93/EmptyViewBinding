package com.example.emptyviewbinding.preference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceFragment
import androidx.appcompat.widget.Toolbar
import com.example.emptyviewbinding.APP_ACTIVITY
import com.example.emptyviewbinding.MainActivity
import com.example.emptyviewbinding.R
import com.example.emptyviewbinding.databinding.ActivityPreferenceBinding

class PreferenceActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar
    private var _binding: ActivityPreferenceBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityPreferenceBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        mToolbar = mBinding.toolbar
        supportFragmentManager.beginTransaction().replace(R.id.fragmentpref, PreferencesFragment()).commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}