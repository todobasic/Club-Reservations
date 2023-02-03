package com.example.clubreservations.ui.table_new

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clubreservations.R
import com.example.clubreservations.databinding.FragmentNewTableBinding
import com.example.clubreservations.presentation.table.TableNewViewModel
import com.example.clubreservations.utils.getDate
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTableFragment: Fragment() {

    private lateinit var binding: FragmentNewTableBinding
    private val viewModel: TableNewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTableBinding.inflate(layoutInflater)
        binding.btnAddTable.setOnClickListener { saveReservation() }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource( requireContext(), R.array.numbers, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        return binding.root
    }

    private fun saveReservation() {
        val ordNumber = binding.spinner.selectedItem.toString()
        val title = binding.etTableUsernameInput.text.toString()
        val content = binding.etTableDrinkInput.text.toString()
        val dateAdded = binding.dpDateAddedInput.getDate()

        viewModel.save(ordNumber, title, content, dateAdded)

        Toast.makeText(context,"Reserving...", Toast.LENGTH_SHORT).show()
        val action = NewTableFragmentDirections.actionNewTableFragmentToEventDetailsFragment(1)
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "NewTable"

        fun create(): Fragment {
            return NewTableFragment()
        }
    }


}