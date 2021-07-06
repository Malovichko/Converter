package com.example.networkexample.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
    private lateinit var startValute: Valute
    private lateinit var endValute: Valute


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
            getTextInput = editCurrencyStart.text.toString().toDouble()
            convert = Converter(getTextInput, startValute, endValute)

            editCurrencyEnd.setText(convert.convertFromStartValute().toString())
        }

        editCurrencyEnd.setOnClickListener{
            getTextOutput = editCurrencyEnd.text.toString().toDouble()
            convert = Converter(getTextOutput, startValute, endValute)

            editCurrencyStart.setText(convert.convertFromStartValute().toString())
        }

    }
}