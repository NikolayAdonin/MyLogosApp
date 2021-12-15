package com.example.logosapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.logosapp.R
import kotlinx.android.synthetic.main.fragment_kab.*

class KabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val paychartview = inflater.inflate(R.layout.fragment_kab, container, false)
        val spinner = paychartview.findViewById<Spinner>(R.id.spinner)

        val imageView: ImageView = paychartview.findViewById(R.id.imageView)

        imageView.setImageDrawable(ContextCompat.getDrawable(requireActivity(),R.drawable.profile_photo))

        val navPlan = resources.getStringArray(R.array.NavPlan)

        if (spinner != null) {
            val adapter = ArrayAdapter.createFromResource(requireActivity(),R.array.NavPlan, android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    var mListView = paychartview.findViewById<ListView>(R.id.currentNavPlan)
                    when(spinner.selectedItemPosition){
                        1 -> {
                            val mListAdapter = ArrayAdapter.createFromResource(requireActivity(),R.array.Points2199,android.R.layout.simple_list_item_1)
                            currentNavPlan.adapter = mListAdapter
                        }
                        2 -> {
                            val mListAdapter = ArrayAdapter.createFromResource(requireActivity(),R.array.Points557,android.R.layout.simple_list_item_1)
                            currentNavPlan.adapter = mListAdapter
                        }
                        else ->{
                            val mListAdapter = ArrayAdapter.createFromResource(requireActivity(),R.array.Points567,android.R.layout.simple_list_item_1)
                            currentNavPlan.adapter = mListAdapter
                        }
                    }
                    }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    }
                }
            }
            return paychartview
    }

}