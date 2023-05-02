package uz.sr.materialdialogs

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import uz.sr.materialdialogs.databinding.ActivityMainBinding
import uz.sr.mylibrary.classes.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.location.setOnClickListener {
            basicDialog()
        }

        binding.alertData.setOnClickListener {
            dateDialog()
        }

        binding.customArgb.setOnClickListener {
            colorDialog()
        }

        binding.network.setOnClickListener {
            openCheckBoxDialog()
        }

        binding.googleWifi.setOnClickListener {
            wifiDialog()
        }

    }


    private fun wifiDialog() {
        val dialog = WifiDialog()
        dialog.show(supportFragmentManager, "wifi_dialog")
        dialog.createDialog("Google wifi", "Wife Name", "password", "Wpa2")
        dialog.setonButtonsCLickedListener(object : WifiDialog.WiFiDialogListeners {
            override fun okButtonClicked(password: String) {
                Toast.makeText(this@MainActivity, "$password", Toast.LENGTH_SHORT).show()
            }
            override fun cancelButtonClicked() {}
        })


    }

    private fun colorDialog() {
        val dialog = ColorDialog()
        dialog.show(supportFragmentManager, "color_dialog")
        dialog.setOnColorSelectedListener(object : ColorDialog.OnColorSelectListener {
            override fun OnColorSelected(color: Int) {
                binding.customArgb.setBackgroundColor(color)
            }

        })
    }

    private fun dateDialog() {
        val dialog = DateAndTimeDialog()
        dialog.show(supportFragmentManager, "date_dialog")
        dialog.setOnOkButtonClicked(object : DateAndTimeDialog.OnButtonClicked {
            @RequiresApi(Build.VERSION_CODES.M)
            override fun OnOkButtonClicked(datePicker: DatePicker, timePicker: TimePicker) {
                val date = "${datePicker.dayOfMonth}.${datePicker.month}.${datePicker.year}  " +
                        "${timePicker.hour}:${timePicker.minute}"
                Toast.makeText(this@MainActivity, date, Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun basicDialog() {
        val basicDialog = BasicDialog(this)
        basicDialog.createDialog(
            title = "Lorem ipsum?",
            mainTxt = "Lorem ipsum dolor sit amet, consectetur adipiscing elit," +
                    "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
            okBtnTxt = "Agree",
            cancelBtnTxt = "Disagree"
        )
        basicDialog.show(supportFragmentManager, "basic_dialog")
        basicDialog.setOnButtonClickListener(object : BasicDialog.BasicDialogListeners {
            override fun okButtonClicked() {
                Toast.makeText(this@MainActivity, "DISAGREE", Toast.LENGTH_SHORT).show()

            }

            override fun cancelButtonClicked() {
                Toast.makeText(this@MainActivity, "AGREE", Toast.LENGTH_SHORT).show()

            }

        })
    }

    private fun openCheckBoxDialog() {
        val list = arrayListOf("Twitter", "Google", "Instagram", "Facebook")

        val checkBoxDialog = CheckBoxDialog(list, "Social medias")
        checkBoxDialog.setOnChooseClickListener(object : CheckBoxDialog.OnChooseClickListener {
            override fun chooseClick(chosenList: List<String>) {
                val stringBuilder = StringBuilder()
                for (i in list) {
                    stringBuilder.append("$i ")
                }
                Toast.makeText(this@MainActivity, stringBuilder.toString(), Toast.LENGTH_SHORT)
                    .show()
            }

        })
        checkBoxDialog.show(supportFragmentManager, "checkbox_dialog")
    }


}