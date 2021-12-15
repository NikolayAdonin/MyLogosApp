package com.example.logosapp.fragments

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logosapp.*
import java.util.*
import com.pusher.client.Pusher
import com.pusher.client.PusherOptions
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.view.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "ChatFragment"

class ChatFragment : Fragment() {
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun setupPusher(chat:View) {
        val options = PusherOptions()
        options.setCluster("eu")

        val pusher = Pusher("5e6d69a50132e3602fb0", options)
        val channel = pusher.subscribe("my-channel")

        channel.bind("my-event") { channelName, eventName, data ->
            val jsonObject = JSONObject(data)
            val message = Message(
                jsonObject["App.user"].toString(),
                jsonObject["message"].toString(),
                jsonObject["time"].toString().toLong()
            )
            activity?.runOnUiThread{
                adapter.addMessage(message)
                chat.messageList.scrollToPosition(adapter.itemCount - 1)
            }
        }
        pusher.connect()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val chat = inflater.inflate(R.layout.fragment_chat, container, false)
        fun resetInput(){
            chat.txtMessage.text.clear()
            val inputManager=
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(requireActivity().currentFocus!!.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }

        chat.messageList.layoutManager = LinearLayoutManager(chat.context)
        adapter = MessageAdapter(chat.context)
        chat.messageList.adapter = adapter
        chat.btnSend.setOnClickListener{
            if(chat.txtMessage.text.isNotEmpty()){
                txtMyMessage.text=txtMessage.text
                txtMyMessage.visibility = View.VISIBLE

                val message = Message(
                    App.user,
                    chat.txtMessage.text.toString(),
                    Calendar.getInstance().timeInMillis
                )
                val call = ChatService.create().postMessage(message)

                call.enqueue(object :Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        resetInput()
                        if(!response.isSuccessful){
                            Log.e(TAG, response.code().toString())
                            Toast.makeText(context, "Response was not successful", Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        resetInput()
                        Log.e(TAG,t.toString())
                        Toast.makeText(context, "Error when calling the service", Toast.LENGTH_SHORT).show()
                    }
                })
            }else{
                Toast.makeText(chat.context, "Message should not be empty", Toast.LENGTH_SHORT).show()
            }
        }
        setupPusher(chat)
        return chat
    }



}