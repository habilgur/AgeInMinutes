package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDate.setOnClickListener {
            clickDatePicker()
        }


    }

    private fun clickDatePicker() {
        val now = Calendar.getInstance()

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                var selectedDate =
                    "$dayOfMonth/${if (month < 10) "0${month + 1}" else month + 1}/$year"
                textBirthDay.text = selectedDate
                Toast.makeText(this, "You selected a date!", Toast.LENGTH_SHORT).show()

                var userBirthDate = SimpleDateFormat("dd/MM/yyy").parse(selectedDate)
                var userBirthDateInMin = userBirthDate!!.time
                var currentDate = Date().time

                textInMinutes.text =
                    ((currentDate - userBirthDateInMin) / (1000 * 60 * 60)).toString()
                textInDays.text =
                    ((currentDate - userBirthDateInMin) / (1000 * 60 * 60 * 24)).toString()

                var currentYear = now.get(Calendar.YEAR)
                if (year < currentYear)
                    textInYears.text = (now.get(Calendar.YEAR) - year - 1).toString()
                else
                    textInYears.text = "0"

            },
            now.get(Calendar.YEAR),
            now.get(Calendar.MONTH),
            now.get(Calendar.DAY_OF_MONTH)
        )

        dpd.datePicker.setMaxDate(Date().time - 8640000)
        dpd.show()

    }
}
