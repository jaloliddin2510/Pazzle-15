import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.SeekBar
import android.widget.TextView
import com.example.a15.R

class CustomTimeDialog(
    context: Context,
    private val onOkClickListener: (Int) -> Unit
) : Dialog(context) {


    private var minutesTextView: TextView
    private var seekBar: SeekBar
    private var cancelButton: TextView
    private var okButton: TextView

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_time, null)
        setContentView(view)

        minutesTextView = findViewById(R.id.minutes_tv)
        seekBar = findViewById(R.id.seekbar)
        cancelButton = findViewById(R.id.cancel)
        okButton = findViewById(R.id.Ok)

        seekBar.progress = 0
        minutesTextView.text = "0"

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                minutesTextView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        cancelButton.setOnClickListener {
            dismiss()
        }
        okButton.setOnClickListener {
            onOkClickListener(seekBar.progress)
            dismiss()
        }
    }
}
