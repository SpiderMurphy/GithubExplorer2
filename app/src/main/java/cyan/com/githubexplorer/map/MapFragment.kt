package cyan.com.githubexplorer.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import cyan.com.githubexplorer.R
import cyan.com.githubexplorer.graphics.MapSymbol
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_map.*
import javax.inject.Inject
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import com.mapbox.geojson.Feature
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.style.layers.PropertyFactory


class MapFragment : DaggerFragment() {
    @Inject
    lateinit var presenter: MapPresenter

    @Inject
    lateinit var mapSymbol: MapSymbol
    private lateinit var mapBoxMap: MapboxMap

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        context?.let {
            Mapbox.getInstance(it, getString(R.string.map_token))
        }
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapView.getMapAsync { mapBox ->
            mapBox.setStyle(Style.OUTDOORS) {
                it.addImage("location-marker", mapSymbol.from(resources.getDrawable(R.drawable.ic_location_on_black_24dp, null)))

                val geoJsonSource = GeoJsonSource(
                    "source-id", Feature.fromGeometry(
                        Point.fromLngLat(-87.679, 41.885)
                    )
                )
                it.addSource(geoJsonSource)

                val symbolLayer = SymbolLayer("layer-id", "source-id")
                symbolLayer.withProperties(
                    PropertyFactory.iconImage("location-marker")
                )
                it.addLayer(symbolLayer)
            }

            mapBoxMap = mapBox
        }
    }
}