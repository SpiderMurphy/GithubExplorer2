package cyan.com.githubexplorer.model

import io.reactivex.SingleTransformer

class ExecutorServiceAsync : ExecutorService {
    override fun <T> singleThreadExecutor(): SingleTransformer<T, T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}