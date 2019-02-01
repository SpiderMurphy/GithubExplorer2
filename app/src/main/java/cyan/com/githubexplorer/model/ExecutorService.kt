package cyan.com.githubexplorer.model

import io.reactivex.SingleTransformer

interface ExecutorService {
    fun <T> singleThreadExecutor(): SingleTransformer<T, T>
}