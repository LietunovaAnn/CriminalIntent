package com.example.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import java.util.Date


private const val ARG_CRIME = "crime"

class ZoomedImageDialogFragment: DialogFragment() {

    private lateinit var photoView: ImageView
    private val crimeRepository = CrimeRepository.get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val view: View = inflater.inflate(R.layout.zoom_image_dialog, container, false)

        photoView = view.findViewById(R.id.crime_zoom_photo)
        val photoFileName = arguments?.getSerializable(ARG_CRIME) as String
        val photoFile = crimeRepository.getPhotoFileForName(photoFileName)
        val bitmap = getScaledBitmap(photoFile.path, requireActivity())
        photoView.setImageBitmap(bitmap)

        return view
    }

    companion object {
        fun newInstance(photoFileName: String): ZoomedImageDialogFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME, photoFileName)
            }
            return ZoomedImageDialogFragment().apply {
                arguments = args
            }
        }
    }
}