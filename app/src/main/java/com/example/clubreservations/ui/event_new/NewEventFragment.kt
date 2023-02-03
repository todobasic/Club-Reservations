package com.example.clubreservations.ui.event_new

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.clubreservations.databinding.FragmentNewEventBinding
import com.example.clubreservations.presentation.event.EventNewViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewEventFragment: Fragment() {

    private lateinit var binding: FragmentNewEventBinding
    private val viewModel: EventNewViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewEventBinding.inflate(layoutInflater)
        binding.btnSaveEvent.setOnClickListener { saveEvent() }
        return binding.root
    }

    private fun saveEvent() {
        val title = binding.etEventTitleInput.text.toString()

        viewModel.save(title)

        Toast.makeText(context, "Saving event", Toast.LENGTH_SHORT).show()
        val action = NewEventFragmentDirections.actionNewEventFragmentToEventListFragment()
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "NewEvent"

        fun create(): Fragment{
            return NewEventFragment()
        }
    }

}