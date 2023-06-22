package com.example.clubreservations.ui.event_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clubreservations.databinding.FragmentEventDetailsBinding
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Reservation
import com.example.clubreservations.presentation.event.EventDetailsViewModel
import com.example.clubreservations.ui.table_list.OnTableEventListener
import com.example.clubreservations.ui.table_list.TableAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailsFragment : Fragment(), OnTableEventListener {

    private lateinit var binding: FragmentEventDetailsBinding
    private lateinit var adapter: TableAdapter
    private val viewModel2: EventDetailsViewModel by viewModel()
    private val args: EventDetailsFragmentArgs by navArgs()
    private lateinit var event: Event
    private var reservations: MutableLiveData<MutableList<Reservation>> = MutableLiveData(
        mutableListOf())
    private var reservedTables: MutableLiveData<MutableList<String>> = MutableLiveData(mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        binding.btnReserve.setOnClickListener { showNewTableFragment() }
        event = viewModel2.getEventById(args.eventId)!!
        reservations.value = event.reservations!!.toMutableList()
        reservedTables.value = event.takenTables!!.toMutableList()
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
        adapter.setTables(reservations.value!!)
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

    override fun onTableLongPress(reservation: Reservation): Boolean {
        reservations.value?.remove(reservation)
        reservations.value = reservations.value
        viewModel2.updateReservations(reservations.value!!)

        adapter.setTables(reservations.value!!)
        adapter.onTableSelectedListener = this
        binding.tableListRvTables.adapter = adapter

        reservedTables.value?.remove(reservation.ordNumber.toString())
        reservedTables.value = reservedTables.value
        viewModel2.updateReservedTables(reservedTables.value!!)

        return true
    }


}