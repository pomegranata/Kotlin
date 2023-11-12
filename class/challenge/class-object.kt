import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class SmartDevice (val name: String, val category: String) {
    
    var deviceStatus = "online"
        protected set

    open fun isDeviceOn() : Boolean {
        if (deviceStatus == "online" || deviceStatus == "on") {
            return (true)
        } else {
            return (false)
        }
    }

    open val deviceType = "unknown"
    
    open fun turnOn() {
        deviceStatus = "on"
    }

    open fun turnOff() {
        deviceStatus = "off"
    }

    fun printDeviceInfo() {
        println("Device name: $name")
        println("Category: $category")
        println("Type: $deviceType")
    }
} 

class SmartTvDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {
        
        override val deviceType = "Smart Tv"

        private var speakerVolume by RangeRegulator(initialValue = 2, minValue = 0, maxValue = 100)

        private var channelNumber by RangeRegulator(initialValue = 1, minValue = 0, maxValue = 200)
        
        fun increaseSpeakerVolume() {
            speakerVolume++
            println("Speaker volume increased to $speakerVolume")
        }

        fun decreaseSpeakerVolume() {
            speakerVolume--
            println("Speaker volume decreased to $speakerVolume")
        }

        fun nextChannel() {
            channelNumber++
            println("Channel number increased to $channelNumber")
        }

        fun previousChannel() {
            channelNumber--
            println("Channel number decreased to $channelNumber")
        }

        override fun turnOn() {
            super.turnOn()
            println("$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                "set to $channelNumber")
        }
        
        override fun turnOff() {
            super.turnOff()
            println("$name turned off")
        }
    }

class SmartLightDevice(deviceName: String, deviceCategory: String) :
    SmartDevice(name = deviceName, category = deviceCategory) {

        override val deviceType = "Smart Light"

        private var brightnessLevel by RangeRegulator(initialValue = 0, minValue = 0, maxValue = 100)

        fun increaseBrightness() {
            brightnessLevel++
            println("Brightness level increased to $brightnessLevel")
        }

        fun decreaseBrightness() {
            brightnessLevel--
            println("Brightness level decreased to $brightnessLevel")
        }

        override fun turnOn() {
            super.turnOn()
            brightnessLevel = 2
            println("$name turned on. The brightness level is $brightnessLevel")
        }

        override fun turnOff() {
            super.turnOff()
            brightnessLevel = 0
            println("Smart Light turned off")
        }

    }

class SmartHome (
    val smartDevice: SmartDevice,
    val smartTvDevice: SmartTvDevice,
    val smartLightDevice: SmartLightDevice
    ) {
    
    var deviceTurnOnCount = 0
        private set
    
    fun turnOnTv() {
        deviceTurnOnCount++
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        deviceTurnOnCount--
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun decreaseTvVolume() {
        smartTvDevice.decreaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun changeTvChannelToPrevious() {
        smartTvDevice.previousChannel()
    }

    fun turnOnLight() {
        deviceTurnOnCount++
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        deviceTurnOnCount--
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun decreseLightBrightness() {
        smartLightDevice.decreaseBrightness()
    }

    fun printSmartTvInfo() {
        smartDevice.printDeviceInfo()
    }

    fun printLightInfo() {
        smartDevice.printDeviceInfo()
    }

    fun turnOffAllDevices() {
        turnOffTv()
        turnOffLight()
    }
}

class RangeRegulator(
    initialValue: Int,
    private val minValue: Int,
    private val maxValue: Int
) : ReadWriteProperty<Any?, Int> {

    var fieldData = initialValue
    
    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        return fieldData
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Int) {
        if (value in minValue..maxValue) {
            fieldData = value
        }
    }
}

fun main() {
    var smartDevice: SmartDevice = SmartTvDevice("Android TV", "Entertainment")
    smartDevice.turnOn()

    if (smartDevice.isDeviceOn()) {
        smartDevice = SmartTvDevice("Android TV", "Entertainment")
        smartDevice.printDeviceInfo()
        smartDevice.increaseSpeakerVolume()
        smartDevice.decreaseSpeakerVolume()
        smartDevice.nextChannel()
        smartDevice.previousChannel()
        smartDevice.turnOff()
    } else {
        println("The device is off")
    }
        
    smartDevice = SmartLightDevice("Google Light", "Utility")
    smartDevice.turnOn()
    
    if (smartDevice.isDeviceOn()) {
    smartDevice.increaseBrightness()
    smartDevice.decreaseBrightness()
    smartDevice.turnOff()
    } else {
        println("The device is off")
    }

}