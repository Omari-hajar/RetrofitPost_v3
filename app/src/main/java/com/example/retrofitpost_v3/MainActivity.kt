package com.example.retrofitpost_v3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitpost_v3.RetrofitInstance.api
import com.example.retrofitpost_v3.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import okhttp3.internal.http1.Http1ExchangeCodec
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var rvMain: RVAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java )
            startActivity(intent)
        }

      //  val name = intent.getStringExtra("name")
       // val location = intent.getStringExtra("location")

      //  Log.d("Name", name.toString())
      //  Log.d("Name", location.toString())

        //postData(name.toString(), location.toString())


        lifecycleScope.launchWhenCreated {
            val response = try{
                RetrofitInstance.api.getData()
            }catch (e: IOException){
                Log.d("Main-Error", e.message.toString())
                return@launchWhenCreated

            }catch (e: HttpException){
                Log.d("Main-Error", e.message.toString())
                return@launchWhenCreated
            }
            if (response.isSuccessful && response.body() !=null){
                rvMain.items = response.body()!!
            } else{
                Log.d("Main-Error", "Response not successful")
            }
        }





    }


    private fun setupRV()= binding.rvMain.apply {
        rvMain = RVAdapter()
        adapter = rvMain
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


    private fun postData(name: String, location: String){
        //val item = Data(2, "Ali", "UAE")
       api.postData(Data(0,name, location)).enqueue(object : Callback<Data>{
       override fun onResponse(call: Call<Data>, response: Response<Data>){
              Log.d("Main-success", "Data Added")
       }
       override fun onFailure(call: Call<Data>, t: Throwable){
               Log.d("Main-Failure", "Data Not Added")
       }
       })

    }
}