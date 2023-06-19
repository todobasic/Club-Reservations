package com.example.clubreservations.ui.table_new

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.clubreservations.R
import com.example.clubreservations.databinding.FragmentNewTableBinding
import com.example.clubreservations.model.Reservation
import com.example.clubreservations.presentation.table.TableNewViewModel
import com.example.clubreservations.utils.getDate
import kotlinx.android.synthetic.main.fragment_new_table.selfieImage
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream

class NewTableFragment : Fragment() {

    private lateinit var binding: FragmentNewTableBinding
    private val viewModel: TableNewViewModel by viewModel()
    private val args: NewTableFragmentArgs by navArgs()

    val REQUEST_CODE = 2000

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewTableBinding.inflate(layoutInflater)
        binding.btnAddTable.setOnClickListener { saveReservation() }

        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.numbers,
            android.R.layout.simple_spinner_item
        )

        binding.btnImageCapture.setOnClickListener { captureImage() }

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinner.adapter = adapter
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        return binding.root
    }

    private fun captureImage() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE && data != null){
            binding.selfieImage.setImageBitmap(data.extras!!.get("data") as Bitmap)
        }


    }

    private fun saveReservation() {

        val eventId = args.eventId

        val ordNumber = binding.spinner.selectedItem.toString()
        val title = binding.etTableUsernameInput.text.toString()
        val content = binding.etTableDrinkInput.text.toString()
        val dateAdded = binding.dpDateAddedInput.getDate()

        val bitmap = (binding.selfieImage.drawable as BitmapDrawable).bitmap
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val imageUploaded: ByteArray = stream.toByteArray()


        val reservations = viewModel.getEventReservations(eventId).toMutableList()
        reservations.add(
            Reservation(
                ordNumber = ordNumber,
                title = title,
                content = content,
                dateAdded = dateAdded,
                imageUploaded = imageUploaded
            )
        )

        viewModel.updateReservations(eventId, reservations)

        Toast.makeText(context, "Reserving...", Toast.LENGTH_SHORT).show()

        val action =
            NewTableFragmentDirections.actionNewTableFragmentToEventDetailsFragment(eventId)
        findNavController().navigate(action)
    }

    companion object {
        val Tag = "NewTable"

        fun create(): Fragment {
            return NewTableFragment()
        }
    }


}