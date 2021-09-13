
fun main() {

    val parking = Parking(mutableSetOf())

    checkInVehicles(parking)
    checkOutVehicles(parking)
    listVehicles(parking)
    showEarnings(parking)




}

fun checkInVehicles(parking: Parking){
    val car = Vehicle("AAA000", VehicleType.BUS, discountCard = "DISCOUNT_CARD_001")
    val moto = Vehicle("AAA001", VehicleType.MOTO)
//    val minibus = Vehicle("AAA002", VehicleType.MINIBUS)
//    val bus = Vehicle("AAA003", VehicleType.BUS, discountCard ="DISCOUNT_CARD_002")
//    val parking = Parking(mutableSetOf(Parkable(car), Parkable(moto), Parkable(minibus), Parkable(bus)))
    (1..18).forEach{parking.checkinVehicle(Vehicle( generatePlate(), VehicleType.MOTO ))}
    parking.checkinVehicle(moto)
    parking.checkinVehicle(car)
}

fun checkOutVehicles(parking: Parking){
    //    SUCCESS
    parking.checkOutVehicle("AAA001")
    parking.checkOutVehicle("AAA000")
//    Error
    parking.checkOutVehicle("AAA000")
}

fun showEarnings(parking: Parking){
    parking.showEarnings()
}

fun listVehicles(parking: Parking){
    parking.listVehicles()
}

fun generatePlate(): String{
    val alphabet: List<Char> = ('A'..'Z') + ('0'..'9')
    var plate: String = ""
    var randomChar: String
    (0..5).forEach {
        randomChar = alphabet.random().toString()
        plate = plate.plus(randomChar)
    }
    return plate
}

