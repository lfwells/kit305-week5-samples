package com.example.week05tabs.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.week05tabs.databinding.FragmentStudentBinding

class StudentFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val inflatedView = FragmentStudentBinding.inflate(layoutInflater, container, false)

        //do normal onCreate things in here, e.g.
        //btnCamera.setOnClickListener ...

        return inflatedView.root
    }
}