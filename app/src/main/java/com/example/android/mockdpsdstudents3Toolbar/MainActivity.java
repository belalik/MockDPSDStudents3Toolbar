package com.example.android.mockdpsdstudents3Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Thomas - MainActivity";

    ArrayList<DPSDStudent> fullDpsdsList = new ArrayList<>();

    ArrayList<DPSDStudent> filteredDpsdsList = new ArrayList<>();

    //ArrayList<DPSDStudent> theChosenList = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private DPSDPreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: runs");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initializeViews();
        initializePreferences();

        prefManager.deleteAllPreferences();

        //initializeData();



        adapter = new CustomListAdapter(this, R.layout.listview_row, fullDpsdsList);

        listview.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //return super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.show_shortlist_menu_item:
                Toast.makeText(this, "item 1 clisked", Toast.LENGTH_LONG).show();
                break;
            case R.id.settings_menu_item:
                Toast.makeText(this, "item 2 clisked", Toast.LENGTH_LONG).show();
                Intent settings = new Intent(this, SettingsActivity.class);

                startActivity(settings);
                break;
            case R.id.about_menu_item:
                Toast.makeText(this, "item 3 clisked", Toast.LENGTH_LONG).show();
                break;
            default:
                // should never reach this

        }
        return super.onOptionsItemSelected(item);

    }

    private void initializeViews() {
        listview = findViewById(R.id.listview);
    }

    private void initializePreferences() {
        prefManager = DPSDPreferencesManager.instance(this);

    }

    private void initializeData() {

        Log.i(TAG, "initializeData: runs !");

        //Gson gson = new Gson();
        String fullDpsdsListJson = prefManager.fetchValueString(DPSDPreferencesManager.FULL_DPSDS_LIST_KEY);
        //Log.i(TAG, "initializeData: is null: "+(fullDpsdsListJson==null));
        // full list is NULL, must be first time app is running!
        if (fullDpsdsListJson == null) {
            Log.i(TAG, "initializeData: fullDpsdsListJson is NULL, recreating hardcoded list");

            createStudentsHardcoded();

        }
        else {
            //SharedPreferences  prefs = PreferenceManager.getDefaultSharedPreferences(this);
            Gson gson = new Gson();
            //String json = prefs.getString("MyPhotos", null);
            Type type = new TypeToken<ArrayList<DPSDStudent>>() {}.getType();
            fullDpsdsList = gson.fromJson(fullDpsdsListJson, type);

            //allDpsds = gson.fromJson(fullDpsdsListJson, ArrayList<DPSDStudent>().class());
            //String json = mPrefs.getString("MyObject", "");
            //MyObject obj = gson.fromJson(json, MyObject.class);
            //createStudentsHardcoded();

            Log.i(TAG, "initializeData: list found and read from prefs");

            filterListAccordingToPrefs();
        }


        

    }


    // todo as this will probably be run only once - put it somewhere else, not on MainActivity..
    private void createStudentsHardcoded() {

        DPSDStudent stu1 = new DPSDStudent("Mitsos Papadimas 1",
                LocalDate.of(2017, 11, 6), 1,
                2016, true);
        fullDpsdsList.add(stu1);

        fullDpsdsList.add(new DPSDStudent("Maria Papadopoulou 2",
                LocalDate.of(2017, 12, 23), 2,
                2014, true));

        fullDpsdsList.add(new DPSDStudent("Makis Pakis 3",
                LocalDate.of(2017, 10, 15), 1,
                2017, false));

        DPSDStudent kogias = new DPSDStudent("Thomas Kogias 4", LocalDate.of(2017, 9, 6), 1,
                2019, false);

        kogias.setEmail("tkogias@aegean.gr");
        //kogias.setShortlisted(true);
        fullDpsdsList.add(kogias);

        fullDpsdsList.add(new DPSDStudent("Katerina Mitsou 5",
                LocalDate.of(2017, 3, 6), 2,
                2015, true));

        fullDpsdsList.add(new DPSDStudent("Petros Pappas 6",
                LocalDate.of(2017, 7, 16), 1,
                2017, false));

        fullDpsdsList.add(new DPSDStudent("Pikos Apikos 7",
                LocalDate.of(2021, 1, 11), 1,
                2017, false));

        fullDpsdsList.add(new DPSDStudent("Ntina Loizou 8",
                LocalDate.of(2020, 11, 18), 2,
                2016, false));

        fullDpsdsList.add(new DPSDStudent("Stathis Lappas 9",
                LocalDate.of(2016, 4, 20), 1,
                2016, false));

        fullDpsdsList.add(new DPSDStudent("Giannis Antetokounmpo 10",
                LocalDate.of(2020, 12, 6), 1,
                2016, true));

        fullDpsdsList.add(new DPSDStudent("Tracy Chapman 11",
                LocalDate.of(2017, 3, 6), 2,
                2015, true));

        fullDpsdsList.add(new DPSDStudent("Petros Pappas 12",
                LocalDate.of(2017, 7, 16), 1,
                2017, false));

        fullDpsdsList.add(new DPSDStudent("Roger Waters 13",
                LocalDate.of(2011, 1, 10), 1,
                2017, false));

        fullDpsdsList.add(new DPSDStudent("Ntina Loizou 14",
                LocalDate.of(2018, 5, 7), 2,
                2016, true));

        fullDpsdsList.add(new DPSDStudent("Gail Ann Dorsey 15",
                LocalDate.of(2011, 9, 27), 2,
                2016, false));

        fullDpsdsList.add(new DPSDStudent("Maria Titika 16",
                LocalDate.of(2017, 1, 7), 2,
                2016, true));

        fullDpsdsList.add(new DPSDStudent("Stathis Lappas 17",
                LocalDate.of(2016, 4, 20), 1,
                2016, false));

        fullDpsdsList.add(new DPSDStudent("Lila Pause 18",
                LocalDate.of(2020, 2, 7), 2,
                2016, false));


        Log.i(TAG, "createStudentsHardcoded: size here of list is: "+ fullDpsdsList.size());

        //todo refactor this, saving list code should not be repeated over and over..
        Gson gson = new Gson();
        String json = gson.toJson(fullDpsdsList);
        prefManager.storeValueString(DPSDPreferencesManager.FULL_DPSDS_LIST_KEY, json);
        Log.i(TAG, "createStudentsHardcoded: saved list in prefs, from now on, list will be saved. ");

    }

    /**
     * Method that filters already loaded list of DPSDStudent objects, according to user preferences.
     *
     * Simple (but inefficient method) is via for-loop that searches whole list, and filters accordingly.
     *
     * More advanced methods, using add-on libraries or Java Predicates. See more:
     * - https://stackoverflow.com/questions/48539690/filter-custom-objects-in-array-list-or-list
     * - https://stackoverflow.com/questions/122105/how-to-filter-a-java-collection-based-on-predicate
     */
    private void filterListAccordingToPrefs() {

        boolean showOnlyShortlistedDpsds = prefManager.fetchBoolean(DPSDPreferencesManager.SHOW_ONLY_SHORTLIST_SETTING_KEY);
        boolean showOnlyDissertationPendingDpsds = prefManager.fetchBoolean(DPSDPreferencesManager.SHOW_ONLY_DISSERTATION_SETTING_KEY);

        Log.i(TAG, "filterListAccordingToPrefs: onlyshortisted is: "+showOnlyShortlistedDpsds+", and onlyDissertation is: "+showOnlyDissertationPendingDpsds);
        if (showOnlyDissertationPendingDpsds && showOnlyShortlistedDpsds) {
            filteredDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isAllButDissertation).collect(Collectors.toList());
            filteredDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isShortlisted).collect(Collectors.toList());

            //fullDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isAllButDissertation).collect(Collectors.toList());
            //fullDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isShortlisted).collect(Collectors.toList());
        }
        else if (showOnlyDissertationPendingDpsds) {

            filteredDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isAllButDissertation).collect(Collectors.toList());

            //fullDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isAllButDissertation).collect(Collectors.toList());
        }
        else if (showOnlyShortlistedDpsds) {

            filteredDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isShortlisted).collect(Collectors.toList());
            //fullDpsdsList = (ArrayList<DPSDStudent>) fullDpsdsList.stream().filter(DPSDStudent::isShortlisted).collect(Collectors.toList());
        }

        else {
            //prefManager.deleteAllPreferences();
            //createStudentsHardcoded();


        }


        if (adapter != null) {
            // since shortlist AND dissertation settings are FALSE, re-show full-list
            if (showOnlyDissertationPendingDpsds || showOnlyShortlistedDpsds) {
                adapter.setNewData(filteredDpsdsList);
                adapter.notifyDataSetChanged();
            }
            else {
                adapter.setNewData(fullDpsdsList);
                adapter.notifyDataSetChanged();
            }
        }

        /*
        List<Article> articleList = new ArrayList<Article>();
        List<Article> filteredArticleList= articleList.stream().filter(article -> article.getDesArt().contains("test")).collect(Collectors.toList());

         */


    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume: running");
        initializeData();

        saveLists();
                
        super.onResume();
    }

    private void saveLists() {
        Gson gson = new Gson();
        String json = gson.toJson(fullDpsdsList);
        prefManager.storeValueString(DPSDPreferencesManager.FULL_DPSDS_LIST_KEY, json);
        Log.i(TAG, "saveLists: saving full list with size: "+fullDpsdsList.size());

        String json2 = gson.toJson(filteredDpsdsList);
        prefManager.storeValueString(DPSDPreferencesManager.FILTERED_DPSDS_LIST_KEY, json2);
        Log.i(TAG, "saveLists: saving filtered list with size: "+filteredDpsdsList.size());
    }


}