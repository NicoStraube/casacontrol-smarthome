package de.nicostraube.casacontrol.lib.components.impl

import de.nicostraube.casacontrol.lib.components.AbstractBaseStation
import de.nicostraube.casacontrol.lib.components.data.BaseStationData

class AbstractBaseStationImpl(
    override val stationData: BaseStationData,
    override val objectId: String,
) : AbstractBaseStation()
