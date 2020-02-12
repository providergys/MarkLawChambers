package com.demo.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.demo.adapter.RecentActivityAdapter;
import com.demo.adapter.TeamAdapter;
import com.demo.marklaw.R;
import com.demo.marklaw.databinding.ActivityOurTeamBinding;
import com.demo.model.TeamModel;

public class OurTeamActivity extends AppCompatActivity {
    ActivityOurTeamBinding binding;
    TeamAdapter teamAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_our_team);
        TeamModel[] mList=new TeamModel[]{new TeamModel("Aaron Mark Pius","Aaron Mark is the managing partner and the diving force in the Mark Law Chambers",R.drawable.aaron),
                new TeamModel("Sia Yi Ting","Sia Yi Ting is the managing partner and the diving force in the Mark Law Chambers ",R.drawable.sia),
                new TeamModel("Ow Ji jim","Ow Ji jim is the managing partner and the diving force in the Mark Law Chambers ",R.drawable.ow),
                new TeamModel("Elaine Tai Yee Lian","Elaine Tai Yee Lian is the managing partner and the diving force in the Mark Law Chambers ",R.drawable.elaine),
                new TeamModel("Nai Mei kei","Nai Mei kei is the managing partner and the diving force in the Mark Law Chambers ",R.drawable.nai)

        };
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        teamAdapter = new TeamAdapter(getApplicationContext(), mList);
        binding.teamRecycler.setLayoutManager(manager);
        binding.teamRecycler.setAdapter(teamAdapter);
    }
    public void back(View view){
        finish();
    }
}
