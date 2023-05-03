package com.suka.superahorro.packages

class UnitValue (var value: Float?, var unit: String? = null)
{
    override fun toString() : String {
        if ( value == null ) return " - "
        return String.format("%.2f %s", value, unit?:"")
    }
}