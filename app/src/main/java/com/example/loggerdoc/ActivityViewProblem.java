package com.example.loggerdoc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityViewProblem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problem);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        Patient patient = (Patient) intent.getSerializableExtra("Patient");
        int position = (int) intent.getSerializableExtra("Position");
        Problem problem = patient.getProblems().getProblemArrayList().get(position);

        TextView problemTitleView = (TextView) findViewById(R.id.ProblemTitleView);
        problemTitleView.setText(problem.getTitle());

        TextView problemDateView = (TextView) findViewById(R.id.dateView);
        //problemDateView.setText(problem.getDate());

        TextView problemDescriptionView = (TextView) findViewById(R.id.ProblemDescriptionText);
        problemDescriptionView.setText(problem.getDescription());

        Button editProblemButton = (Button) findViewById(R.id.editButton);
        Button deleteProblemButton = (Button) findViewById(R.id.deleteButton);
        Button addRecordButton = (Button) findViewById(R.id.addRecordButton);
    }

    public void goEditProblem (View v){
        Intent intent = new Intent(this, ActivityEditProblem.class);
        startActivity(intent);
    }

    public void goDeleteProblem (View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you would like to delete this problem?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: Delete Problem from the User's problemList
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void goAddRecord (View v){
        Intent intent = new Intent(this, ActivityEditProblem.class);
        startActivity(intent);
    }
}
