package de.nicostraube.casacontrol.lib.components.impl

import de.nicostraube.casacontrol.lib.components.AbstractDevice
import de.nicostraube.casacontrol.lib.components.data.DeviceData

class AbstractDeviceImpl(
    override val deviceData: DeviceData,
    override val componentId: String,
) : AbstractDevice(deviceData.baseStation)
