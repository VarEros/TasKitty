package ni.edu.uca.taskitty.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ni.edu.uca.taskitty.R
import ni.edu.uca.taskitty.model.ONG

class OngAdapter(var context : Context, var ongList: List<ONG>,private val onClickOngLink : (ONG) -> Unit):
    RecyclerView.Adapter<OngAdapter.OngHolder>() {

    inner class OngHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvOngName : TextView
        var tvOngDesc : TextView
        var tvWhyDonate: TextView
        var ivOngLogo : ImageView
        var btnAbout: Button

        init {
            btnAbout = itemView.findViewById(R.id.btnAbout)
            tvOngName = itemView.findViewById(R.id.tvOngName)
            tvOngDesc = itemView.findViewById(R.id.tvOngDesc)
            tvWhyDonate = itemView.findViewById(R.id.tvWhyDonate)
            ivOngLogo = itemView.findViewById(R.id.ivOngLogo)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OngHolder {
        var itemView =  LayoutInflater.from(context).inflate(R.layout.charity_element, parent, false)
        return OngHolder(itemView)
    }

    override fun onBindViewHolder(holder: OngHolder, position: Int) {
        var ong = ongList[position]
        holder.ivOngLogo.setImageResource(ong.logo)
        holder.tvOngName.text = ong.title
        holder.tvOngDesc.text = ong.description
        holder.tvWhyDonate.text = ong.wDonar

        holder.btnAbout.setOnClickListener {
            onClickOngLink(ong)
        }
    }

    override fun getItemCount(): Int {
        return ongList.size
    }

}