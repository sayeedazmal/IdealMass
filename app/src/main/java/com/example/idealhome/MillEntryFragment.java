package com.example.idealhome;

import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.idealhome.Pojo.MillInfo;
import com.example.idealhome.Pojo.User;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class MillEntryFragment extends Fragment{
    private DatePicker datePicker;
    private TextView daytxt,monthtxt,yeartxt;
    private int year, month, day;
    EditText date;
    Button button1;
    Context context;
//    MillEntryListener millEntryListener;
    private ArrayList<String> arrayList,monthArraylist,yearlist;
    private Spinner spmonth,spday,spyear;


    private Calendar calendar;
    Spinner year_spinner, month_spinner, day_spinner,countSpinner;
    TextView mytext;
    EditText nameEdit;
    Button btnsave;




    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        this.context = context;
//        millEntryListener = (MillEntryListener) context;
    }

    public MillEntryFragment() {

    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vh = inflater.inflate(R.layout.fragment_millentry,container,false);
//         mytext = (TextView)vh.findViewById(R.id.selectedDate);

        List<String>member=new ArrayList<>();
        calendar = Calendar.getInstance();

        year_spinner    = (Spinner)vh.findViewById(R.id.year_spinner);
        month_spinner   = (Spinner)vh.findViewById(R.id.month_spinner);
        day_spinner     = (Spinner)vh.findViewById(R.id.day_spinner);

        nameEdit = vh.findViewById(R.id.nameEdit);
        countSpinner = vh.findViewById(R.id.spinnerQuantity);
        btnsave = vh.findViewById(R.id.btnsave);

        List<String> countMill = new ArrayList<>();
                countMill.add("Select");
                countMill.add("1");
                countMill.add("2");
                countMill.add("3");
                countMill.add("4");
                countMill.add("5");
                countMill.add("6");
                countMill.add("7");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, countMill);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countSpinner.setAdapter(spinnerArrayAdapter);

        String users = new User().getUsername();
        member.add(users);


        year_spinner.setOnItemSelectedListener(fixDays);
        month_spinner.setOnItemSelectedListener(fixDays);

        populateYears(-10, 30);

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                MillInfo millInfo = new MillInfo(year_spinner,month_spinner,day_spinner,countSpinner,member);






            }
        });


        return vh;
    }

    AdapterView.OnItemSelectedListener fixDays = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            setDays();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            //Another interface callback
        }
    };

    public void setDays() {

        int year = Integer.parseInt(year_spinner.getSelectedItem().toString());
        String month = month_spinner.getSelectedItem().toString();
        List<String> months = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.months)));

        Calendar mycal = new GregorianCalendar(year, months.indexOf(month), 1);



        // Get the number of days in that month
        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);


        String[] days_array = new String[daysInMonth];

        for (int k = 0; k < daysInMonth; k++)
            days_array[k] = "" + (k + 1);

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, days_array);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        day_spinner.setAdapter(spinnerArrayAdapter);
    }

    public void populateYears(int minAge, int maxAge) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        String[] years_array = new String[maxAge - minAge];

        for (int i = 0; i < maxAge - minAge; i++ ){
                if(i==0){
                    years_array[i]=String.valueOf(currentYear);
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_dropdown_item, years_array);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    year_spinner.setAdapter(spinnerArrayAdapter);
                }else{
                    years_array[i] = "" + (currentYear - minAge - i);
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(getActivity(),
                            android.R.layout.simple_spinner_dropdown_item, years_array);
                    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    year_spinner.setAdapter(spinnerArrayAdapter);

                }

            }



        }


//    public interface MillEntryListener{
//       void millEntrymethod();
//   }
}
