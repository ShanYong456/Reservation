package sg.edu.rp.c346.id18015938.reservation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

  EditText editName;
  EditText editMobile;
  EditText editGroup;
  CheckBox checkSmoke;
  TimePicker pickTime;
  DatePicker pickDate;
  Button btnReserve;
  Button btnReset;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      editName = findViewById(R.id.editTextName);
      editMobile = findViewById(R.id.editTextMobile);
      editGroup = findViewById(R.id.editTextGroup);
      checkSmoke = findViewById(R.id.checkBoxSmoke);
      pickTime = findViewById(R.id.timePicker);
      pickDate = findViewById(R.id.datePicker);
      btnReserve = findViewById(R.id.buttonReserve);
      btnReset = findViewById(R.id.buttonReset);

      checkSmoke.setChecked(false);
      pickTime.setCurrentHour(19);
      pickTime.setCurrentMinute(30);
      pickDate.updateDate(2020,5,1);

      btnReserve.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v){



             int month = pickDate.getMonth()+1;
             int day = pickDate.getDayOfMonth();
             int year = pickDate.getYear();
             int hour = pickTime.getCurrentHour();
             int minutes = pickTime.getCurrentMinute();
             String name = editName.getText().toString();
             String mobile = editMobile.getText().toString();
             String group = editGroup.getText().toString();
             String smoke;
             String message;

              Calendar today = Calendar.getInstance();
              Calendar reserve_date = Calendar.getInstance();
              reserve_date.set(year, month, day, hour, minutes);
              if(today.after(reserve_date)==false){
                  if(name.length()==0 && mobile.length()==0 && group.length()==0){
                      message = "Please fill up all the fields";
                  }
                  else if(name.length()==0 && mobile.length()!=0 && group.length()!=0){
                      message = "Please enter your name";
                  }
                  else if(name.length()!=0 && mobile.length()==0 && group.length()!=0){
                      message = "Please enter your mobile number";
                  }
                  else if(name.length()!=0 && mobile.length()!=0 && group.length()==0){
                      message = "Please enter your group size";
                  }
                  else if(name.length()!=0 && mobile.length()==0 && group.length()==0){
                      message = "Please enter your mobile number and group size";
                  }
                  else if(name.length()==0 && mobile.length()!=0 && group.length()==0){
                      message = "Please enter your name and group size";
                  }
                  else if(name.length()==0 && mobile.length()==0 && group.length()!=0){
                      message = "Please enter your name and mobile number";
                  }
                  else{

                      if(checkSmoke.isChecked()){
                          smoke = "smoking area";
                      }else{
                          smoke = "non-smoking area";
                      }

                      message = "Reservation\n" +
                              "Name: "+name+"\n"+
                              "Mobile Number: "+mobile+"\n"+
                              "Group Size: "+group+"\n"+
                              "Table in "+smoke+"\n"+
                              "Date of Reservation: "+day+"/"+month+"/"+year+"\n"+
                              "Time of Reservation: "+hour+":"+minutes;


                  }

                  Toast.makeText(MainActivity.this, message , Toast.LENGTH_LONG).show();


              }else{
                  Toast.makeText(MainActivity.this, "Please reserve the day after today" , Toast.LENGTH_LONG).show();
              }



          }
      });

      btnReset.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v){

              editName.setText("");
              editMobile.setText("");
              editGroup.setText("");
              checkSmoke.setChecked(false);
              pickTime.setCurrentHour(19);
              pickTime.setCurrentMinute(30);
              pickDate.updateDate(2020,5,1);

          }
      });

      pickTime.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
          @Override
          public void onTimeChanged(TimePicker t, int hourOfDay, int minutes){
              // Add you code here to limit the time to 8AM and 8PM

              if(hourOfDay<8){
                  Toast.makeText(MainActivity.this, "The restaurant is not open till 8AM" , Toast.LENGTH_SHORT).show();
                  pickTime.setCurrentHour(8);
              }
              else if(hourOfDay>=21){
                  Toast.makeText(MainActivity.this, "The restaurant will be closed at 9PM" , Toast.LENGTH_SHORT).show();
                  pickTime.setCurrentHour(20);
              }
              else if(hourOfDay==20 && minutes>30){
                  Toast.makeText(MainActivity.this, "No more reservation is allowed 30min before closure of restaurant" , Toast.LENGTH_SHORT).show();
                  pickTime.setCurrentMinute(25);
              }


          }
      });






  }
}
