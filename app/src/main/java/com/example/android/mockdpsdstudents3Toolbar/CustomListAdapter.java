package com.example.android.mockdpsdstudents3Toolbar;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListAdapter extends ArrayAdapter {

    private static final String TAG = "Thomas - CustomListAdapter";

    private final Activity context;

    private ArrayList<DPSDStudent> allDPSDS;

    private DPSDPreferencesManager prefManager;


    public CustomListAdapter(@NonNull Activity context, int resource, @NonNull ArrayList allDPSDS) {
        super(context, resource, allDPSDS);

        this.allDPSDS = allDPSDS;
        this.context = context;

        initializePreferences();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);

        TextView txtName = rowView.findViewById(R.id.txt_name);
        TextView txtEmail = rowView.findViewById(R.id.txt_email);
        ImageView imgStudentIcon = rowView.findViewById(R.id.img_student);
        ImageButton imgButtonAddRemove = rowView.findViewById(R.id.img_button_add_remove);
        ImageButton imgButtonDissertation = rowView.findViewById(R.id.img_button_dissertation);
        //ImageView imgShortlistedPositive = rowView.findViewById(R.id.img_shortlisted_positive);

        txtName.setText(allDPSDS.get(position).getName());
        txtEmail.setText(allDPSDS.get(position).getEmail());
        imgStudentIcon.setImageResource(allDPSDS.get(position).chooseIcon());

        if (allDPSDS.get(position).isAllButDissertation()) {
            imgButtonDissertation.setImageResource(R.drawable.ic_dissertation);
        }
        if (allDPSDS.get(position).isShortlisted()) {
            //Log.d(TAG, "getView: disabling button for name="+allDPSDS.get(position).getName()+", ID="+allDPSDS.get(position).getID());
            imgButtonAddRemove.setImageResource(R.drawable.ic_remove);
            //imgButtonAddRemove.setAlpha(.5f);
            //imgButtonAddRemove.setClickable(false);
            //imgButtonAddRemove.setEnabled(false);
        }
        else {
            imgButtonAddRemove.setImageResource(R.drawable.ic_add);
            //imgButtonAddRemove.setBackgroundResource(ic_add);
        }

        imgButtonAddRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (allDPSDS.get(position).isShortlisted()) {
                    Toast.makeText(context, "removing item at position: "+position, Toast.LENGTH_SHORT).show();
                    //imgButtonAddRemove.setImageDrawable(null);
                    //imgButtonAddRemove.refreshDrawableState();
                    //imgButtonAddRemove.setVisibility(View.INVISIBLE);
                    //imgButtonAddRemove.setBackgroundResource( ic_remove );
                    allDPSDS.get(position).setShortlisted(false);
                    imgButtonAddRemove.setImageResource(R.drawable.ic_remove);
                    //imgButtonAddRemove.setImageDrawable(context.getResources().getDrawable(ic_remove, context.getTheme()));



                }
                else {
                    Toast.makeText(context, "shortlisting item at position: "+position, Toast.LENGTH_SHORT).show();

                    //addToShortlist(position);
                    // viewToUse.setImageResource(android.R.color.transparent);
                    // imgView.setImageDrawable(null);
                    //imgButtonAddRemove.setImageDrawable(null);
                    imgButtonAddRemove.setImageResource(R.drawable.ic_add);
                    //imgButtonAddRemove.setImageDrawable(context.getResources().getDrawable(ic_add, context.getTheme()));
                    allDPSDS.get(position).setShortlisted(true);

                }
                notifyDataSetChanged();

                //todo refactor this, saving list code should not be repeated over and over..
                // todo PROBLEM IS HERE - should not overwrite (complete) list. Rather, it should change shortlist status on given object and re-save full list.

                /*Gson gson = new Gson();
                String json = gson.toJson(allDPSDS);
                prefManager.storeValueString(DPSDPreferencesManager.FULL_DPSDS_LIST_KEY, json);
                Log.i(TAG, "onClick: inside list, saving list to prefs, with size: "+allDPSDS.size());*/

                //prefsEditor.putString("MyObject", json);
                //prefsEditor.commit();
            }
        });

        return rowView;
    }

    public void setNewData (ArrayList newData) {
        this.allDPSDS.clear();
        this.allDPSDS.addAll(newData);

    }

    private void initializePreferences() {
        prefManager = DPSDPreferencesManager.instance();

    }
}
