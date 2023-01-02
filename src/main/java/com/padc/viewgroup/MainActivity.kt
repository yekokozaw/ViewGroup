package com.padc.viewgroup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNextPage.setOnClickListener {
            val intent = Intent(this,RelativeActivity::class.java)
            startActivity(intent)
        }

        btnNextAppCompat.setOnClickListener {
            val intent = Intent(this,TestAppComActivity::class.java)
            startActivity(intent)
        }

//        btnHello.setOnClickListener {
//            val name:String = editName.text.toString()
//            Toast.makeText(this,"Hello $name",Toast.LENGTH_LONG).show()
//        }
        val greetingImages = intArrayOf(
            R.drawable.hello,
            R.drawable.hellotext,
            R.drawable.yellow_hello
        )

        btnHello.setOnClickListener {
            val randomSeed = Random()
            val randomIndex = randomSeed.nextInt(greetingImages.size)

            imageView.setImageResource(greetingImages[randomIndex])
        }

        btnCheckFruit.setOnClickListener {
            var selectedFruit = ""
            if (checkBoxApple.isChecked) {
                selectedFruit = getString(R.string.cb_apple_content)
            }
            if (checkBoxOrange.isChecked) {
                selectedFruit += getString(R.string.cb_orange_content)
            }
            if (checkBoxMango.isChecked) {
                selectedFruit += getString(R.string.cb_mango_content)
            }

            val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
            alertDialog.setTitle(getString(R.string.fruit_alert_dialog_title))
            alertDialog.setMessage("Your selected fruits, $selectedFruit")
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                DialogInterface.OnClickListener { dialog, which: Int -> dialog.dismiss() })
            alertDialog.show()
        }
            //radio Button
            radioButtonMale.setOnCheckedChangeListener { compoundButton, isChecked ->
                if(isChecked){
                    Toast.makeText(this,getString(R.string.radio_button_male),Toast.LENGTH_LONG).show()
                }
            }
            radioButtonFemale.setOnCheckedChangeListener { compoundButton, isChecked ->
                if(isChecked){
                    Toast.makeText(this,getString(R.string.radio_button_female),Toast.LENGTH_LONG).show()
                }
            }

            //Toggle Button
            tbGuestRoom.setOnCheckedChangeListener { compoundButton, b ->
                when{
                    b ->{
                        Toast.makeText(this,getString(R.string.tb_turn_on_content),Toast.LENGTH_LONG).show()
                    }
                    else ->{
                        Toast.makeText(this,getString(R.string.tb_turn_off_content),Toast.LENGTH_LONG).show()
                    }
                }
            }

            //Switch Button
            swtDoorLock.setOnCheckedChangeListener { compoundButton, b ->
                when{
                    b ->{
                        Toast.makeText(this,"Sucessfully Locked",Toast.LENGTH_LONG).show()
                    }
                    else ->{
                        Toast.makeText(this,"Disable Lock",Toast.LENGTH_LONG).show()
                    }
                }
            }

            //Seek Bar
        sbBrightness.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (seekBar != null) {
                    seekBar.progress = progress
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        rbHowmuchYouLove.setOnRatingBarChangeListener { ratingBar, rating, fromUser ->  }

        //autoCompleteTextView
        /** need to attach with an adapter **/
        val additionalDrinksAdapter:ArrayAdapter<Any?> = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.additional_drink)
        )
        actvAdditionDrinks.setAdapter(additionalDrinksAdapter)

        btnSelectDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val day = calendar[Calendar.DAY_OF_MONTH]
            val month = calendar[Calendar.MONTH]
            val year = calendar[Calendar.YEAR]

            val picker = DatePickerDialog(
                this,{view,year,monthOfYear,dayOfMonth ->
                    tvSelectedDate.text = ("$dayOfMonth/${monthOfYear+1}/$year")
                },1978,3,18
            )
            picker.show()
        }

        btnSelectTime.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar[Calendar.HOUR_OF_DAY]
            val minute = calendar[Calendar.MINUTE]

            val picker = TimePickerDialog(
                this,
                {tp,hour:Int,minute:Int ->
                    tvSelectedTime.text = ("$hour:$minute")
                },
                hour,
                minute,
                true
            )
            picker.show()
        }
    }
}