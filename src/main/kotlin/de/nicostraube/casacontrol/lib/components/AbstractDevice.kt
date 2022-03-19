package de.nicostraube.casacontrol.lib.components

import de.nicostraube.casacontrol.lib.HttpUtils
import de.nicostraube.casacontrol.lib.components.data.DeviceData
import de.nicostraube.casacontrol.lib.registry.IRegisterable
import java.net.http.HttpResponse
import java.net.http.HttpResponse.BodyHandlers

abstract class AbstractDevice(protected val baseStation: AbstractBaseStation) : IRegisterable {

    abstract val deviceData: DeviceData


    fun turnOnSync(): HttpResponse<String> {
        this.baseStation.requestStatusSync()
        return HttpUtils.httpClient.send(HttpUtils.getActionRequest(this.deviceData, 11), BodyHandlers.ofString())
    }

    fun turnOffSync(): HttpResponse<String> {
        this.baseStation.requestStatusSync()
        return HttpUtils.httpClient.send(HttpUtils.getActionRequest(this.deviceData, 12), BodyHandlers.ofString())
    }
}
