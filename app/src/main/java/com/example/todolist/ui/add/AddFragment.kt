package com.example.todolist.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.todolist.R
import com.example.todolist.database.TaskEntry
import com.example.todolist.databinding.FragmentAddBinding

class AddFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentAddBinding.inflate(inflater)
        val myAdapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            resources.getStringArray(R.array.priorities)
        )

        binding.apply {
            spinner.adapter = myAdapter
            btnSave.setOnClickListener {
                if (TextUtils.isEmpty(txtEditText.text.toString())){
                    Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT)
                    return@setOnClickListener
                }

                val title_str = btnSave.text.toString()
                val priority =  spinner.selectedItemPosition
                // task entry
                val taskEntry = TaskEntry(
                    0,
                    title_str,
                    priority,
                    System.currentTimeMillis()
                )
            }
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }
}