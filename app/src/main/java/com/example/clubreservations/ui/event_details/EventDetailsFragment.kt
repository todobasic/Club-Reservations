package com.example.clubreservations.ui.event_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clubreservations.data.repository.EventRepository
import com.example.clubreservations.databinding.FragmentEventDetailsBinding
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Reservation
import com.example.clubreservations.presentation.event.EventDetailsViewModel
import com.example.clubreservations.ui.table_list.OnTableEventListener
import com.example.clubreservations.ui.table_list.TableAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailsFragment : Fragment(), OnTableEventListener {

    private lateinit var binding: FragmentEventDetailsBinding
    private lateinit var adapter: TableAdapter
    private val viewModel2: EventDetailsViewModel by viewModel()
    private val args: EventDetailsFragmentArgs by navArgs()
    private lateinit var event: Event

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        binding.btnReserve.setOnClickListener { showNewTableFragment() }
        event = viewModel2.getEventById(args.eventId)!!
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.tableListRvTables.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TableAdapter()
        event.reservations?.let { adapter.setTables(it) }
        adapter.onTableSelectedListener = this
        binding.tableListRvTables.adapter = adapter
    }

    private fun showNewTableFragment() {
        val action = EventDetailsFragmentDirections.actionEventDetailsFragmentToNewTableFragment(
            args.eventId
        )
        findNavController().navigate(action)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val event = viewModel2.getEventById(args.eventId)
        display(event)
    }

    private fun display(event: Event?) {
        event?.let {
            binding.apply {
                tvEventTitle.text = event.title
                return
            }
        }
        binding.tvEventTitle.text = "No such event exists."
    }

    companion object {
        val Tag = "EventsDetails"
        val EventIdKey = "EventId"

        fun create(id: Long): Fragment {
            val fragment = EventDetailsFragment()
            return fragment
        }
    }

    override fun onTableSelected(reservation: Reservation) {
        val action =
            EventDetailsFragmentDirections.actionEventDetailsFragmentToTableDetailsFragment(
                reservation
            )
        findNavController().navigate(action)
    }

    override fun onTableLongPress(reservation: Reservation?): Boolean {
        reservation?.let {

        }
        return true
    }


}