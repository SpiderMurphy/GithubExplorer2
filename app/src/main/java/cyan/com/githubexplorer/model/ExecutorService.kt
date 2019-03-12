package cyan.com.githubexplorer.model

import io.reactivex.ObservableTransformer
import io.reactivex.SingleTransformer

interface ExecutorService {
    fun <T> singleThreadExecutor(): SingleTransformer<T, T>
    fun <T> observableExecutor(): ObservableTransformer<T, T>
}