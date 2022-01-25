package com.example.ytclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.VideoView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchJason()
    }
    fun fetchJason()
    {
        println("fetchjson running")

        //val is how u define a constant that doesn't change
        val url="https://api.letsbuildthatapp.com/youtube/home_feed"
        val request=Request.Builder().url(url).build() //creating a request object
        val client=OkHttpClient() //constructor to construct the client
        client.newCall(request).enqueue(object :Callback{
            override fun onResponse(call: Call, response: Response) {
                val bdy= response.body?.string()

                val gson= GsonBuilder().create()
                val homefeed=gson.fromJson(bdy, HomeFeed::class.java)
                var recyclerView= findViewById<RecyclerView>(R.id.recyclerv_main)

                runOnUiThread{
                    recyclerView.adapter=mainAdapter(homefeed)
                }
            }
            override fun onFailure(call: Call, e: IOException) {
                println("Not yet implemented")
            }
        })
    }
}
//"id": 1,
//"name": "Instagram Firebase - Learn How to Create Users, Follow, and Send Push Notifications",
//"link": "https://www.letsbuildthatapp.com/course/instagram-firebase",
//"imageUrl": "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/04782e30-d72a-4917-9d7a-c862226e0a93",
//"channel": {
//    "name": "Lets Build That App",
//    "profileimageUrl": "https://letsbuildthatapp-videos.s3-us-west-2.amazonaws.com/dda5bc77-327f-4944-8f51-ba4f3651ffdf",
//    "numberOfSubscribers": 100000
class HomeFeed(val videos: List<Vid>)

class Vid(val id: Int, val name: String, val link: String, val imageUrl: String, val channel: Channel, val numberOfViews: Int)

class Channel(val name: String, val profileimageUrl: String, val numberOfSubscribers: Int)