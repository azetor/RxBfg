package com.mobilemonkeysoftware.rxbfg

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * Created by AR on 08/06/2017.
 */
class RxInfoFragment : Fragment() {

    val changeTextSubject: Subject<String> = PublishSubject.create<String>()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_rx_info, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val text = view?.findViewById(R.id.text) as TextView
        changeTextSubject
                .subscribe { text.text = it }
    }

}