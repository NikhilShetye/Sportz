package com.nikhil.sportzapi

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.sportzapi.adapter.PlayerAdapter
import com.nikhil.sportzapi.databinding.ActivityMatchDetailBinding
import com.nikhil.sportzapi.model.VisiblePlayer
import com.nikhil.sportzapi.model.SportzResponse

class MatchDetailActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMatchDetailBinding
    private var playerList = ArrayList<VisiblePlayer>()
    private lateinit var playerAdapter: PlayerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMatchDetailBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        val matchData = intent.getSerializableExtra(MATCH_DATA) as? SportzResponse
        matchData?.let {
            playerAdapter = PlayerAdapter(
                getPlayerList(
                    teamAVisible = true,
                    teamBVisible = true,
                    matchData = it
                )
            ) { visiblePlayer ->
                adapterOnClick(visiblePlayer)
            }
            _binding.rvMatchDetails.layoutManager = LinearLayoutManager(this)
            _binding.rvMatchDetails.adapter = playerAdapter

            setOnPlayerFilterChangeListener(it)

            setTeamNames(it)

        }

    }

    private fun setTeamNames(sportzResponse: SportzResponse) {
        _binding.rbTeamA.text = sportzResponse.teams.n4.nameFull
        _binding.rbTeamB.text = sportzResponse.teams.n5.nameFull
    }

    private fun adapterOnClick(visiblePlayer: VisiblePlayer) {
        showDialog(visiblePlayer)

    }

    private fun showDialog(visiblePlayer: VisiblePlayer) {

        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle(visiblePlayer.name)
            setMessage(
                        "Batting Style : ${visiblePlayer.batStyle}\n\n" +
                        "Batting Average : ${visiblePlayer.batAvg}\n\n" +
                        "Strikerate : ${visiblePlayer.strikeRate}\n\n" +
                        "Runs : ${visiblePlayer.runs}\n\n" +
                        "Bowling Style : ${visiblePlayer.bowlStyle}\n\n" +
                        "Bowling Average : ${visiblePlayer.bowlAvg}\n\n" +
                        "Economyrate : ${visiblePlayer.economyRate}\n\n" +
                        "Wickets : ${visiblePlayer.wickets}\n\n"
            )
            show()
        }


    }

    private fun setOnPlayerFilterChangeListener(matchData: SportzResponse) {
        _binding.rgSelectTeam.setOnCheckedChangeListener { _, checkedId ->
            if (R.id.rb_team_a == checkedId) setFilteredPlayerList(
                matchData,
                teamAVisible = true,
                teamBVisible = false
            )
            if (R.id.rb_team_b == checkedId) setFilteredPlayerList(
                matchData,
                teamAVisible = false,
                teamBVisible = true
            )
            if (R.id.rb_team_all == checkedId) setFilteredPlayerList(
                matchData,
                teamAVisible = true,
                teamBVisible = true
            )
        }
    }

    private fun setFilteredPlayerList(
        matchData: SportzResponse,
        teamAVisible: Boolean,
        teamBVisible: Boolean,
    ) {
        playerAdapter.updatePlayerList(
            getPlayerList(
                teamAVisible,
                teamBVisible,
                matchData
            )
        )
    }


    private fun getPlayerList(
        teamAVisible: Boolean,
        teamBVisible: Boolean,
        matchData: SportzResponse,
    ): ArrayList<VisiblePlayer> {
        val playersTeamA = matchData.teams.n4.players
        val playersTeamB = matchData.teams.n5.players
        playerList = arrayListOf()
        if (teamAVisible) {
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n3632.nameFull,
                    playersTeamA.n3632.batting.style,
                    playersTeamA.n3632.batting.average,
                    playersTeamA.n3632.batting.strikerate,
                    playersTeamA.n3632.batting.runs,
                    playersTeamA.n3632.bowling.style,
                    playersTeamA.n3632.bowling.average,
                    playersTeamA.n3632.bowling.economyrate,
                    playersTeamA.n3632.bowling.wickets,
                    isKeeper = true,
                    isCaptain = false
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n3722.nameFull,
                    playersTeamA.n3722.batting.style,
                    playersTeamA.n3722.batting.average,
                    playersTeamA.n3722.batting.strikerate,
                    playersTeamA.n3722.batting.runs,
                    playersTeamA.n3722.bowling.style,
                    playersTeamA.n3722.bowling.average,
                    playersTeamA.n3722.bowling.economyrate,
                    playersTeamA.n3722.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n3852.nameFull ,
                    playersTeamA.n3852.batting.style,
                    playersTeamA.n3852.batting.average,
                    playersTeamA.n3852.batting.strikerate,
                    playersTeamA.n3852.batting.runs,
                    playersTeamA.n3852.bowling.style,
                    playersTeamA.n3852.bowling.average,
                    playersTeamA.n3852.bowling.economyrate,
                    playersTeamA.n3852.bowling.wickets,
                    isKeeper = false,
                    isCaptain = true
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n4176.nameFull,
                    playersTeamA.n4176.batting.style,
                    playersTeamA.n4176.batting.average,
                    playersTeamA.n4176.batting.strikerate,
                    playersTeamA.n4176.batting.runs,
                    playersTeamA.n4176.bowling.style,
                    playersTeamA.n4176.bowling.average,
                    playersTeamA.n4176.bowling.economyrate,
                    playersTeamA.n4176.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n4532.nameFull,
                    playersTeamA.n4532.batting.style,
                    playersTeamA.n4532.batting.average,
                    playersTeamA.n4532.batting.strikerate,
                    playersTeamA.n4532.batting.runs,
                    playersTeamA.n4532.bowling.style,
                    playersTeamA.n4532.bowling.average,
                    playersTeamA.n4532.bowling.economyrate,
                    playersTeamA.n4532.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n5132.nameFull,
                    playersTeamA.n5132.batting.style,
                    playersTeamA.n5132.batting.average,
                    playersTeamA.n5132.batting.strikerate,
                    playersTeamA.n5132.batting.runs,
                    playersTeamA.n5132.bowling.style,
                    playersTeamA.n5132.bowling.average,
                    playersTeamA.n5132.bowling.economyrate,
                    playersTeamA.n5132.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n63187.nameFull,
                    playersTeamA.n63187.batting.style,
                    playersTeamA.n63187.batting.average,
                    playersTeamA.n63187.batting.strikerate,
                    playersTeamA.n63187.batting.runs,
                    playersTeamA.n63187.bowling.style,
                    playersTeamA.n63187.bowling.average,
                    playersTeamA.n63187.bowling.economyrate,
                    playersTeamA.n63187.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n63751.nameFull,
                    playersTeamA.n63751.batting.style,
                    playersTeamA.n63751.batting.average,
                    playersTeamA.n63751.batting.strikerate,
                    playersTeamA.n63751.batting.runs,
                    playersTeamA.n63751.bowling.style,
                    playersTeamA.n63751.bowling.average,
                    playersTeamA.n63751.bowling.economyrate,
                    playersTeamA.n63751.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n65867.nameFull,
                    playersTeamA.n65867.batting.style,
                    playersTeamA.n65867.batting.average,
                    playersTeamA.n65867.batting.strikerate,
                    playersTeamA.n65867.batting.runs,
                    playersTeamA.n65867.bowling.style,
                    playersTeamA.n65867.bowling.average,
                    playersTeamA.n65867.bowling.economyrate,
                    playersTeamA.n65867.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n66818.nameFull,
                    playersTeamA.n66818.batting.style,
                    playersTeamA.n66818.batting.average,
                    playersTeamA.n66818.batting.strikerate,
                    playersTeamA.n66818.batting.runs,
                    playersTeamA.n66818.bowling.style,
                    playersTeamA.n66818.bowling.average,
                    playersTeamA.n66818.bowling.economyrate,
                    playersTeamA.n66818.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamA.n9844.nameFull,
                    playersTeamA.n9844.batting.style,
                    playersTeamA.n9844.batting.average,
                    playersTeamA.n9844.batting.strikerate,
                    playersTeamA.n9844.batting.runs,
                    playersTeamA.n9844.bowling.style,
                    playersTeamA.n9844.bowling.average,
                    playersTeamA.n9844.bowling.economyrate,
                    playersTeamA.n9844.bowling.wickets,
                )
            )
        }

        if (teamBVisible) {
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n10167.nameFull ,
                    playersTeamB.n10167.batting.style,
                    playersTeamB.n10167.batting.average,
                    playersTeamB.n10167.batting.strikerate,
                    playersTeamB.n10167.batting.runs,
                    playersTeamB.n10167.bowling.style,
                    playersTeamB.n10167.bowling.average,
                    playersTeamB.n10167.bowling.economyrate,
                    playersTeamB.n10167.bowling.wickets,
                    isKeeper = true,
                    isCaptain = false
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n10172.nameFull,
                    playersTeamB.n10172.batting.style,
                    playersTeamB.n10172.batting.average,
                    playersTeamB.n10172.batting.strikerate,
                    playersTeamB.n10172.batting.runs,
                    playersTeamB.n10172.bowling.style,
                    playersTeamB.n10172.bowling.average,
                    playersTeamB.n10172.bowling.economyrate,
                    playersTeamB.n10172.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n11703.nameFull,
                    playersTeamB.n11703.batting.style,
                    playersTeamB.n11703.batting.average,
                    playersTeamB.n11703.batting.strikerate,
                    playersTeamB.n11703.batting.runs,
                    playersTeamB.n11703.bowling.style,
                    playersTeamB.n11703.bowling.average,
                    playersTeamB.n11703.bowling.economyrate,
                    playersTeamB.n11703.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n11706.nameFull,
                    playersTeamB.n11706.batting.style,
                    playersTeamB.n11706.batting.average,
                    playersTeamB.n11706.batting.strikerate,
                    playersTeamB.n11706.batting.runs,
                    playersTeamB.n11706.bowling.style,
                    playersTeamB.n11706.bowling.average,
                    playersTeamB.n11706.bowling.economyrate,
                    playersTeamB.n11706.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n3752.nameFull,
                    playersTeamB.n3752.batting.style,
                    playersTeamB.n3752.batting.average,
                    playersTeamB.n3752.batting.strikerate,
                    playersTeamB.n3752.batting.runs,
                    playersTeamB.n3752.bowling.style,
                    playersTeamB.n3752.bowling.average,
                    playersTeamB.n3752.bowling.economyrate,
                    playersTeamB.n3752.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n4330.nameFull ,
                    playersTeamB.n4330.batting.style,
                    playersTeamB.n4330.batting.average,
                    playersTeamB.n4330.batting.strikerate,
                    playersTeamB.n4330.batting.runs,
                    playersTeamB.n4330.bowling.style,
                    playersTeamB.n4330.bowling.average,
                    playersTeamB.n4330.bowling.economyrate,
                    playersTeamB.n4330.bowling.wickets,
                    isKeeper = false,
                    isCaptain = true
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n4338.nameFull,
                    playersTeamB.n4338.batting.style,
                    playersTeamB.n4338.batting.average,
                    playersTeamB.n4338.batting.strikerate,
                    playersTeamB.n4338.batting.runs,
                    playersTeamB.n4338.bowling.style,
                    playersTeamB.n4338.bowling.average,
                    playersTeamB.n4338.bowling.economyrate,
                    playersTeamB.n4338.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n4964.nameFull,
                    playersTeamB.n4964.batting.style,
                    playersTeamB.n4964.batting.average,
                    playersTeamB.n4964.batting.strikerate,
                    playersTeamB.n4964.batting.runs,
                    playersTeamB.n4964.bowling.style,
                    playersTeamB.n4964.bowling.average,
                    playersTeamB.n4964.bowling.economyrate,
                    playersTeamB.n4964.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n57594.nameFull,
                    playersTeamB.n57594.batting.style,
                    playersTeamB.n57594.batting.average,
                    playersTeamB.n57594.batting.strikerate,
                    playersTeamB.n57594.batting.runs,
                    playersTeamB.n57594.bowling.style,
                    playersTeamB.n57594.bowling.average,
                    playersTeamB.n57594.bowling.economyrate,
                    playersTeamB.n57594.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n57903.nameFull,
                    playersTeamB.n57903.batting.style,
                    playersTeamB.n57903.batting.average,
                    playersTeamB.n57903.batting.strikerate,
                    playersTeamB.n57903.batting.runs,
                    playersTeamB.n57903.bowling.style,
                    playersTeamB.n57903.bowling.average,
                    playersTeamB.n57903.bowling.economyrate,
                    playersTeamB.n57903.bowling.wickets,
                )
            )
            playerList.add(
                VisiblePlayer(
                    playersTeamB.n60544.nameFull,
                    playersTeamB.n60544.batting.style,
                    playersTeamB.n60544.batting.average,
                    playersTeamB.n60544.batting.strikerate,
                    playersTeamB.n60544.batting.runs,
                    playersTeamB.n60544.bowling.style,
                    playersTeamB.n60544.bowling.average,
                    playersTeamB.n60544.bowling.economyrate,
                    playersTeamB.n60544.bowling.wickets,
                )
            )
        }
        return playerList
    }

    companion object {
        const val MATCH_DATA: String = "match_data"
    }
}