package de.nicostraube.casacontrol.lib.components.data

import de.nicostraube.casacontrol.lib.components.AbstractBaseStation

data class BaseStationData(val name: String, val serialNumber: String, val ipAddress: String)
data class DeviceData(val baseStation: AbstractBaseStation, val name: String, val id: String)
