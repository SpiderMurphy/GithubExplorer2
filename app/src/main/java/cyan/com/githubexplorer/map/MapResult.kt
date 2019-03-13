package cyan.com.githubexplorer.map

import cyan.com.githubexplorer.model.data.LatLng

sealed class MapResult {
    class StartPositionResult(val position: LatLng): MapResult()
}