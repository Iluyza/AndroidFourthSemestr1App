package com.example.fourthsemestr1.models.convertors

import com.example.fourthsemestr1.models.WindDeg

object WindDegConvertor {
    private const val res1 = 0
    private const val res2 = 45
    private const val res3 = 90
    private const val res4 = 135
    private const val res5 = 180
    private const val res6 = 225
    private const val res7 = 270
    fun convertWindDeg(deg: Int): WindDeg {
        return when ((deg / 45 + 2* (deg%45) / 45) * 45 % 360) {
            res1 -> WindDeg.N
            res2 -> WindDeg.NE
            res3 -> WindDeg.E
            res4 -> WindDeg.SE
            res5 -> WindDeg.S
            res6 -> WindDeg.SW
            res7 -> WindDeg.W
            else -> WindDeg.NW
        }
    }
}

