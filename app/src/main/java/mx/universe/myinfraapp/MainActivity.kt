package mx.universe.myinfraapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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

    fun testService() {
        val mapData = mapOf(
            "date-init" to "2020-01-01",
            "date-finish" to "2021-09-01",
        )
        ApiInfringement().getCustomService().getImplicatedDetail(mapData)
            .enqueue(object : Callback<ResponseInfringements> {
                override fun onResponse(
                    call: Call<ResponseInfringements>,
                    response: Response<ResponseInfringements>
                ) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        if (response.body()!!.responseInfringements?.size!! > 0) {
                            val res = response.body()!!.responseInfringements
                            Log.d("RESULT", res?.get(0)?.folio.toString())

                        } else {
                            Log.d("RESULT", response.message())
                        }
                    }
                }

                override fun onFailure(call: Call<ResponseInfringements>, t: Throwable) {
                    Log.d("RESULT", t.message.toString())
                }
            })


    }
}