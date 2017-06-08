package com.mobilemonkeysoftware.rxbfg

import android.util.Log
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

/**
 * Created by AR on 08/06/2017.
 */
object Three {

    fun test1() {

        val subject = PublishSubject.create<Int>()
        arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
                .forEach { subject.onNext(it) }

        subject
                .subscribe { Log.i("THREE_1", "on next -> " + it) }
    }

    fun test2() {

        val subject = PublishSubject.create<Int>()
        subject
                .subscribe { Log.i("THREE_2", "on next -> " + it) }

        arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
                .forEach { subject.onNext(it) }
    }

    fun test3() {

        val subject = ReplaySubject.create<Int>()
        subject
                .subscribe { Log.i("THREE_3", "on next -> " + it) }

        arrayOf(1, 2, 3, 4, 5, 6, 7, 8)
                .forEach { subject.onNext(it) }

        subject
                .subscribe { Log.i("THREE_3_AFTER", "on next -> " + it) }
    }

    fun test4() {

        val subject = BehaviorSubject.create<Int>()
        subject
                .subscribe { Log.i("THREE_4", "on next -> " + it) }

        arrayOf(1, 2, 3, 4, 5)
                .forEach { subject.onNext(it) }

        subject
                .subscribe { Log.i("THREE_4_AFTER", "on next -> " + it) }

//        arrayOf(6, 7, 8)
//                .forEach { subject.onNext(it) }
    }

    fun test5() {

        val subject = PublishSubject.create<Int>() // pipe
        subject
                .subscribe { Log.i("THREE_5", "on next -> " + it) }

        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .subscribe(subject)
    }

    fun test6() {

        val disposable = Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .subscribe()
        Log.i("THREE_6", "is disposed -> " + disposable.isDisposed)
    }

    fun test7() {

        var disposable = Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .flatMap {
                    Observable
                            .create<Int> { emitter ->
                                Thread.sleep(1000)
                                emitter.onNext(it)
                                emitter.onComplete()
                            }
                }
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.newThread())
                .subscribe { Log.i("THREE_7", "on next -> " + it) }
//        Thread.sleep(3000)
        disposable.dispose()
        Log.i("THREE_7", "is disposed -> " + disposable.isDisposed)
    }

    fun runAll() {

        test1()
        test2()
        test3()
        test4()
        test5()
        test6()
        test7()
    }

}