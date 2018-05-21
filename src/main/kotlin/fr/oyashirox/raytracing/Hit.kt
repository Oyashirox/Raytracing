@file:Suppress("unused")

package fr.oyashirox.raytracing

import fr.oyashirox.math.Vector

sealed class Hit
object NoHit: Hit()
class HitData(
        val distance: Double,
        val position: Vector,
        val normal: Vector,
        val t: Double = 0.0
): Hit()