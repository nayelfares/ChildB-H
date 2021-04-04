package com.medical.childbh.doctor

import android.app.FragmentManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.medical.childbh.R
import com.medical.childbh.doctor.ui.ActiveQuestons
import com.medical.childbh.doctor.ui.ParentsFragment
import com.medical.childbh.doctor.ui.ProfileFragment
import kotlinx.android.synthetic.main.activity_doctor.*

class DoctorActivity : AppCompatActivity() {
    private var toggle: ActionBarDrawerToggle? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_gradient))
        window.statusBarColor = ContextCompat.getColor(this,R.color.purple_500)
        setContentView(R.layout.activity_doctor)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle!!)
        toggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        doctorNavView.setNavigationItemSelectedListener{ item ->
            val menu = doctorNavView.menu
            for (i in 0 until menu.size()) {
                menu.getItem(i).isChecked = false
            }
            when (item.itemId) {
                R.id.profile -> {
                    item.isChecked = true
                    drawerLayout.closeDrawers()
                    replaceFragmentAndClear(ProfileFragment())
                }
                R.id.parents -> {
                    item.isChecked = true
                    drawerLayout.closeDrawers()
                    replaceFragmentAndClear(ParentsFragment())
                }
                R.id.questions -> {
                    item.isChecked = true
                    drawerLayout.closeDrawers()
                    replaceFragmentAndClear(ActiveQuestons())
                }
                else -> {
                    Toast.makeText(applicationContext, "nothing selected", Toast.LENGTH_LONG).show()
                }
            }
            false
        }
        replaceFragmentAndClear(ParentsFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.doctor_container, fragment)
        transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    fun replaceFragmentAndClear(fragment: Fragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.doctor_container, fragment)
        transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentById(R.id.doctor_container)
        fragment!!.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager.fragments
        if (fragments.size <= 2) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    companion object {
        var token = ""
        var id = 0
        var type = "parent"
    }
}