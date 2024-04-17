package im.ntub.myapplicationactivity

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import im.ntub.myapplicationactivity.databinding.ActivitySecBinding

class SecActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecBinding
    private var totalNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.showPopupButtonSecActivity.setOnClickListener {
            showPopup()

        }
    }

    private val dishNamesMap1 = mapOf(
        R.id.btnRice to "炒飯",
        R.id.btnnoodle to "炒麵",
        R.id.btnNoodles to "涼麵"
    )

    private val dishNamesMap2 = mapOf(
        R.id.btnBlackTea to "紅茶",
        R.id.btnMilkTea to "奶茶"
    )

    private val dishPriceMap1 = mapOf(
        R.id.btnRice to 15,
        R.id.btnnoodle to 20,
        R.id.btnNoodles to 10
    )

    private val dishPriceMap2 = mapOf(
        R.id.btnBlackTea to 10,
        R.id.btnMilkTea to 15
    )


    private fun getNameForId1(id: Int): String {
        return dishNamesMap1[id] ?: ""
    }

    private fun getNameForId2(id: Int): String {
        return dishNamesMap2[id] ?: ""
    }

    private fun getMDishTotal(id: Int): Int{
        return dishPriceMap1[id] ?: 0
    }

    private fun getDrinkTotal(id: Int): Int{
        return dishPriceMap2[id] ?: 0
    }


    private fun showPopup() {
        AlertDialog.Builder(this)
            .setMessage("是否確定？")
            .setPositiveButton("確定") { dialog, which ->
                val selectedDishId = binding.RGpMain.checkedRadioButtonId
                val selectedDishName = getNameForId1(selectedDishId)
                val selectedDishPrice = getMDishTotal(selectedDishId)
                val selectedDrinkId = binding.RGpDrink.checkedRadioButtonId
                val selectedDrinkName = getNameForId2(selectedDrinkId)
                val selectedDrinkPrice = getDrinkTotal(selectedDrinkId)
                val desertCookie = binding.chbCookie.isChecked
                val desertChip = binding.chbChip.isChecked

                var desert1 = ""
                var desert2 = ""

                totalNumber = selectedDishPrice + selectedDrinkPrice

                if(desertCookie){
                    desert1 = "曲奇"
                    totalNumber+= 60
                }
                if(desertChip){
                    desert2 = "洋芋片"
                    totalNumber+=80
                }

                val intent = Intent().apply {
                    putExtra("MainDish", selectedDishName)
                    putExtra("Drink", selectedDrinkName)
                    putExtra("desertCookie", desert1)
                    putExtra("desertChip", desert2)
                    putExtra("TotalPrice",totalNumber)
                }
                setResult(RESULT_OK, intent)
                finish()
            }
            .setNegativeButton("取消") { dialog, which ->
                dialog.cancel()
            }
            .show()
    }

}
