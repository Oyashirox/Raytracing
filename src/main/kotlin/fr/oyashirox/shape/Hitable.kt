package fr.oyashirox.shape

import fr.oyashirox.raytracing.Hit
import fr.oyashirox.raytracing.HitData
import fr.oyashirox.raytracing.NoHit
import fr.oyashirox.raytracing.Ray

interface Hitable {
    /** Hit test.
     * @param ray The ray to test
     * @param min The minimum distance to hit confirm
     * @param max The maximum distance to hit confirm
     * @return [NoHit] if there are not hit, [HitData] that contains hit data otherwise*/
    fun hit(ray: Ray, min: Double, max: Double): Hit
}