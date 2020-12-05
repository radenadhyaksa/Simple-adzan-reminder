package id.rdev.parsejson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import id.rdev.parsejson.dataAdzan.ResultAdzan
import id.rdev.parsejson.dataAdzan.ResultAdzan2
import id.rdev.parsejson.dataAdzan.Timings
import id.rdev.parsejson.model.ApiSolatResponse
import id.rdev.parsejson.model.DataItem
import id.rdev.parsejson.model.Date
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {

    var data: DataItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NetworkConfig().getService()
            .getUsers(
                "Jakarta",
                "Indonesia",
                "11",
                "12",
                "2020"
            )
            .enqueue(object : Callback<ApiSolatResponse> {
                override fun onFailure(call: Call<ApiSolatResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(
                    call: Call<ApiSolatResponse>,
                    response: Response<ApiSolatResponse>
                ) {

                    var datePray = ""
                    //date
                    val frmt = SimpleDateFormat("dd MMM yyyy")
                    val datenow = frmt.format(java.util.Date())
                    Log.e("MainActivity", "current date" + datenow)
                    println("DATE = "+ datenow)

                    //time
//                    val frmtTime = SimpleDateFormat("hh:mm tt")
//                    val timenow = frmtTime.format(java.util.Date())
//                    println("TIME = "+ timenow)



                        var index = 0
                    while (datePray != datenow) {
                        datePray = response.body()?.data?.get(index)?.date?.readable.toString()
                        if (datePray == datenow){
                            data = response.body()?.data?.get(index)
                        } else {
                            index +=1
                        }
                    }

                    Log.d("MainActivity", "response1" + response.body()?.data?.get(index))

                    tvSubuh.text = data?.timings?.fajr
                    tvDzuhur.text = data?.timings?.dhuhr
                    tvAsar.text = data?.timings?.asr
                    tvMaghrib.text = data?.timings?.maghrib
                    tvIsya.text = data?.timings?.isha
                    tvDate.text = datenow

//                    rvUser.adapter = response.body()?.let { UsersAdapter(it) }
                }

            })
    }
}
