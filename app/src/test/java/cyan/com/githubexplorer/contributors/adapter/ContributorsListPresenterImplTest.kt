package cyan.com.githubexplorer.contributors.adapter

import cyan.com.githubexplorer.model.data.Contributor
import io.mockk.CapturingSlot
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class ContributorsListPresenterImplTest {
    @RelaxedMockK
    private lateinit var view: ContributorsListView.ContributorsViewHolderView
    private lateinit var presenter: ContributorsListPresenter

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        presenter = ContributorsListPresenterImpl(listOf(
            Contributor("test1", 1, "Mvbhe", "test_avatar_1", 10),
            Contributor("aest2", 2, "CBnmh", "test_avatar_2", 20)
        ))
    }

    @Test
    fun testBindView_With0OffsetItem_Success() {
        presenter.bindView(1, view)
        verify { view.onBindView("test1", "10") }
    }

    @Test
    fun testGetCount_With2Items_Success() {
        assert(presenter.getCount() == 4)
    }

    @Test
    fun testDisplayAvatar_With1OffsetItem_Success() {
        presenter.displayAvatar(3, view)
        verify { view.onDisplayAvatar("test_avatar_2") }
    }

    @Test
    fun testGetViewType_WithViewTypeContributor_Success() {
        assertTrue(presenter.getViewType(1) == ContributorsListAdapter.VIEW_TYPE_CONTRIBUTOR)
    }

    @Test
    fun testGroupByFirstLetter_With2Items_Success() {
        presenter.groupByFirstLetter()
        assertTrue(presenter.getCount() == 4)
    }

    @Test
    fun testBindView_WithHeader_Success() {
        presenter.bindView(0, view)
        verify { view.onBindView("T") }
    }
}