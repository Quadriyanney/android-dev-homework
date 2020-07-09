import io.reactivex.rxjava3.core.Single

fun <T> Single<T>.assertCompleteAndDispose() {
    test().assertComplete().dispose()
}

fun <T> Single<T>.assertErrorAndDispose(error: Throwable) {
    test().assertNotComplete().assertError(error).dispose()
}

fun <T> Single<T>.assertValueAndDispose(value: T) {
    test().assertValue(value).dispose()
}
