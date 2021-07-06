package com.example.networkexample.presentation

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.networkexample.R
import com.example.networkexample.domain.converter.Converter
import com.example.networkexample.domain.model.SharedViewModel
import com.example.networkexample.domain.model.Valute
import com.example.networkexample.presentation.valutelist.ValuteListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var charCodeStart: TextView
    private lateinit var charCodeEnd: TextView
    private lateinit var editCurrencyStart: EditText
    private lateinit var editCurrencyEnd: EditText
    private lateinit var buttonChangeCurrencyStart: Button
    private lateinit var buttonChangeCurrencyEnd: Button
    private var startValute: Valute? = null
    private var endValute: Valute? = null


    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initListener()
    }


    private fun initViews() {
        charCodeStart = findViewById(R.id.text_view_original_char_code)
        charCodeEnd = findViewById(R.id.text_view_output_char_code)
        editCurrencyStart = findViewById(R.id.source_edit_original_currency)
        editCurrencyEnd = findViewById(R.id.source_edit_output_currency)
        buttonChangeCurrencyStart = findViewById(R.id.button_change_original_currency)
        buttonChangeCurrencyEnd = findViewById(R.id.button_change_output_currency)
        sharedViewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
    }

    private fun initListener() {
        var getTextInput: Double
        var getTextOutput: Double
        var convert: Converter

        buttonChangeCurrencyStart.setOnClickListener {
            ValuteListFragment.newInstance(getString(R.string.start))
                .show(supportFragmentManager, ValuteListFragment.KEY_CHOOSE_RECEIVER)
            sharedViewModel.startCharCode.observe(this, Observer {
                charCodeStart.text = it.charCode
                startValute = it
            })
        }

        buttonChangeCurrencyEnd.setOnClickListener {
            ValuteListFragment.newInstance(getString(R.string.end))
                .show(supportFragmentManager, ValuteListFragment.KEY_CHOOSE_RECEIVER)
            sharedViewModel.endCharCode.observe(this, Observer {
                charCodeEnd.text = it.charCode
                endValute = it
            })
        }
        editCurrencyStart.setOnClickListener {
            try {
                getTextInput = editCurrencyStart.text.toString().toDouble()
                if (endValute != null && startValute != null) {
                    convert = Converter(getTextInput, startValute!!, endValute!!)
                    editCurrencyEnd.setText(convert.convertFromStartValute().toString())
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.text_message_choose_valute),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.text_message_input_number),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        editCurrencyEnd.setOnClickListener {
            try {
                getTextOutput = editCurrencyEnd.text.toString().toDouble()
                if (endValute != null && startValute != null) {
                    convert = Converter(getTextOutput, startValute!!, endValute!!)
                    editCurrencyStart.setText(convert.convertFromStartValute().toString())
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.text_message_choose_valute),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.text_message_input_number),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }


    }
}