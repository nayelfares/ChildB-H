package com.medical.childbh.parent

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
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.medical.childbh.R
import com.medical.childbh.parent.ui.ArticalsFragment
import com.medical.childbh.parent.ui.ChildrenFragment
import com.medical.childbh.parent.ui.DoctorsFragment
import kotlinx.android.synthetic.main.activity_parent.*

class ParentActivity : AppCompatActivity() {
    private var toggle: ActionBarDrawerToggle? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setBackgroundDrawable(ContextCompat.getDrawable(this, R.drawable.button_gradient))
        window.statusBarColor = ContextCompat.getColor(this,R.color.purple_500)
        setContentView(R.layout.activity_parent)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle!!)
        toggle!!.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        parentNavView.setNavigationItemSelectedListener{ item ->
            val menu = parentNavView.menu
            for (i in 0 until menu.size()) {
                menu.getItem(i).isChecked = false
            }
            when (item.itemId) {
                R.id.children -> {
                    item.isChecked = true
                    drawerLayout.closeDrawers()
                    replaceFragmentAndClear(ChildrenFragment())
                }
                R.id.doctors -> {
                    item.isChecked = true
                    drawerLayout.closeDrawers()
                    replaceFragmentAndClear(DoctorsFragment())
                }
                R.id.articals -> {
                    item.isChecked = true
                    drawerLayout.closeDrawers()
                    replaceFragmentAndClear(ArticalsFragment())
                }
                else -> {
                    Toast.makeText(applicationContext, "nothing selected", Toast.LENGTH_LONG).show()
                }
            }
            false
        }
        replaceFragmentAndClear(ArticalsFragment())
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (toggle!!.onOptionsItemSelected(item)) true else super.onOptionsItemSelected(item)
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.parent_container, fragment)
        transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    fun replaceFragmentAndClear(fragment: Fragment) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.parent_container, fragment)
        transaction.addToBackStack(fragment.tag)
        transaction.commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val fragment = supportFragmentManager.findFragmentById(R.id.parent_container)
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