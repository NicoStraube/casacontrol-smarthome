package de.nicostraube.casacontrol.lib

import de.nicostraube.casacontrol.lib.components.data.DeviceData
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest

object HttpUtils {

    val httpClient: HttpClient = HttpClient.newBuilder().build()


    fun getStatusURI(ipAddress: String): URI = URI.create("http://$ipAddress/txcomm.asp")

    private fun getActionURI(ipAddress: String, deviceId: String, commandId: Int, serialNumber: String): URI =
        URI.create("http://$ipAddress/goform/commtx?command=:02:$deviceId:00:$commandId&serialn=$serialNumber")


    fun getActionRequest(deviceData: DeviceData, commandId: Int): HttpRequest =
        HttpRequest.newBuilder().uri(this.getActionURI(deviceData.baseStation.stationData.ipAddress,
            deviceData.id,
            commandId,
            deviceData.baseStation.stationData.serialNumber)).build()
}
