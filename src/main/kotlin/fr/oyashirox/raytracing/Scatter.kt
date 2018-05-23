package fr.oyashirox.raytracing

import fr.oyashirox.math.Vector

sealed class Scatter
object NoScatter: Scatter()
data class ScatterData(val scatteredRay: Ray, val attenuation: Vector): Scatter()