package cyan.com.githubexplorer.contributors.utils

import cyan.com.githubexplorer.model.ExecutorService
import io.reactivex.Scheduler
import io.reactivex.SingleTransformer

class ExecutorServiceSync(private val scheduler: Scheduler) : ExecutorService {
    override fun <T> singleThreadExecutor(): SingleTransformer<T, T> {
        return SingleTransformer {
            it.subscribeOn(scheduler)
                .observeOn(scheduler)
        }
    }
}