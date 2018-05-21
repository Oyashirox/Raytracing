package fr.oyashirox.shape

import fr.oyashirox.raytracing.Ray

interface Hitable {
    fun hit(ray: Ray): Boolean
}