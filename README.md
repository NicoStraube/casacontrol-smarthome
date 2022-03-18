## CasaControl-Smarthome

This simple library makes it easy to switch the sockets of the CasaControl smart home on and off. The library works
synchronously.

There are 2 basic ways this library can be included.

1. Either a class is created for each component in which it gets its properties directly,
2. or a class is created that contains a constructor with the data and is repeatedly instantiated for different objects.

<hr>
<details><summary><b>Method 1 - Each component is represented by its own class.</b></summary>

Define your base station:

- A class must inherit
  from [AbstractBaseStation](src/main/kotlin/de/nicostraube/casacontrol/lib/components/AbstractBaseStation.kt).

```kotlin
class BaseStation : AbstractBaseStation()
```

- After you have done that, you still have to enter the data for the base station. To do this, you simply have to
  override the variable provided for this purpose with an
  instantiated [BaseStationData](src/main/kotlin/de/nicostraube/casacontrol/lib/components/data/Data.kt') class.

```kotlin
override val stationData: BaseStationData
    get() = BaseStationData(name = "", serialNumber = "", ipAddress = "")
```

<br>
Next, define your device - a base station must already be defined for this:

- A class must inherit
  from [AbstractDevice](src/main/kotlin/de/nicostraube/casacontrol/lib/components/AbstractDevice.kt). In addition, the
  base station must be passed in the constructor and this must then be passed to the abstract device.

```kotlin
class Socket(baseStation: AbstractBaseStation) : AbstractDevice(baseStation)
```

- After you have done that, as with the base station, the data must be given. So you have to overwrite the given
  variable with the instantiated
  class [DeviceData](src/main/kotlin/de/nicostraube/casacontrol/lib/components/data/Data.kt).

```kotlin
override val deviceData: DeviceData
    get() = DeviceData(baseStation, name = "", id = "")
```

<br>
The integration of the classes you just created should look something like this:

```kotlin
val baseStation: AbstractBaseStation = BaseStation()
val socket: AbstractDevice = Socket(baseStation)
```

</details>
<br>
<details><summary><b>Method 2 - Each component is instantiated from the same class and data is passed in the constructor.</b></summary>

The basis of your base stations:

```kotlin
class BaseStation(
    private val name: String,
    private val serialNumber: String,
    private val ipAddress: String
) : AbstractBaseStation() {

    override val stationData: BaseStationData
        get() = BaseStationData(name, serialNumber, ipAddress)
}
```

The basis of your device:

```kotlin
class Socket(
    baseStation: AbstractBaseStation,
    private val name: String,
    private val id: String,
) : AbstractDevice(baseStation) {

    override val deviceData: DeviceData
        get() = DeviceData(baseStation, name, id)
}
```

<br>
The integration of the classes you just created should look something like this:

```kotlin
val baseStation: AbstractBaseStation = BaseStation(name = "", serialNumber = "", ipAddress = "")
val socket: AbstractDevice = Socket(baseStation, name = "", id = "")
```

</details>

<br>
<hr>
###After you have your components, the following methods are available to you
per [AbstractDevice](src/main/kotlin/de/nicostraube/casacontrol/lib/components/AbstractDevice.kt):

```kotlin
val baseStation: AbstractBaseStation = BaseStation(name = "", serialNumber = "", ipAddress = "")
val socket: AbstractDevice = Socket(baseStation, name = "", id = "")

socket.turnOnSync() // This will turn on the device synchronously if present.
socket.turnOffSync() // This will turn off the device synchronously if present.
```
