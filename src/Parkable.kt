import kotlin.math.ceil

data class Parkable(var vehicle: Vehicle?){

    private val TWO_HOURS_IN_MINUTES: Int = 120
    private val MINUTES_PER_FRACTION: Int = 15
    private val FRACTION_COST: Int = 5
    private val DISCOUNT_RATE: Int = 15



    override fun equals(other: Any?): Boolean {
        if(other is Parkable){
            return this.vehicle == other.vehicle
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.vehicle.hashCode()
    }

    fun checkOutVehicle(): Int{

        val fee = vehicle?.let { vehicle ->
            val hasDiscountCard: Boolean = !vehicle.discountCard.isNullOrEmpty()
            calculateFee(vehicle.vehicleType, parkedTime = vehicle.parkedTime, hasDiscountCard)
        } ?: 0

        vehicle = null

        return fee
    }

    private fun calculateFee(vehicleType: VehicleType, parkedTime: Int, hasDiscountCard: Boolean): Int{
//                    the user has 2 hours of fixed price
        var parkingCost: Int = vehicleType.tarifa

        return if(parkedTime <= TWO_HOURS_IN_MINUTES) {

            if(hasDiscountCard)
                parkingCost = ceil(parkingCost.toDouble() *(Constant.ONE_HUNDRED - DISCOUNT_RATE).toDouble()/Constant.ONE_HUNDRED).toInt()

            parkingCost
        }
        else {
//                    After 2 hours, the fraction of 15 minutes costs $5
            parkingCost += ceil( ( ((parkedTime - TWO_HOURS_IN_MINUTES)/MINUTES_PER_FRACTION.toDouble())) ).toInt() * FRACTION_COST

            if(hasDiscountCard)
                parkingCost =  ceil(parkingCost *(Constant.ONE_HUNDRED - DISCOUNT_RATE).toDouble()/Constant.ONE_HUNDRED).toInt()

            parkingCost
        }
    }
}