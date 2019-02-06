package cyan.com.githubexplorer.contributors

import cyan.com.githubexplorer.contributors.utils.ExecutorServiceSync
import cyan.com.githubexplorer.model.Repository
import cyan.com.githubexplorer.model.data.Contributor
import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ContributorsPresenterImplTest {
    @RelaxedMockK
    private lateinit var view: ContributorsView
    @RelaxedMockK
    private lateinit var repository: Repository
    private lateinit var presenter: ContributorsPresenter
    private val scheduler = TestScheduler()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        every { repository.fetchRepoContributors("ruby", "ruby") } returns Single.just(
            listOf(
                Contributor("test", 1, "MVyudu8", "test_url", 10),
                Contributor("aest", 2, "Something", "test2_url", 20)
            )
        ) .compose(ExecutorServiceSync(scheduler).singleThreadExecutor())
        every { repository.fetchRepoContributors("error", "error") } returns
                Single.error(Throwable("Network error"))
        presenter = ContributorsPresenterImpl(view, repository)
    }

    @Test
    fun testQueryContributors_With1Contributor_Success() {
        val slot = CapturingSlot<List<Contributor>>()
        presenter.queryContributors("ruby", "ruby")
        scheduler.triggerActions()
        verify { view.onDisplayContributors(capture(slot)) }
        assertTrue(slot.captured[0].login == "test")
    }

    @Test
    fun testQueryContributors_With2Contributors_Success() {
        val slot = CapturingSlot<List<Contributor>>()
        presenter.queryContributors("ruby", "ruby")
        scheduler.triggerActions()
        verify { view.onDisplayContributors(capture(slot)) }
        assertTrue(slot.captured[0].login == "aest")
        assertTrue(slot.captured[1].login == "test")
    }

    @Test
    fun testDisplayError_WithError_Displayed() {
        presenter.queryContributors("error", "error")
        verify { view.onDisplayError("Network error") }
    }
}