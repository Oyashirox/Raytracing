@file:Suppress("unused")

package fr.oyashirox.raytracing

import fr.oyashirox.material.Material
import fr.oyashirox.math.Vector

sealed class Hit
object NoHit: Hit()
class HitData(
        val distance: Double,
        val position: Vector,
        val normal: Vector,
        val material: Material,
        val t: Double = 0.0
): Hit()