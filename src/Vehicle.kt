import java.util.*

data class Vehicle(val plate: String, val vehicleType: VehicleType, val checkInTime: Calendar = Calendar.getInstance(), val discountCard: String? = null){
    override fun equals(other: Any?): Boolean {
        if(other is Vehicle){
            return this.plate == other.plate
        }
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return this.plate.hashCode()
    }

    val parkedTime: Int
        get() = ((Calendar.getInstance().timeInMillis - checkInTime.timeInMillis) / Constant.MINUTES_IN_MILISECONDS).toInt()
}