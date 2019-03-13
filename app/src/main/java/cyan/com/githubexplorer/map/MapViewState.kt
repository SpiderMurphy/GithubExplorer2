package cyan.com.githubexplorer.map

import cyan.com.githubexplorer.model.data.LatLng

data class MapViewState(
    val startLocation: LatLng?,
    val endLocation: LatLng?,
    val airCraft: LatLng?,
    val route: List<LatLng>?
)