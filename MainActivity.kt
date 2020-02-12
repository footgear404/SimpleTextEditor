package com.example.company.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var savedText:String = ""

    fun wordCounter(n:String):Int{
        val wordList = n.split("[^a-zA-Zа-яА-я0-9_-]+".toRegex())
        return wordList.size - 1
    }

    fun saveOrNot(n:String):String{
        if (n == savedText){
            return "All changes saved"
        }
        return "Unsaved changes"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        save_button.setOnClickListener{
            savedText = editText.text.toString()
            unsaved_changes_view.text = saveOrNot(editText.text.toString())
        }

        clear_button.setOnClickListener{
            editText.post{editText.setText("")}
            stats_view.text = "0"
        }

        load_button.setOnClickListener {
            editText.post{editText.setText(savedText)}
        }

        editText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                unsaved_changes_view.text = saveOrNot(editText.text.toString())
                stats_view.text = wordCounter(editText.text.toString()).toString()
            }
        })
    }
}
