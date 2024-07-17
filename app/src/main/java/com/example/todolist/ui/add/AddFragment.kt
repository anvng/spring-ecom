package com.example.todolist.ui.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.TaskEntry
import com.example.todolist.databinding.FragmentAddBinding
import com.example.todolist.viewmodel.TaskViewModel

class AddFragment : Fragment() {

    private val viewModel: TaskViewModel by viewModels()

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
                val titleStr = txtEditText.text.toString()
                val noteStr = txtNote.text.toString()

                if (TextUtils.isEmpty(titleStr) || TextUtils.isEmpty(noteStr)) {
                    Toast.makeText(
                        requireContext(),
                        "Please fill out all fields",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                val priorities = spinner.selectedItemPosition
                // task entry
                val taskEntry = TaskEntry(
                    0,
                    titleStr,
                    priorities.toString(),
                    noteStr,
                    System.currentTimeMillis()
                )

                viewModel.insert(taskEntry)
                Toast.makeText(requireContext(), "Success added Task", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_taskFragment)

                // back button
                btnBack.setOnClickListener {
                    findNavController().navigate(R.id.action_addFragment_to_taskFragment)
                }
            }

            // Inflate the layout for this fragment
            return binding.root
        }
    }
}