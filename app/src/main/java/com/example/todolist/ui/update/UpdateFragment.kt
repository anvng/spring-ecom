package com.example.todolist.ui.update

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todolist.R
import com.example.todolist.database.TaskEntry
import com.example.todolist.databinding.FragmentUpdateBinding
import com.example.todolist.viewmodel.TaskViewModel

class UpdateFragment : Fragment() {
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentUpdateBinding.inflate(inflater)
        val args = UpdateFragmentArgs.fromBundle(requireArguments())

        binding.apply {
            txtUpdateText.setText(args.taskEntry.titles)
            updateSpinner.setSelection(args.taskEntry.priority.toInt())
            txtUpdateNote.setText(args.taskEntry.notes)

            btnUpdate.setOnClickListener {
                if (TextUtils.isEmpty(txtUpdateText.text)) {
                    Toast.makeText(requireContext(), "It is empty!", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                val taskStr = txtUpdateText.text.toString()
                val priorityStr = updateSpinner.selectedItemPosition
                val noteStr = txtUpdateNote.text.toString()

                val taskEntry = TaskEntry(
                    args.taskEntry.id,
                    taskStr,
                    priorityStr.toString(),
                    noteStr,
                    args.taskEntry.timestamps
                )
                viewModel.update(taskEntry)
                Toast.makeText(requireContext(), "Updated!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_updateFragment_to_taskFragment)
            }
        }
        // Inflate the layout for this fragment
        return binding.root
    }
}
