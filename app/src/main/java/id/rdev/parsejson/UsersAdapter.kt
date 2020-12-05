package id.rdev.parsejson

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.rdev.parsejson.dataAdzan.ResultAdzan
import id.rdev.parsejson.dataAdzan.ResultAdzan2
import id.rdev.parsejson.dataAdzan.Timings
import id.rdev.parsejson.model.ApiSolatResponse
import id.rdev.parsejson.model.DataItem
import kotlinx.android.synthetic.main.item_user.view.*

class UsersAdapter(val dataApi: ApiSolatResponse) : RecyclerView.Adapter<UsersAdapter.MyHolder>() {

    val data = dataApi.data
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
        Log.e("UserAdapter", "position = " + position + "data = " + data?.get(position).toString())
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: DataItem?) {
            itemView.nama.text = get?.timings?.dhuhr
//            itemView.nama.text = get?.sunset
//            itemView.nama.text = get?.asr
//            itemView.nama.text = get?.isha
//            itemView.nama.text = get?.fajr
//            itemView.nama.text = get?.dhuhr
//            itemView.nama.text = get?.maghrib
//            itemView.nama.text = get?.sunrise
//            itemView.nama.text = get?.midnight
//            itemView.nama.text = get?.imsak

            //itemView.nama.text = get?.code
            //itemView.email.text = get?.status
//            itemView.notelp.text = get?.phone



//             val address = "${get?.data?.sunset}, ${get?.data?.asr}, ${get?.data?.isha}, ${get?.data?.fajr}, " +
//                     "${get?.data?.dhuhr}, ${get?.data?.maghrib}, ${get?.data?.sunrise}, ${get?.data?.midnight}"
//            itemView.alamat.text = address
        }

    }

}