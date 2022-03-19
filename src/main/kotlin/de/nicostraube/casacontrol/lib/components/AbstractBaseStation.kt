package de.nicostraube.casacontrol.lib.components

import de.nicostraube.casacontrol.lib.HttpUtils
import de.nicostraube.casacontrol.lib.components.data.BaseStationData
import de.nicostraube.casacontrol.lib.registry.IRegisterable
import java.net.http.HttpRequest
import java.net.http.HttpResponse

abstract class AbstractBaseStation : IRegisterable {

    abstract val stationData: BaseStationData

    private val statusRequest: HttpRequest
        get() = HttpRequest.newBuilder().uri(HttpUtils.getStatusURI(this.stationData.ipAddress)).build()


    fun requestStatusSync(): HttpResponse<String> =
        HttpUtils.httpClient.send(this.statusRequest, HttpResponse.BodyHandlers.ofString())
}
