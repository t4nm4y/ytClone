package com.example.ytclone
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class mainAdapter(val homeFeed: HomeFeed) :RecyclerView.Adapter<mainAdapter.CustomViewHolder>(){
 val videoTitles= listOf("one","2nd","three","more")

    class  CustomViewHolder(val view:View):RecyclerView.ViewHolder(view){
        val textView_vidTitle: TextView = view.findViewById(R.id.textView_vidTitle)
        val txt_chName: TextView = view.findViewById(R.id.txt_chName)
        val thumbnl: ImageView=view.findViewById(R.id.img_thumbnail)
        val logo: ImageView=view.findViewById(R.id.img_chLogo)
    }
    override fun getItemCount(): Int {  //tells the no, of elements in the list
        return homeFeed.videos.count()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //how do we create a view:
        //can be easily done by creating a new layout resource

        val layoutInflater=LayoutInflater.from(parent.context)
        val cellForRow=layoutInflater.inflate(R.layout.vid_row,parent,false)
        return CustomViewHolder((cellForRow))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val video=homeFeed.videos.get(position)
        holder.textView_vidTitle.text=video.name
        holder.txt_chName.text=video.channel.name
        Picasso.get().load(video.imageUrl).into(holder.thumbnl);
        Picasso.get().load(video.channel.profileimageUrl).into(holder.logo);
    }
}
