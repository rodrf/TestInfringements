package mx.universe.myinfraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnTest = findViewById<Button>(R.id.btnSearchInfr)
        btnTest.setOnClickListener {
            testService()
        }
    }

    private fun testService() {
        val mapData = mapOf(
            "date-init" to "2020-01-01",
            "date-finish" to "2021-09-01",
        )
        ApiInfringement().getCustomService().getImplicatedDetail(mapData)
            .enqueue(object : Callback<JsonArray> {
                override fun onResponse(
                    call: Call<JsonArray>,
                    response: Response<JsonArray>
                ) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        if (response.body()!!.size()>0) {
                            val parseObject = "{\"ResponseInfringements\":${response.body().toString()}}"
                            val gson = GsonBuilder().create()
                            val res = gson.fromJson(parseObject, ResponseInfringements::class.java)
                            Log.d("RESULT", "Folio infracción 1: ${res.responseInfringements?.get(0)?.folio.toString()}")
                            Log.d("RESULT", "Placa infracción 1: ${res.responseInfringements?.get(0)?.numDoc.toString()}")

                        } else {
                            Log.d("RESULT", "Sin resultados de búsqueda ${response.message()}")
                        }
                    }
                }
                override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                    Log.d("RESULT", "Error: ${t.message.toString()}")
                }
            })


    }
}