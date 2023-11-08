class SmartDevice {
    val name = "Android TV"
    val category = "Entertainment"
    val deviceStatus = "online"

    fun turnOn() {
        println("Smart device is turned on.")
    }

    fun turnOff() {
        println("Smart device is turned off.")
    }
}

fun main() {
    val smartTvDevice = SmartDevice()
    
    println("Device name is ${smartTvDevice.name}")
    println("Device category is ${smartTvDevice.category}")
    println("Device status is ${smartTvDevice.deviceStatus}")
}