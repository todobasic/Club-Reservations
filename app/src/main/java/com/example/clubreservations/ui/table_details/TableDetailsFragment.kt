package com.example.clubreservations.ui.table_details

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.clubreservations.databinding.FragmentTableDetailsBinding
import com.example.clubreservations.model.Reservation
import com.example.clubreservations.presentation.table.TableDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat

class TableDetailsFragment : Fragment() {

    private val dateDisplayFormat = SimpleDateFormat("dd.MM.yyyy")
    private lateinit var binding: FragmentTableDetailsBinding
    private val viewModel: TableDetailsViewModel by viewModel()
    private val args: TableDetailsFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTableDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val table = args.reservation
        display(table)
    }

    private fun display(reservation: Reservation?) {
        reservation?.let {
            binding.apply {
                tvTableDetailsTablenumber.text = "Table " + reservation.ordNumber
                tvTableDetailsUsername.text = reservation.title
                tvTableDetailsDrink.text = reservation.content
                tvTableDetailsDate.text = dateDisplayFormat.format(it.dateAdded)
                ivSelfieImage.setImageBitmap(BitmapFactory.decodeByteArray(reservation.imageUploaded, 0, reservation.imageUploaded.size))
                return
            }
        }
        binding.tvTableDetailsUsername.text = "Sorry, no such table exists"
    }

    companion object {
        val Tag = "TablesDetails"
        val TableIdKey = "TableId"

        fun create(id: Long): Fragment {
            val fragment = TableDetailsFragment()
            return fragment
        }
    }


}