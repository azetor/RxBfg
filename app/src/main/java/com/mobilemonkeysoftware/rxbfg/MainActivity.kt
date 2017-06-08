package com.mobilemonkeysoftware.rxbfg

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by AR on 08/06/2017.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var content: RxInfoFragment

    private val listener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_one -> {
                content.changeTextSubject.onNext(
                        "http://reactivex.io" +
                                "\n1. Observable (Single, Flowable) - emitter" +
                                "\n2. Subject - pipe" +
                                "\n3. Scheduler - multithreading" +
                                "\n4. Operators - map, flatMap, zip, combine, merge" +
                                "\n5. Disposable - state"
                )
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_two -> {
                content.changeTextSubject.onNext("Observable")
                Two.runAll()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_three -> {
                content.changeTextSubject.onNext("Subject & Disposable")
                Three.runAll()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation = findViewById(R.id.navigation) as BottomNavigationView
        navigation.setOnNavigationItemSelectedListener(listener)
        listener.onNavigationItemSelected(navigation.menu.getItem(0))
    }

    override fun onAttachFragment(fragment: Fragment?) {
        super.onAttachFragment(fragment)

        content = fragment as RxInfoFragment
    }

}
