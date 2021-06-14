package com.example.logosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.logosapp.fragments.ChatFragment
import com.example.logosapp.fragments.KabFragment
import com.example.logosapp.fragments.NavigationFragment
import com.example.logosapp.fragments.PersonFragment
import kotlinx.android.synthetic.main.activity_fragmets.*

class FragmetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragmets)
        val kabFragment = KabFragment()
        val navigationFragment = NavigationFragment()
        val personFragment = PersonFragment()
        val chatFragment = ChatFragment()

        makeCurrentFragment(kabFragment)

        bottom_navigation.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_user_kab->makeCurrentFragment(kabFragment)
                R.id.ic_navigation->makeCurrentFragment(navigationFragment)
                R.id.ic_people->makeCurrentFragment(personFragment)
                R.id.ic_chat->makeCurrentFragment(chatFragment)
            }
            true
        }
    }

    private fun makeCurrentFragment(fragment: Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper,fragment)
            commit()
        }

}