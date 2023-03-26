package com.nikhil.sportzapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.request.Disposable
import com.nikhil.sportzapi.MatchDetailActivity.Companion.MATCH_DATA
import com.nikhil.sportzapi.databinding.ActivityMainBinding
import com.nikhil.sportzapi.model.SportzResponse
import com.nikhil.sportzapi.utils.NetworkResult
import com.nikhil.sportzapi.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel by viewModels<MainViewModel>()
    private lateinit var _binding: ActivityMainBinding
    private lateinit var disposable: Disposable
    private var matchData: SportzResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        fetchData()
        _binding.btnGoNext.setOnClickListener {
            startActivity(Intent(this,MatchDetailActivity::class.java)
                .putExtra(MATCH_DATA,matchData))
        }

    }

    private fun fetchResponse() {
        mainViewModel.fetchSportzResponse()
//        _binding.pbDog.visibility = View.VISIBLE
    }


    private fun fetchData() {
        fetchResponse()
        mainViewModel.response.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                        matchData=it
                        _binding.tvTeamNames.text = "${response.data.teams.n4.nameFull} VS ${response.data.teams.n5.nameFull}"
                        _binding.tvDate.text = "Match Date : ${response.data.matchdetail.match.date}"
                        _binding.tvVenue.text = "Match Venue : ${response.data.matchdetail.venue.name}"
                        /*imageUrl = response.data.message
                        _binding.imgDog.load(
                            response.data.message
                        ) {
                            transformations(RoundedCornersTransformation(16f))
                        }*/
                    }
//                    _binding.pbDog.visibility = View.GONE
                }

                is NetworkResult.Error -> {
//                    _binding.pbDog.visibility = View.GONE
                    Toast.makeText(
                        this,
                        response.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }

                is NetworkResult.Loading -> {
//                    _binding.pbDog.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
