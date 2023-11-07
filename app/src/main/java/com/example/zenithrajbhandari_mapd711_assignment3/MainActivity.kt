package com.example.zenithrajbhandari_mapd711_assignment3

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val intent = Intent(this, MapActivity::class.java)

        val actionBar = supportActionBar
        val colorDrawable = ColorDrawable(Color.parseColor("#D0A2F7"))

        actionBar?.setBackgroundDrawable(colorDrawable);
        actionBar?.setTitle("Find Pizza Stores!");

        val spinner: Spinner = findViewById(R.id.citySpinner)
        ArrayAdapter.createFromResource(
            this,
            R.array.cities_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears.
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner.
            spinner.adapter = adapter
        }
        var selectedCity = spinner.selectedItem.toString()
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                selectedCity=spinner.selectedItem.toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                selectedCity="Toronto Downtown"
            }
        }
        findViewById<Button>(R.id.viewStoreBtn).setOnClickListener(){
            intent.putExtra("selectedCity",selectedCity)
            startActivity(intent)
        }

    }
}