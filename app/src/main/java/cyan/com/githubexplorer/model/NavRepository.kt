package cyan.com.githubexplorer.model

import cyan.com.githubexplorer.map.MapResult
import cyan.com.githubexplorer.model.data.LatLng
import io.reactivex.Observable

interface NavRepository {
    fun queryStartPosition(source: LatLng): Observable<MapResult>
    fun queryEndPosition(source: LatLng): Observable<MapResult>
}