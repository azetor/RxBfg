package com.mobilemonkeysoftware.rxbfg

import android.util.Log
import io.reactivex.Flowable
import io.reactivex.Observable

/**
 * Created by AR on 08/06/2017.
 */
object Two {

    fun test1() {

        Observable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .subscribe { Log.i("TWO_1", "on next -> " + it) }
    }

    fun test2() {

        Flowable
                .just(1, 2, 3, 4, 5, 6, 7, 8)
                .subscribe { Log.i("TWO_2", "on next -> " + it) }
    }

    fun test3() {

        Observable
                .just(1, 2, 3, 4)
                .mergeWith {
                    Observable
                            .just(5, 6, 7, 8)
                }
                .subscribe { Log.i("TWO_3", "on next -> " + it) }
    }

    fun test4() {

        Observable
                .just(1, 2, 3, 4) // 4x4
                .mergeWith {
                    Observable
                            .just("5", "6", "7", "8")
                }
                .subscribe { Log.i("TWO_4", "on next -> " + it) }
    }

    fun test5() {

        Observable
                .concat(
                        Observable.just(1, 2, 3, 4),
                        Observable.just("5", "6", "7", "8"))
                .subscribe { Log.i("TWO_5", "on next -> " + it) }
    }

    fun test6() {

        Observable
                .concat(
                        Observable
                                .just(1, 2, 3, 4),
                        Observable
                                .just("5", "6", "RX", "7", "8")
                                .filter({
                                    try {
                                        return@filter it.toInt() is Int
                                    } catch (e: Exception) {
                                        return@filter false
                                    }
                                }))
                .subscribe { Log.i("TWO_6", "on next -> " + it) }
    }

    fun test7() {

        Observable
                .concat(
                        Observable
                                .just(1, 2, 3, 4),
                        Observable
                                .just("5", "6", "RX", "7", "8")
                                .flatMap {
                                    Observable
                                            .create<Int> { emitter ->
                                                try {
//                                                    emitter.onNext(it.toIntOrNull()) // ??
                                                    emitter.onNext(it.toInt())
                                                    emitter.onComplete()
                                                } catch (e: NumberFormatException) {
                                                    emitter.onComplete() // no emission
                                                } catch (e: Exception) {
                                                    emitter.onError(e)
                                                }
                                            }
                                }
                )
                .subscribe { Log.i("TWO_7", "on next -> " + it) }
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