package com.example.spinner

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import com.example.spinner.R
import com.example.spinner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var presence: Array<String>
    private lateinit var selectedDate :String
    private lateinit var selectedTime :String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //buat binding
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot())

        presence = getResources().getStringArray(R.array.presence);

        with(binding) {
            //membuat adapter
            val adapterPresence = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                presence
            )
            adapterPresence.setDropDownViewResource(com.google.android.material.R.layout.support_simple_spinner_dropdown_item)
            spinnerPresence.adapter = adapterPresence

            spinnerPresence.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {

                        val presensi = presence[position]
                        Toast.makeText(this@MainActivity, presensi, Toast.LENGTH_SHORT).show()

                        val selectedItem = spinnerPresence.selectedItem.toString()
                        if (selectedItem == "Sakit") {
                            // Tampilkan EditText jika item tertentu dipilih
                            editTxtKet.visibility = View.VISIBLE
                        } else if (selectedItem == "Terlambat") {
                            editTxtKet.visibility = View.VISIBLE
                        } else if (selectedItem == "Izin") {
                            editTxtKet.visibility = View.VISIBLE
                        } else {
                            // Sembunyikan EditText jika item selainnya dipilih
                            editTxtKet.visibility = View.GONE
                        }
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {
                        // Metode ini akan dipanggil jika tidak ada item yang dipilih
                    }
                }

            datePicker.init(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth
            ) {_, year, monthOfYear, dayOfMonth ->
                val selectedDate = "$dayOfMonth/${monthOfYear +1}/$year"
                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }

            timePicker.setOnTimeChangedListener{view, hourOfDay, minute ->
                var selectedTime = String.format("%02d:%02d", hourOfDay, minute)

                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }

            btnSubmit.setOnClickListener {
                val selectedDateTime = "$selectedDate jam $selectedTime"
                Toast.makeText(
                    this@MainActivity,
                    "Presensi berhasil pada $selectedDateTime",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
