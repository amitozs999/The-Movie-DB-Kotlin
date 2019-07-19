package com.example.popularmovies

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_1.view.*
import kotlinx.android.synthetic.main.layout_1.view.parentLayout
import kotlinx.android.synthetic.main.layout_4.view.*

class similarmovieadapter(val context: Context, val namelist:List<similar>, val check:Boolean): RecyclerView.Adapter<similarmovieadapter.myviewholder>() {

    val baseURL = "https://image.tmdb.org/t/p/w342/"
    class myviewholder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
        if(check==false)
            if (namelist != null) {
                return namelist.size

            }

        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myviewholder {

        var li=parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView=li.inflate(R.layout.layout_4,parent,false)
        return myviewholder(itemView)

    }

    override fun onBindViewHolder(holder: myviewholder, position: Int) {

        val item1= this.namelist[position]
        holder.itemView.similartitle.text=item1.original_title
        val target=item1.poster_path
        Picasso.get().load(baseURL+target).into(holder.itemView.similarimage)

//        holder.itemView.parentLayout.setOnClickListener {
//
//            val intent= Intent(context,activity_second::class.java)
//            intent.putExtra("id",item1.id)
//            intent.putExtra("type","Movie")
//            ContextCompat.startActivity(context, intent, null)
//        }

    }


}