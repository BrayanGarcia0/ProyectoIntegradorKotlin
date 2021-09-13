import java.lang.Exception

data class Parking(val vehicles: MutableSet<Parkable>){
    private val maxVehicles: Short = 20
    private var vehiclesXProfits: Pair<Int, Int> = Pair(0,0)

    fun checkinVehicle(vehicle: Vehicle): Boolean{
        return (vehicles.size < maxVehicles && vehicles.add(Parkable(vehicle))).also {
            if(it) println("Welcome to AlkeParking!") else println("Sorry, the check-in failed")
        }
    }

    fun checkOutVehicle(plate: String, success: (Int) -> (Unit) = ::onSuccess, error: () -> (Unit) = ::onError){
        try {
            val parkableSearched = vehicles.filter { parkable ->
                parkable.vehicle?.let {
                    it.plate == plate
                } ?: false
            }[0]

            val fee = parkableSearched.checkOutVehicle()

            if(fee == 0) {
                error()
            }
            else {
                success(fee)
            }

        } catch (e: Exception){
            error()
        }

    }

    fun showEarnings(){
        println("${vehiclesXProfits.first} vehicles have checked out and have earnings of \\$${vehiclesXProfits.second}")
    }

    fun listVehicles(){
        println("Vehicles:")
        vehicles.forEach {
            it.vehicle?.let {  vehicle ->
                println(vehicle.plate)
            }
        }
    }

    private fun onSuccess (monto: Int){
        vehiclesXProfits = Pair(vehiclesXProfits.first + 1, vehiclesXProfits.second + monto)
        println("SUCCESS: Your fee is $monto. Come back soon. ")
    }

    private fun onError(){
        println("ERROR: Sorry, the check-out failed")
    }


}