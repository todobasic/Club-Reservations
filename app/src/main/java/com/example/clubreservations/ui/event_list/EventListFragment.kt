package com.example.clubreservations.ui.event_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clubreservations.databinding.FragmentEventListBinding
import com.example.clubreservations.model.Event
import com.example.clubreservations.presentation.event.EventListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class EventListFragment : Fragment(), OnEventEventListener {

    private lateinit var binding: FragmentEventListBinding
    private lateinit var adapter: EventAdapter
    private val viewModel: EventListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventListBinding.inflate(layoutInflater)
        binding.btnAddEvent.setOnClickListener { showCreateNewEventFragment() }
        setupRecyclewView()
        viewModel.events.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.setEvents(it)
            }
        }
        return binding.root


    }

    private fun setupRecyclewView() {
        binding.eventListRvEvents.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = EventAdapter()
        adapter.onEventSelectedListener = this
        binding.eventListRvEvents.adapter = adapter
    }

    companion object {
        val Tag = "EventsList"

        fun create(): Fragment {
            return EventListFragment()
        }
    }

    override fun onEventSelected(id: Long?) {
        val action =
            EventListFragmentDirections.actionEventListFragmentToEventDetailsFragment(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onEventLongPress(event: Event): Boolean {
        event.let { it ->
            viewModel.delete(it)
        }
        return true
    }

    private fun showCreateNewEventFragment() {
        val action = EventListFragmentDirections.actionEventListFragmentToNewEventFragment()
        findNavController().navigate(action)
    }

}