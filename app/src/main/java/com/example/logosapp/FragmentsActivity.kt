package com.example.logosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.logosapp.fragments.ChatFragment
import com.example.logosapp.fragments.KabFragment
import com.example.logosapp.fragments.NavigationFragment
import com.example.logosapp.fragments.PersonFragment
import kotlinx.android.synthetic.main.activity_fragmets.*
import android.util.Log
import android.widget.ListView
import android.widget.ProgressBar
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import android.view.ViewGroup.LayoutParams;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context
import android.content.pm.PackageManager
import android.preference.PreferenceManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup.INVISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.ContentView

import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentContainer
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_navigation.*
import org.osmdroid.config.Configuration.*


import java.util.ArrayList

class FragmentsActivity : AppCompatActivity() {
    private val REQUEST_PERMISSIONS_REQUEST_CODE = 1


    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));

        setContentView(R.layout.activity_fragmets)

        val kabFragment = KabFragment()
        val navigationFragment = NavigationFragment()
        val personFragment = PersonFragment()
        val chatFragment = ChatFragment()

        makeCurrentFragment(chatFragment)

        bottom_navigation.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.ic_user_kab -> {
                    makeCurrentFragment(kabFragment)
                }
                R.id.ic_navigation -> {
                    makeCurrentFragment(navigationFragment)
                }
                R.id.ic_people -> {
                    makeCurrentFragment(personFragment)
                }
                R.id.ic_chat -> {
                    makeCurrentFragment(chatFragment)
                }
            }
            true
        }

    }

    override fun onResume() {
        super.onResume();
    }

    override fun onPause() {
        super.onPause();
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val permissionsToRequest = ArrayList<String>();
        var i = 0;
        while (i < grantResults.size) {
            permissionsToRequest.add(permissions[i]);
            i++;
        }
        if (permissionsToRequest.size > 0) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(),
                REQUEST_PERMISSIONS_REQUEST_CODE
            );
        }
    }

}