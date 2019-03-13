package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.map.MapResult
import cyan.com.githubexplorer.model.data.LatLng
import io.reactivex.Observable
import kotlin.random.Random

class LocalNavRepository : NavRepository {
    override fun queryStartPosition(source: LatLng): Observable<MapResult> {
        return Observable.just(MapResult.StartPositionResult(generateRandomPositionFromSource(source, 20.0)))
    }

    override fun queryEndPosition(source: LatLng): Observable<MapResult> {
        return Observable.just(MapResult.StartPositionResult(generateRandomPositionFromSource(source, 20.0)))
    }

    private fun generateRandomPositionFromSource(source: LatLng, radius: Double): LatLng {
        return LatLng(
            Random.nextDouble(source.lat - radius, source.lat + radius),
            Random.nextDouble(source.lng - radius, source.lng + radius)
        )
    }
}