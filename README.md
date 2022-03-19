## CasaControl-Smarthome

This simple library makes it easy to switch the sockets of the CasaControl smart home on and off. The library works
synchronously.

According to my tests, with the simplest implementation, as shown in the examples, there should be a minimum time of one
and a half seconds between each interaction with the base station. If this time is not reached, it can happen that after
the first action, the following actions are not correctly processed and executed by the base station.

<b>An implementation of the radiator thermostats is not planned, but I might devote myself to that on request.</b>

There are 2 basic ways this library can be included.

1. Either a class is created for each component in which it gets its properties directly,
2. or you directly use
   the [AbstractBaseStationImpl](src/main/kotlin/de/nicostraube/casacontrol/lib/components/impl/AbstractBaseStationImpl.kt)
   and [AbstractDeviceImpl](src/main/kotlin/de/nicostraube/casacontrol/lib/components/impl/AbstractDeviceImpl.kt)
   classes

Note: The required componentId is currently to be specified, but has no relevance.

<hr>
<details><summary><b>Method 1 - Each component is represented by its own class.</b></summary>

Define your base station:

- A class must inherit
  from [AbstractBaseStation](src/main/kotlin/de/nicostraube/casacontrol/lib/components/AbstractBaseStation.kt).

```kotlin
class BaseStation : AbstractBaseStation()
```

- After you have done that, you still have to provide the data for the base station and an "unique" id for the
  component. To do this, you simply have to override the variables provided for this purpose.
- The data with the [BaseStationData](src/main/kotlin/de/nicostraube/casacontrol/lib/components/data/Data.kt) class and
  the componentId with an "unique" String.

```kotlin
override val stationData: BaseStationData
    get() = BaseStationData(name = "", serialNumber = "", ipAddress = "")
override val componentId: String
    get() = "" // For example stationData.name
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
override val componentId: String
    get() = "" // For example deviceData.name
```

<br>
The integration of the classes you just created should look something like this:

```kotlin
val baseStation: AbstractBaseStation = BaseStation()
val socket: AbstractDevice = Socket(baseStation)
```

</details>
<br>
<details><summary><b>Method 2 - Using the AbstractBaseStationImpl and AbstractDeviceImpl classes.</b></summary>

The basis of your base stations:

```kotlin
val baseStation =
    AbstractBaseStationImpl(
        BaseStationData(name = "", serialNumber = "", ipAddress = ""),
        componendId = "")
```

The basis of your devices:

```kotlin
val socket =
    AbstractDeviceImpl(
        DeviceData(baseStation, name = "", deviceId = ""),
        componentId = "")
```

</details>

<hr>

#### After you have created your components, your have the following methods per [AbstractDevice](src/main/kotlin/de/nicostraube/casacontrol/lib/components/AbstractDevice.kt) class:

```kotlin
AbstractDevice#turnOnSync() // This will turn on the device synchronously if present.
AbstractDevice#turnOffSync() // This will turn off the device synchronously if present.
```
