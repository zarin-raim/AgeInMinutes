package com.zarinraim.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.zarinraim.ageinminutes.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
                binding.tvSelectedDate.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                val selectedDateInSeconds = theDate!!.time / 1000

                val curDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val curDateInSeconds = curDate!!.time / 1000

                val difference = curDateInSeconds - selectedDateInSeconds
                binding.tvSelectedDateInSeconds.text = "$difference"
                binding.tvSelectedDateInMinutes.text = "${difference / 60}"
                binding.tvSelectedDateInDays.text = "${difference / (60 * 60 * 24)}"
            }
        ,year
        ,month
        ,day)
        dpd.datePicker.maxDate = Date().time - (86_400_000)
        dpd.show()
    }

}