package com.example.clubreservations.ui.event_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clubreservations.databinding.FragmentEventDetailsBinding
import com.example.clubreservations.model.Event
import com.example.clubreservations.model.Table
import com.example.clubreservations.presentation.event.EventDetailsViewModel
import com.example.clubreservations.presentation.table.TableListViewModel
import com.example.clubreservations.ui.table_list.OnTableEventListener
import com.example.clubreservations.ui.table_list.TableAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class EventDetailsFragment :  Fragment(), OnTableEventListener {

    private lateinit var binding: FragmentEventDetailsBinding
    private lateinit var adapter: TableAdapter
    private val viewModel: TableListViewModel by viewModel()
    private val viewModel2: EventDetailsViewModel by viewModel()
    private val args: EventDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailsBinding.inflate(layoutInflater)
        binding.btnReserve.setOnClickListener { showNewTableFragment() }
        setupRecyclerView()
        viewModel.tables.observe(viewLifecycleOwner) {
            if (it != null && it.isNotEmpty()) {
                adapter.setTables(it)
            }
        }
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.tableListRvTables.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = TableAdapter()
        adapter.onTableSelectedListener = this
        binding.tableListRvTables.adapter = adapter
    }

    private fun showNewTableFragment() {
        val action =
            EventDetailsFragmentDirections.actionEventDetailsFragmentToNewTableFragment()
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

    override fun onTableSelected(id: Long?) {
        val action =
            EventDetailsFragmentDirections.actionEventDetailsFragmentToTableDetailsFragment(id ?: -1)
        findNavController().navigate(action)
    }

    override fun onTableLongPress(table: Table?): Boolean {
        table?.let { it ->
            viewModel.delete(it)
        }
        return true
    }


}