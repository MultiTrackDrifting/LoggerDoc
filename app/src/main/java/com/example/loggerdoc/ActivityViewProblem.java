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

    private Patient patient;
    private Problem problem;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_problem);
    }

    @Override
    protected void onResume(){
        super.onResume();

        Intent intent = getIntent();
        patient = (Patient) intent.getSerializableExtra("Patient");
        position = (int) intent.getSerializableExtra("Position");
        problem = patient.getProblems().getProblemArrayList().get(position);

        TextView problemTitleView = (TextView) findViewById(R.id.ProblemTitleView);
        problemTitleView.setText(problem.getTitle());

        TextView problemDateView = (TextView) findViewById(R.id.dateProblemView);
        problemDateView.setText(problem.getTimestamp().toString());

        TextView problemDescriptionView = (TextView) findViewById(R.id.descriptionProblemView);
        problemDescriptionView.setText(problem.getDescription());
    }

    public void goEditProblem (View v){
        Intent intent = new Intent(this, ActivityEditProblem.class);
        intent.putExtra("Patient", patient);
        intent.putExtra("Position", position);
        startActivity(intent);
    }

    public void goDeleteProblem (final View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure you would like to delete this problem?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                patient.getProblems().remove(problem);
                goBrowseProblems(v);
                dialog.dismiss();
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

    public void goBrowseProblems(View v){
        Intent intent = new Intent(this, ActivityBrowseProblems.class);
        intent.putExtra("Patient", patient);
        startActivity(intent);
    }

    public void goAddRecord (View v){
        Intent intent = new Intent(this, ActivityAddRecord.class);
        startActivity(intent);
    }
}
