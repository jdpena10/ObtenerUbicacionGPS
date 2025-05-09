package co.edu.unipiloto.obtenerubicacindelgps

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng

/*class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private lateinit var tvLatitud: TextView
    private lateinit var tvLongitud: TextView

    private val rutaMascota = mutableListOf<LatLng>() // Guardará los puntos del recorrido
    private var ultimaUbicacionReal: LatLng? = null // Para verificar cambios de ubicación

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        tvLatitud = findViewById(R.id.tv_latitud)
        tvLongitud = findViewById(R.id.tv_longitud)
        val btnUbicacion: Button = findViewById(R.id.btn_location)
        val btnIniciarPaseo: Button = findViewById(R.id.btn_iniciar_paseo)
        val btnMostrarRuta: Button = findViewById(R.id.btn_ver_ruta)
        val btnSimularMovimiento: Button = findViewById(R.id.btn_simular_movimiento)

        btnUbicacion.setOnClickListener { obtenerUbicacion() }
        btnIniciarPaseo.setOnClickListener { iniciarPaseo() }
        btnMostrarRuta.setOnClickListener { abrirRutaEnGoogleMaps() }
        btnSimularMovimiento.setOnClickListener { simularMovimiento() }

        locationRequest = LocationRequest.create().apply {
            interval = 10000
            fastestInterval = 5000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    val nuevaUbicacion = LatLng(location.latitude, location.longitude)

                    // Solo actuar si la ubicación es diferente a la última
                    if (nuevaUbicacion != ultimaUbicacionReal) {
                        rutaMascota.add(nuevaUbicacion)
                        ultimaUbicacionReal = nuevaUbicacion

                        tvLatitud.text = "Latitud: ${nuevaUbicacion.latitude}"
                        tvLongitud.text = "Longitud: ${nuevaUbicacion.longitude}"

                        Toast.makeText(this@MainActivity, "Movimiento detectado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener(this) { location ->
                if (location != null) {
                    tvLatitud.text = "Latitud: ${location.latitude}"
                    tvLongitud.text = "Longitud: ${location.longitude}"
                } else {
                    tvLatitud.text = "Latitud: desconocida"
                    tvLongitud.text = "Longitud: desconocida"
                }
            }
    }

    private fun iniciarPaseo() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2
            )
            return
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        Toast.makeText(this, "Iniciando paseo...", Toast.LENGTH_SHORT).show()
    }

    private fun abrirRutaEnGoogleMaps() {
        if (rutaMascota.size < 2) {
            Toast.makeText(this, "Aún no hay suficientes puntos para mostrar la ruta", Toast.LENGTH_SHORT).show()
            return
        }

        val origen = rutaMascota.first()
        val destino = rutaMascota.last()

        val uri = Uri.parse(
            "https://www.google.com/maps/dir/?api=1" +
                    "&origin=${origen.latitude},${origen.longitude}" +
                    "&destination=${destino.latitude},${destino.longitude}" +
                    "&travelmode=walking"
        )

        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show()
        }
    }

    private fun simularMovimiento() {
        val ultimaUbicacion = rutaMascota.lastOrNull() ?: LatLng(4.7110, -74.0721) // Bogotá

        val nuevaLatitud = ultimaUbicacion.latitude + 0.0001
        val nuevaLongitud = ultimaUbicacion.longitude + 0.0001
        val nuevoPunto = LatLng(nuevaLatitud, nuevaLongitud)

        rutaMascota.add(nuevoPunto)

        tvLatitud.text = "Latitud: ${nuevaLatitud}"
        tvLongitud.text = "Longitud: ${nuevaLongitud}"

        Toast.makeText(this, "Movimiento simulado agregado", Toast.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                1 -> obtenerUbicacion()
                2 -> iniciarPaseo()
            }
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }
}*/


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private lateinit var tvLatitud: TextView
    private lateinit var tvLongitud: TextView

    private val rutaMascota = mutableListOf<LatLng>() // Guardará los puntos del recorrido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        tvLatitud = findViewById(R.id.tv_latitud)
        tvLongitud = findViewById(R.id.tv_longitud)
        val btnUbicacion: Button = findViewById(R.id.btn_location)
        val btnIniciarPaseo: Button = findViewById(R.id.btn_iniciar_paseo)
        val btnMostrarRuta: Button = findViewById(R.id.btn_ver_ruta) // Asegúrate de tener este botón en el layout

        btnUbicacion.setOnClickListener { obtenerUbicacion() }
        btnIniciarPaseo.setOnClickListener { iniciarPaseo() }
        btnMostrarRuta.setOnClickListener { abrirRutaEnGoogleMaps() }

        // Configurar solicitud de ubicación continua
        locationRequest = LocationRequest.create().apply {
            interval = 5000
            fastestInterval = 2000
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        // Callback para registrar cada nueva ubicación
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                for (location in locationResult.locations) {
                    val latLng = LatLng(location.latitude, location.longitude)
                    rutaMascota.add(latLng)

                    // Mostrar ubicación en pantalla
                    tvLatitud.text = "Latitud: ${latLng.latitude}"
                    tvLongitud.text = "Longitud: ${latLng.longitude}"
                }
            }
        }
    }

    private fun obtenerUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
            return
        }

        fusedLocationClient.lastLocation
            .addOnSuccessListener(this) { location ->
                if (location != null) {
                    tvLatitud.text = "Latitud: ${location.latitude}"
                    tvLongitud.text = "Longitud: ${location.longitude}"
                } else {
                    tvLatitud.text = "Latitud: desconocida"
                    tvLongitud.text = "Longitud: desconocida"
                }
            }
    }

    private fun iniciarPaseo() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 2
            )
            return
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

        Toast.makeText(this, "Iniciando paseo...", Toast.LENGTH_SHORT).show()
    }

    private fun abrirRutaEnGoogleMaps() {
        if (rutaMascota.size < 2) {
            Toast.makeText(this, "Aún no hay suficientes puntos para mostrar la ruta", Toast.LENGTH_SHORT).show()
            return
        }

        val origen = rutaMascota.first()
        val destino = rutaMascota.last()

        val uri = Uri.parse(
            "https://www.google.com/maps/dir/?api=1" +
                    "&origin=${origen.latitude},${origen.longitude}" +
                    "&destination=${destino.latitude},${destino.longitude}" +
                    "&travelmode=walking"
        )

        val intent = Intent(Intent.ACTION_VIEW, uri)
        intent.setPackage("com.google.android.apps.maps")

        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Google Maps no está instalado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            when (requestCode) {
                1 -> obtenerUbicacion()
                2 -> iniciarPaseo()
            }
        } else {
            Toast.makeText(this, "Permiso denegado", Toast.LENGTH_SHORT).show()
        }
    }
}
